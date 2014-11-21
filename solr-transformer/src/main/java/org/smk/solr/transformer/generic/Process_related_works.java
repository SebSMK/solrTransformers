package org.smk.solr.transformer.generic;

import java.util.Map;
import org.smk.solr.transformer.generic.Util;

public class Process_related_works{

	/**
	 * Extract ORIG work of related works
	 **/            
	public Object transformRow(Map<String, Object> row) {	
		String[] related_works_split = ((String) row.get("related_works_title_dk")).split(Util.split_1_niv);  				
		int arrayLength = related_works_split.length;				

		for(int i = 0; i < arrayLength; i++) {	
			String[] values = related_works_split[i].split(Util.split_2_niv);			         
			String objectnumber = new String(values[1]);
			
			if(objectnumber.toUpperCase().indexOf("ORIG") > -1){
				row.put("related_works_orig_number", objectnumber);
				break;
			}			
		}            		
		
		return row;

	}	
}
