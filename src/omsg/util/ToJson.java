package omsg.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ToJson {
	public static String objectToGson(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;

	}
	
	public static String obectToGsonPretty(Object obj){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(obj);
		return json;
	}

	public static String csvToJson(String csvPath) {
		int i;
		String line = "";
		BufferedReader fileReader = null;
		boolean initial = true;
		ArrayList<String> header = new ArrayList<String>();
		JSONObject record = new JSONObject();
		String json = new String("[");
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
				json = json + "\n" +  record.toString() +",";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		json = json.substring(0, json.length()-1);
		json = json + "]";
		return json;
	}
}
