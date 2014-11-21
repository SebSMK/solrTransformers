package org.smk.solr.transformer.generic;

import java.util.Map;

public class Process_location {

	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {
		if ((String)row.get("location_name") == null)
			return row;

		String loc = (String) row.get("location_name");

		if(loc.toUpperCase().indexOf("SAL") == -1)
			row.remove("location_name");

		return row; 
	}	
}
