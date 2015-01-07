package org.smk.solr.transformer.fullexport;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_production_date{

	protected final static Logger log = Logger .getLogger(Process_production_date.class);

	/**	 
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row){
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_production_date - csid:%s", (String) row.get("csid")));

		if ((String)row.get("object_all_production_dates") == null)
			return row;

		String[] dates_split = ((String) row.get("object_all_production_dates")).split(Util.split_1_niv);                      				
		int arrayLength = dates_split.length;  
		String datering = new String();
		String datering_eng = new String();
		String datering_udfoert = new String();
		String datering_udgivet = new String();		        

		for(int i = 0; i < arrayLength; i++) {         
			String[] values = dates_split[i].split(Util.split_2_niv);			         			      								
			String date_type = Util.getValueFromSplit(values, 0);
			String date = Util.getValueFromSplit(values, 1);
			String date_eng = Util.getValueFromSplit(values, 4);

			switch(prodDateType.toString(date_type.replaceAll("[^A-Za-z0-9 ]", ""))) {
			case udfrt:                                
				if(Util.isValidDataText(date))
					datering_udfoert = date;
				break;

			case udgivet:
				if(Util.isValidDataText(date))
					datering_udgivet = date;
				break;                                

			default:
				if(Util.isValidDataText(date))
					datering = date;
				if(Util.isValidDataText(date_eng))
					datering_eng = date_eng;				                            
			}				
		}

		if(Util.isValidDataText(datering))                                                                                                     
			row.put("object_production_date", datering);

		if(Util.isValidDataText(datering_eng))                                                                                                     
			row.put("object_production_date_eng", datering_eng);        

		if(Util.isValidDataText(datering_udfoert))                                                                                                     
			row.put("object_production_date_udfoert", datering_udfoert); 

		if(Util.isValidDataText(datering_udgivet))                                                                                                     
			row.put("object_production_date_udgivet", datering_udgivet); 

		row.remove("object_all_production_dates"); 

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_production_date - csid:%s\r\n--------------", (String) row.get("csid")));

		return row;		

	}	

	static enum prodDateType {
		udgivet, udfrt, 		
		undefined;

		public static prodDateType toString(String str)
		{
			try {
				return valueOf(str);
			} 
			catch (Exception ex) {
				return undefined;
			}
		}   
	}

}
