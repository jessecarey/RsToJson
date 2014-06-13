package omsg.factory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


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
}
