import java.io.File;
import java.io.IOException;

import org.json.JSONException;

import osmg.util.PropertyManager;

public class Connection {
	public static void main(String[] args) throws JSONException, IOException {
		/*String json = new String();
		final String username = "CRMSYS";
		final String password = "CRMSYS";
		final String url = "jdbc:db2://nyprod.militarycars.com:446/NYPROD";
		final String mysJDBCDriver = "com.ibm.db2.jcc.DB2Driver";
		String queryString = "SELECT COLUMN_NAME,COLUMN_TEXT, DATA_TYPE, LENGTH FROM QSYS2.SYSCOLUMNS WHERE DBNAME='QS36F' AND TABLE_NAME='CMAST'" ;

		java.sql.Connection conn = null;
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
			System.out.println(driver.toString());
			conn = DriverManager.getConnection(url, sysprops);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//retrieves json
		JsonFactory jf = new JsonFactory(queryString, conn);
		try {
			List<LinkedHashMap<String, Object>> results = jf.runFactory();
			json = JsonFactory.generateJSON(results);
			System.out.println(json);
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//write to file
		File file = new File("C:\\Users\\bpmdev\\Desktop\\test.json");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		fw.write(json);
		fw.flush();
		fw.close();
		String test = "TestString";
		System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "C_NAME"));
		*/
		File testFile = new File("text.txt");
		PropertyManager pm = PropertyManager.getInstance();
		//System.out.println(pm.getProperty("test.number"));
		pm.getDebugLogger().info(testFile.getAbsoluteFile());
	}
	
}
