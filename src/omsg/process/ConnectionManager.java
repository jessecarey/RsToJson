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
		
		for(int i = 0; i < configSet.size() ; i++){
			String driver = configSet.get(i).getDriver();
			String url = configSet.get(i).getUrl();
			String username = configSet.get(i).getUsername();
			String password = configSet.get(i).getPassword();
			conns.add(ConnectionFactory.connect(driver, url, username, password));
		}
		System.out.println();
		
		
		
		
		

	}
	
}
