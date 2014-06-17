package omsg.process;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import omsg.entities.DataSetConfig;
import omsg.factory.ConfigSetFactory;
import omsg.factory.ConnectionFactory;
import omsg.util.PropertyManager;

import org.json.JSONException;

public class ConnectionManager {
	public static void main(String[] args) throws JSONException, IOException {
		String queryString;
		Connection conn = null;
		PropertyManager pm = PropertyManager.getInstance();
		ConfigSetFactory csf = new ConfigSetFactory();
		ArrayList<Connection> conns = new ArrayList<Connection>();
		ArrayList<DataSetConfig> configSet = csf.getConfigSet();
		pm.loadConfigSet(configSet);
		conns = generateConnectionList(configSet);
		ReportCachingProcess rcp = new ReportCachingProcess();
		rcp.init(conns);
		System.out.println();
	}
	
	private static ArrayList<Connection> generateConnectionList(ArrayList<DataSetConfig> configSet){
		ArrayList<Connection> conns = new ArrayList<Connection>();
		for(int i = 0; i < configSet.size() ; i++){
			conns = getConnection(configSet, conns, i);
		}
		return conns;
	}
	
	private static ArrayList<Connection> getConnection(ArrayList<DataSetConfig> configSet, ArrayList<Connection> conns, int i){
		conns.add(ConnectionFactory.connect(configSet.get(i)));
		PropertyManager pm = PropertyManager.getInstance();
		pm.getDebugLogger().info("Connection established " + configSet.get(i).getName() + " " +  configSet.get(i).getDriver() + " " + configSet.get(i).getSource() + " " + configSet.get(i).getUrl());
		return conns;
	}
	
}
