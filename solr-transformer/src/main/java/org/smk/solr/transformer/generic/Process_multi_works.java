package org.smk.solr.transformer.generic;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class Process_multi_works{

	protected final static Logger log = Logger .getLogger(Process_producents.class);
	
	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {										
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_multi_works - id:%s", (String) row.get("id")));
		
		if ((String)row.get("multi_work_ref") == null)
			return row;

		String[] multi_works_split = ((String) row.get("multi_work_ref")).split(Util.split_1_niv); 

		Map<Integer, String> multi_works_data = new TreeMap<Integer, String>();  				
		int arrayLength = multi_works_split.length;

		for(int i = 0; i < arrayLength; i++) {	
			String[] values = multi_works_split[i].split(Util.split_2_niv);
			String title = new String(values[0]);          
			String ref = new String(values[1]);
			String rank = ref.split("/").length < 2 ? "0" : ref.split("/")[1];  
			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(rank);

			String res = multi_works_split[i];                        
			if (Util.isValidDataText(title) && matcher.find())            
				multi_works_data.put(Integer.parseInt(matcher.group()), res);
		}            

		row.remove("multi_work_ref");        

		if (multi_works_data.size() > 0){
			String[] multi_work_ref = multi_works_data.values().toArray(new String[0]);
			row.put("multi_work_ref", StringUtils.join(multi_work_ref, Util.split_1_niv));			
		}          

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_multi_works - id:%s\r\n--------------", (String) row.get("id")));
		
		return row;

	}	
}
