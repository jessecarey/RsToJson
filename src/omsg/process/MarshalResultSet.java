package omsg.process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class MarshalResultSet {
	
	public static List<LinkedHashMap<String, Object>> getEntitiesFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<LinkedHashMap<String, Object>> entities = new ArrayList<>();
        while (resultSet.next()) {
            entities.add(getEntityFromResultSet(resultSet));
        }
        return entities;
    }
   /**
    * Creates a map representing a single tuple of the result set. 
    * @param resultSet
    * @return
    * @throws SQLException
    */
    private static LinkedHashMap<String, Object> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        LinkedHashMap<String, Object> resultsMap = new LinkedHashMap<>();
        for (int i = 1; i <= columnCount; ++i) {
            String columnName = metaData.getColumnName(i).toLowerCase();
            //columnName = CaseFormatConversion.toReadable(columnName);
            Object object = resultSet.getObject(i);
            if(object instanceof String){
            	object = ((String)object).trim();
            }
            resultsMap.put(columnName, object);
        }
        return resultsMap;
    }
	
	
	public static String CsvToJsonDynamic(String csvPath){
		int i;
		String line = "";
		BufferedReader fileReader = null;
		boolean initial = true;
		ArrayList<String> header = new ArrayList<String>();
		JSONObject record = new JSONObject();
		
		
		try {
			fileReader = new BufferedReader(new FileReader(csvPath));
			while ((line = fileReader.readLine()) != null) {
				i = 0;
				String[] tokens = line.split(",");
				if (initial) {

					for (String token : tokens) {
						if (token.startsWith("\"")) {
							token = token.substring(1);
						}
						if (token.endsWith("\"")) {
							token = token.substring(0, token.length() - 1);
						}
						header.add(token);
					}
					initial = false;
					continue;

				}
		    // Get all tokens available in line
				for (String token : tokens) {
					if (token.startsWith("\"")) {
						token = token.substring(1);
					}
					if (token.endsWith("\"")) {
						token = token.substring(0, token.length() - 1);
					}
					record.put(header.get(i).toString(), token);
					i++;
					if (i == header.size())
						break;

				}
				System.out.println(record);
		   
			}

		  	} catch (IOException e) {
		  		e.printStackTrace();
		  	} catch (JSONException e) {

		  		e.printStackTrace();
		  	}
		  	return record.toString();
		 }
	}

