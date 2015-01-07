package org.smk.solr.transformer.generic;

import java.util.Map;

import org.apache.log4j.Logger;

public class Process_location {

	protected final static Logger log = Logger .getLogger(Process_producents.class);
	
	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_location - id:%s", (String) row.get("id")));
		
		if ((String)row.get("location_name") == null)
			return row;

		String loc = (String) row.get("location_name");

		if(loc.toUpperCase().indexOf("SAL") == -1)
			row.remove("location_name");

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_location - id:%s\r\n--------------", (String) row.get("id")));
		
		return row; 
	}	
}
