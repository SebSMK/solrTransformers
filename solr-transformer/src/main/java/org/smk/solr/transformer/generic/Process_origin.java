package org.smk.solr.transformer.generic;

import java.util.Map;

import org.apache.log4j.Logger;

public class Process_origin{

	protected final static Logger log = Logger .getLogger(Process_producents.class);
	
	/**
	 * Extract ORIG work of related works
	 **/            
	public Object transformRow(Map<String, Object> row) {
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_related_works - id:%s", (String) row.get("id")));
		
		if ((String)row.get("related_id") == null)
			return row;

		String[] related_works_split = ((String) row.get("related_id")).split(Util.split_1_niv);  				
		int arrayLength = related_works_split.length;				

		for(int i = 0; i < arrayLength; i++) {	
			String[] values = related_works_split[i].split(Util.split_2_niv);			         
			String objectnumber = values.length > 0 ? new String(values[1]) : "";

			if(objectnumber.toUpperCase().indexOf("ORIG") > -1){
				row.put("related_works_orig_number", objectnumber);
				break;
			}			
		}            		

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_related_works - id:%s\r\n--------------", (String) row.get("id")));
		
		return row;

	}	
}
