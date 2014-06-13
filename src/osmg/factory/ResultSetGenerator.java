package osmg.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetGenerator {
		private final String queryString;
		protected java.sql.Connection conn;
		
		public ResultSetGenerator(String queryString, java.sql.Connection conn){
			this.queryString = queryString;
			this.conn = conn;
		}
		
		public ResultSet runQuery(){
			ResultSet rs = null;
			try {
				java.sql.PreparedStatement ps = conn.prepareStatement(queryString);
				rs=ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
		}
	
}
