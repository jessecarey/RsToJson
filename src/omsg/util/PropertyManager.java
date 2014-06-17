package omsg.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

import omsg.entities.DataSetConfig;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PropertyManager {
	//singleton
	private static PropertyManager INSTANCE;
	private ArrayList<DataSetConfig> configSet;
	//properties
	private Properties prop;
	private static final String PROP_FILE = "config/test.properties";
	//log
	private  Logger debugLogger  ;
	private  Logger errorLogger  ;
	private  Logger consoleLogger  ;
	private  Logger exportLogger  ;
	private  Logger duplicatesLogger  ;
	private static final String LOG_PROP_FILE = "config/log4j.properties";
	
	public PropertyManager(){
		prop = new Properties();
		
		try {
			InputStream is = new FileInputStream(PROP_FILE); 
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configureLoggers();
		
	}
	
	public static PropertyManager getInstance(){
		if(INSTANCE == null)
			INSTANCE = new PropertyManager();
		
		return INSTANCE;
	}
	
	
	public String getProperty(String key){
		if(prop.isEmpty())
			return null;
		else
			return prop.getProperty(key);
	}
	
	public Properties getProperties(){
		return prop;
	}
	
	private void configureLoggers(){
		PropertyConfigurator.configure(LOG_PROP_FILE);
		debugLogger = Logger.getLogger("DL");
		errorLogger = Logger.getLogger("EL");
		consoleLogger = Logger.getLogger("CL");
		exportLogger = Logger.getLogger("EXPL");
		duplicatesLogger = Logger.getLogger("DUPL");
		
	}
	
	public Logger getDebugLogger(){
		return debugLogger;
	}
	
	public Logger getErrorLogger(){
		return errorLogger;
	}
	
	public  Logger getConsoleLogger(){
		return consoleLogger ;
	}

	public Logger getExportLogger() {
		return exportLogger;
	}

	public Logger getDuplicatesLogger() {
		return duplicatesLogger;
	}
	
	public void loadConfigSet(ArrayList<DataSetConfig> configSet){
		this.configSet = configSet;
	}
	
	public ArrayList<DataSetConfig> getConfigSet(){
		return configSet;
	}

}
