package org.smk.solr.transformer.search.SAFO;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_vaerkstatus{

	protected final static Logger log = Logger .getLogger(Process_vaerkstatus.class);

	/**	 
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row){
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_vaerkstatus - csid:%s", (String) row.get("csid")));
		 		
		if ((String)row.get("vaerkstatus") != null){			                    						      		
			ArrayList<String> vaerkstatus = processField(((String) row.get("vaerkstatus")).split(Util.split_1_niv));		
			row.remove("vaerkstatus");
			if (vaerkstatus.size() > 0)        
				row.put("vaerkstatus", vaerkstatus);
		}											 

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_vaerkstatus - csid:%s\r\n--------------", (String) row.get("csid")));

		return row;		

	}	
	
	private ArrayList<String> processField(String[] field_split){
		                      				
		int arrayLength = field_split.length;        

		ArrayList<String> field_data = new ArrayList<String> ();

		for(int i = 0; i < arrayLength; i++) {         					         			      				
			String value = field_split[i];	
			if(Util.isValidDataText(value))
				field_data.add(value);			
		}
		
		return field_data;
	}
}
