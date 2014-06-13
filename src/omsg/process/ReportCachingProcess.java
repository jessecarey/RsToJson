package omsg.process;

import omsg.entities.DataSetConfig;
import omsg.util.PropertyManager;



public class ReportCachingProcess {
	
	
	
	public boolean init(){
		boolean status = false;
		
		PropertyManager ps = PropertyManager.getInstance();
		
		/*
		 * getConfigurationManager
		 * getConnectionsSet
		 */
		return status ;
	}
	
	public boolean processdataSets(){
		
		boolean status = false;
		
		/* ArrayList<DataSetConfig>  getConfigSets()
		 * 
		 * processDataSet(DataSetConfig dataSetConfig)
		 * 
		 */
		return status ;
	}
	
	
public boolean processdataSet(DataSetConfig dataSetConfig){
		
		boolean status = false;
		
		/* 
		 * ResultSet getResultSet(dataSetConfig)
		 * 
		 * String results = marshalResultSet(ResultSet, dataSetConfig)
		 * 
		 * storeResults(results, dataSetConfig)
		 */
		return status ;
	}

}
