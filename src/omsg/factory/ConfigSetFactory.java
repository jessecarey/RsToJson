package omsg.factory;
import java.util.ArrayList;

import omsg.entities.DataSetConfig;
import omsg.util.PropertyManager;

public class ConfigSetFactory {
	private PropertyManager pm;
	private ArrayList<DataSetConfig> configList;
	private String[] names;
	
	
	public ConfigSetFactory(){
		configList = new ArrayList<DataSetConfig>();
		pm = PropertyManager.getInstance();
		names = pm.getProperty("config.names").split(",");
		loadConfigs();
	}
	/**
	 * loads confiurations from properties file
	 */
	public void loadConfigs(){
		for(int i =0; i<names.length; i++){
			loadConfig(i);
		}
	}
	/**
	 * loads signle config from properties file
	 * @param pos
	 */
	public void loadConfig(int pos){
		//get values from properties
		String name = names[pos].trim();
		String type = pm.getProperty("config."+name+".type");
		String source = pm.getProperty("config."+name+".source");
		String sql = pm.getProperty("config."+name+".sql");
		String driver = pm.getProperty("config."+name+".driver");
		String username = pm.getProperty("config."+name+".username");
		String password = pm.getProperty("config."+name+".password");
		String url = pm.getProperty("config."+name+".url");
		String path = pm.getProperty("config."+name+".path");

		//store values in configSet
		DataSetConfig config= new DataSetConfig();
		config.setType(type);
		config.setName(name);
		config.setSource(source);
		config.setSql(sql);
		config.setDriver(driver);
		config.setUsername(username);
		config.setPassword(password);
		config.setUrl(url);
		config.setPath(path);
		configList.add(config);
		pm.getDebugLogger().info("Configuration " + name +" loaded");
		
	}
	
	public ArrayList getConfigSet(){
		return configList;
	}
}
