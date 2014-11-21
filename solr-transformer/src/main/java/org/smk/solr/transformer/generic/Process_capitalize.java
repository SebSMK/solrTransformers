package org.smk.solr.transformer.generic;

import java.util.Map;

import org.smk.solr.transformer.generic.Util;

import org.apache.commons.lang.StringUtils;

public class Process_capitalize {

	/**
     * Process fields that must be capitalized
     **/            
     public Object transformRow(Map<String, Object> row) {
    	 String acq_method = (String) row.get("acq_method");
         String acq_source = (String) row.get("acq_source");                  
         
         if (Util.isValidDataText(acq_method))
         	row.put("acq_method", StringUtils.capitalize(acq_method));
         
         if (Util.isValidDataText(acq_source))
         	row.put("acq_source", StringUtils.capitalize(acq_source));
                         
         return row;       
     }	
}
