package org.smk.solr.transformer.search.SAFO;

import java.util.ArrayList;
import java.util.HashMap;
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

			String[] portrait_data = ((String) row.get("portrait_person")).split(Util.split_1_niv);
			int arrayLength = portrait_data.length; 

			ArrayList<String> portrait_name = new ArrayList<String> ();
			ArrayList<String> portrait_surname_firstname = new ArrayList<String> ();

			HashMap<String, ArrayList<String>> portraits_data = new HashMap<String, ArrayList<String>>();
			portraits_data.put("portrait_name", portrait_name);
			portraits_data.put("portrait_surname_firstname", portrait_surname_firstname);

			for(int i = 0; i < arrayLength; i++) {         
				String[] values = portrait_data[i].split(Util.split_2_niv);			         			      			
				concat_portraits_data(values, portraits_data);  			
			}  
					
			row.remove("portrait_person");
			
			if (portraits_data.get("portrait_name").size() > 0){
				row.put("portrait_person", portraits_data.get("portrait_name"));

				if (portraits_data.get("portrait_surname_firstname").size() > 0)        
					row.put("portrait_person_surname_firstname", portraits_data.get("portrait_surname_firstname"));
			}						
		}											 

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_portrait - csid:%s\r\n--------------", (String) row.get("csid")));

		return row;		

	}	

	private void concat_portraits_data(String[] values, HashMap<String, ArrayList<String>> portraits_data) { 
		String name = Util.getValueFromSplit(values, 0);
		String forname = Util.getValueFromSplit(values, 1);
		String surname = Util.getValueFromSplit(values, 2); 					   

		if(Util.isValidDataText(name)){
			portraits_data.get("portrait_name").add(name.trim());
			portraits_data.get("portrait_surname_firstname").add(Util.isValidDataText(surname) || Util.isValidDataText(forname)? String.format("%s %s", surname, forname) : "");				
		}
	}
}
