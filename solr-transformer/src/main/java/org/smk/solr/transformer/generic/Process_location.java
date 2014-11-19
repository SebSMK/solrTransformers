package org.smk.solr.transformer.generic;

import java.util.Map;

import org.smk.solr.transformer.generic.Util;

import org.apache.commons.lang.StringUtils;

public class Process_location {

	/**
     * Process fields that must be capitalized
     **/            
     public Object transformRow(Map<String, Object> row) {
    	 String loc = (String) row.get("location_name");
         
         if(loc.toUpperCase().indexOf("SAL") == -1)
           row.remove("location_name");
                  
         return row; 
     }	
}
