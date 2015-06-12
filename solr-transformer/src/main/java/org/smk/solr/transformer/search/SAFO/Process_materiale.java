package org.smk.solr.transformer.search.SAFO;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_materiale{

	protected final static Logger log = Logger .getLogger(Process_materiale.class);

	/**	 
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row){
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_materiale - csid:%s", (String) row.get("csid")));
		 		
		if ((String)row.get("materiale") != null){			                    						      		
			ArrayList<String> materiales_data = processField(((String) row.get("materiale")).split(Util.split_1_niv));		
			row.remove("materiale");
			if (materiales_data.size() > 0)        
				row.put("materiale", materiales_data);
		}
		
		if ((String)row.get("materiale_en") != null){      		
			ArrayList<String> materiales_data = processField(((String) row.get("materiale_en")).split(Util.split_1_niv));		
			row.remove("materiale_en");
			if (materiales_data.size() > 0)        
				row.put("materiale_en", materiales_data);
		}							 

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_materiale - csid:%s\r\n--------------", (String) row.get("csid")));

		return row;		

	}	
	
	private ArrayList<String> processField(String[] materiale_split){
		                      				
		int arrayLength = materiale_split.length;        

		ArrayList<String> materiales_data = new ArrayList<String> ();

		for(int i = 0; i < arrayLength; i++) {         
			materiales_data.add(materiale_split[i]);			
		}
		
		return materiales_data;
	}
}
