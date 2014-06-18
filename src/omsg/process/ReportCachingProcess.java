package omsg.process;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import omsg.entities.DataSetConfig;
import omsg.factory.ResultSetGenerator;
import omsg.util.PropertyManager;
import omsg.util.ToJson;

public class ReportCachingProcess {

	private ArrayList<DataSetConfig> configSet;
	private ArrayList<Connection> connectionSet;
	private PropertyManager pm;

	public boolean init(ArrayList<Connection> connectionSet) {
		boolean status = false;
		pm = PropertyManager.getInstance();
		this.configSet = pm.getConfigSet();
		this.connectionSet = connectionSet;
		processdataSets();
		return status;
	}

	public boolean processdataSets() {

		boolean status = false;
		ArrayList<DataSetConfig> configSet = pm.getConfigSet();
		for (int i = 0; i < configSet.size(); i++) {
			processDataSet(configSet.get(i), connectionSet.get(i));
		}
		return status;
	}

	public boolean processDataSet(DataSetConfig dataSetConfig, Connection conn) {

		boolean status = true;
		if (dataSetConfig.getSource().equals("jdbc")) {
			processJdbc(dataSetConfig, conn);
		}
		else if (dataSetConfig.getSource().equals("csv")){
			processCsv(dataSetConfig);
		}
		return status;
	
	
	}
		
	public boolean processJdbc(DataSetConfig dataSetConfig, Connection conn){
		boolean status = true;
		String json = new String();
		ResultSet rs = ResultSetGenerator.generateJDBCResultSet(conn,
				dataSetConfig);
		pm.getDebugLogger().info(
				"Results processed for " + dataSetConfig.getSql());
		try {
			List<LinkedHashMap<String, Object>> results = MarshalResultSet
					.getEntitiesFromResultSet(rs);
			json = ToJson.objectToGson(results);
		} catch (SQLException e) {
			status = false;
		}
		try {
			storeResults(json, dataSetConfig);
		} catch (FileNotFoundException e) {
			status = false;
		}
		return status;
	}
	
	public boolean processCsv(DataSetConfig config){
		Boolean status = true;
		String results = ToJson.csvToJson(config.getPath());
		try {
			storeResults(results, config);
		} catch (FileNotFoundException e) {
			status = false;
		}
		
		return status;
	}

	public void storeResults(String results, DataSetConfig config)
			throws FileNotFoundException {
		String type = config.getType();
		String name = config.getName();
		String path = type + "\\" + name + "." + type;
		String newpath = "C:\\Users\\bpmdev\\workspace\\osmgjs\\WebContent\\Json\\" + name + "." +type; 
		PrintWriter pw = new PrintWriter(newpath);
		pw.print(results);
		pw.close();
		pm.getDebugLogger().info(
				"json stored for " + config.getName() + " in " + path);

	}

}
