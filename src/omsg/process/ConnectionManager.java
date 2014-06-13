package omsg.process;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection ;

import omsg.factory.ResultSetGenerator;
import omsg.util.PropertyManager;

import org.json.JSONException;

public class ConnectionManager {
	public static void main(String[] args) throws JSONException, IOException {
		String json = new String();
		final String username = "CRMSYS";
		final String password = "CRMSYS";
		final String url = "jdbc:db2://nyprod.militarycars.com:446/NYPROD";
		final String mysJDBCDriver = "com.ibm.db2.jcc.DB2Driver";
		String queryString;

		Connection conn = null;
		Driver driver = null;
		java.sql.ResultSet rs = null;
		//loads driver
		try {
			driver = (Driver) Class.forName(mysJDBCDriver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//creates connection
		java.util.Properties sysprops = System.getProperties();
		sysprops.put("user", username);
		sysprops.put("password", password);
		java.sql.PreparedStatement testStatement;
		try {
			conn = DriverManager.getConnection(url, sysprops);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		PropertyManager pm = PropertyManager.getInstance();
		queryString = pm.getProperty("SQL.test");
		pm.getDebugLogger().info(queryString);
		ResultSetGenerator rsg = new ResultSetGenerator(queryString, conn);
		rs = rsg.runQuery();
		pm.getDebugLogger().info(rs.toString());
	}
	
}
