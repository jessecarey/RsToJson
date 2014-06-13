package omsg.process;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

/**
 * JSON factory better implementation
 * @author bpmdev
 *
 */
public class ToJSONHashMap {
	

	/**
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public static List<LinkedHashMap<String, Object>> getEntitiesFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<LinkedHashMap<String, Object>> entities = new ArrayList<>();
        while (resultSet.next()) {
            entities.add(getEntityFromResultSet(resultSet));
        }
        convertToJSON(entities);
        return entities;
    }
   /**
    * Creates a map representing a single tuple of the result set. 
    * @param resultSet
    * @return
    * @throws SQLException
    */
    public static LinkedHashMap<String, Object> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        LinkedHashMap<String, Object> resultsMap = new LinkedHashMap<>();
        for (int i = 1; i <= columnCount; ++i) {
            String columnName = metaData.getColumnName(i).toLowerCase();
            Object object = resultSet.getObject(i);
            resultsMap.put(columnName, object);
        }
        return resultsMap;
    }
    /**
     * Uses googles GSON to convert the list of maps to
     * a json string.
     * @param entities
     * @return
     */
    public static String convertToJSON(List<LinkedHashMap<String, Object>> entities){
    	
    	Gson gson = new Gson();
    	String json = gson.toJson(entities);
    	System.out.println(json);
    	return json;
    }
}
