package omsg.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import omsg.entities.DataSetConfig;
import omsg.util.PropertyManager;

public class ResultSetGenerator {

		public static ResultSet generateJDBCResultSet(Connection conn, DataSetConfig config){
			ResultSet rs = null;
			try{
				PreparedStatement ps = conn.prepareStatement(config.getSql());
				rs=ps.executeQuery();
			} catch (SQLException e){
				e.printStackTrace();
			}
			return rs;
		}
	
}
