package org.smk.solr.transformer.generic;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.smk.solr.transformer.generic.Util;

public class Process_century {
	
	/**
     * Process fields that must be capitalized
     **/            
     public Object transformRow(Map<String, Object> row) {
    	 Object prod_earlier_date = row.get("object_production_date_earliest");
    	 Date date = new Date((Long) prod_earlier_date); 
       
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(date);

       int century = (calendar.get(Calendar.YEAR) / 100) +1;
       
       row.put("object_production_century_earliest", Integer.toString(century));
 
//    	 row.put("object_production_century_earliest", prod_earlier_date);
    	 
//         if(Util.isValidDataText(prod_earlier_date)){
//           prod_earlier_date = prod_earlier_date.replaceAll("CET", "EDT");
//           prod_earlier_date = prod_earlier_date.replaceAll("CEST", "EDT");
//           Date date = new Date(prod_earlier_date); 
//           
//           Calendar calendar = Calendar.getInstance();
//           calendar.setTime(date);
//
//           int century = (calendar.get(Calendar.YEAR) / 100) +1;
//           
//           row.put("object_production_century_earliest", Integer.toString(century));
//         }        
         
         return row;
     }	

}
