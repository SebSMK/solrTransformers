package org.smk.solr.transformer.search.SAFO;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_portrait{

	protected final static Logger log = Logger .getLogger(Process_portrait.class);

	/**	 
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row){
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_portrait - csid:%s", (String) row.get("csid")));
		 		
		if ((String)row.get("portrait_person") != null){			                    						      		
			ArrayList<String> portrait_data = processField(((String) row.get("portrait_person")).split(Util.split_1_niv));		
			row.remove("portrait_person");
			if (portrait_data.size() > 0)        
				row.put("portrait_person", portrait_data);
		}											 

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_portrait - csid:%s\r\n--------------", (String) row.get("csid")));

		return row;		

	}	
	
	private ArrayList<String> processField(String[] portrait_split){
		                      				
		int arrayLength = portrait_split.length;        

		ArrayList<String> portrait_data = new ArrayList<String> ();

		for(int i = 0; i < arrayLength; i++) {         					         			      				
			String mat = portrait_split[i];	
			if(Util.isValidDataText(mat))
				portrait_data.add(mat);			
		}
		
		return portrait_data;
	}
}
