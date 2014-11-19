package org.smk.solr.transformer.generic;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.smk.solr.transformer.generic.Util;

import org.apache.commons.lang.StringUtils;

public class Process_multi_works{

	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {	
		String[] multi_works_split = ((String) row.get("multi_work_ref")).split(Util.split_1_niv); 
		String[] multi_works_data = {};    				
		int arrayLength = multi_works_split.length;
		String current_obj_number_java = (String)row.get("id");
		
		for(int i = 0; i < arrayLength; i++) {	
			String[] values = multi_works_split[i].split(Util.split_2_niv);
			String title = new String(values[0]);          
			String ref = new String(values[1]);
			String ref_java = new java.lang.String(values[1]);
			String type = new String(values[2]); 
			String rank = ref.split("/").length < 2 ? "0" : ref.split("/")[1];  
//			rank = parseInt(/\d+/.exec(rank));
			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(rank);

			String res = multi_works_split[i];                        
			if (Util.isValidDataText(title) && matcher.find())            
				multi_works_data[matcher.start()] = res;

		}            

		row.remove("multi_work_ref");        

		if (multi_works_data.length > 0)          
			row.put("multi_work_ref", StringUtils.join(multi_works_data, Util.split_1_niv));

		return row;

	}	
}
