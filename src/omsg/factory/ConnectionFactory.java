package omsg.factory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import omsg.entities.DataSetConfig;


public class ConnectionFactory {

	public static Connection connect(String driver, String url, String username, String password){
		Connection conn;
		Driver drive;
		try {
			drive = (Driver) Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (InstantiationException | SQLException | IllegalAccessException
				| ClassNotFoundException e) {
			e.getStackTrace();
		}
		return null;
	}
	
	public static Connection connect(DataSetConfig config){
		Connection conn;
		Driver drive;
		String driver = config.getDriver();
		String url = config.getUrl();
		String username = config.getUsername();
		String password = config.getPassword();
		if(url == null){
			return null;
		}
		try {
			drive = (Driver) Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (InstantiationException | SQLException | IllegalAccessException
				| ClassNotFoundException e) {
			e.getStackTrace();
		}
		return null;
		
	}
}
