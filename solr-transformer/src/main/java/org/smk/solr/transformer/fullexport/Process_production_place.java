package org.smk.solr.transformer.fullexport;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.fullexport.Process_production_date.prodDateType;
import org.smk.solr.transformer.generic.Util;

public class Process_production_place{

	protected final static Logger log = Logger .getLogger(Process_production_place.class);

	/**	 
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row){
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_production_place - csid:%s", (String) row.get("csid")));

		ArrayList<String> udgivet = new ArrayList<String> ();	
		ArrayList<String> udfoert = new ArrayList<String> ();	

		//* get places
		if ((String)row.get("object_production_place") == null)
			return row;

		String[] places_split = ((String) row.get("object_production_place")).split(Util.split_1_niv);                      				
		int arrayLength = places_split.length;        

		ArrayList<String> place_udgivet = new ArrayList<String> ();	
		ArrayList<String> place_udfoert = new ArrayList<String> ();	

		for(int i = 0; i < arrayLength; i++) {         
			String[] values = places_split[i].split(Util.split_2_niv);			         			      					
			String place = Util.getValueFromSplit(values, 0);
			String type = Util.getValueFromSplit(values, 1);	

			if(Util.isValidDataText(place)){
				switch(prodPlaceType.toString(type.replaceAll("[^A-Za-z0-9 ]", ""))) {
				case udgivelsessted:       					
					place_udgivet.add(place);	
					break;			       
				case udfrelsessted:                            
					place_udfoert.add(place);	
					break;			                               
				}				
			}			 
		}

		//* get dates

		ArrayList<String> datering_udfoert = new ArrayList<String> ();	
		ArrayList<String> datering_udgivet = new ArrayList<String> ();

		if ((String)row.get("object_all_production_dates") != null){
			String[] dates_split = ((String) row.get("object_all_production_dates")).split(Util.split_1_niv);                      				
			arrayLength = dates_split.length;  

			for(int i = 0; i < arrayLength; i++) {         
				String[] values = dates_split[i].split(Util.split_2_niv);			         			      								
				String date_type = Util.getValueFromSplit(values, 0);
				String date = Util.getValueFromSplit(values, 1);

				switch(prodDateType.toString(date_type.replaceAll("[^A-Za-z0-9 ]", ""))) {
				case udfrt:                                
					if(Util.isValidDataText(date))
						datering_udfoert.add(date);
					break;

				case udgivet:
					if(Util.isValidDataText(date))
						datering_udgivet.add(date);
					break;                                
				}				
			}			
		}					

		//* trying to match places and dates... (see CollectionsSpace interface / database in order to understand)
		if(place_udfoert.size() == datering_udfoert.size()){
			for(int i = 0; i < place_udfoert.size(); i++) {  
				udfoert.add(String.format("%s %s", place_udfoert.get(i), datering_udfoert.get(i)));				
			}						
		}else{
			for(int i = 0; i < place_udfoert.size(); i++) {  
				udfoert.add(place_udfoert.get(i));				
			}
		}

		if(place_udgivet.size() == datering_udgivet.size()){
			for(int i = 0; i < place_udgivet.size(); i++) {  
				udgivet.add(String.format("%s %s", place_udgivet.get(i), datering_udgivet.get(i)));				
			}						
		}else{
			for(int i = 0; i < place_udgivet.size(); i++) {  
				udgivet.add(place_udgivet.get(i));				
			}
		}

		if (udgivet.size() > 0)        
			row.put("object_production_place_udgivet", StringUtils.join(udgivet, Util.split_1_niv)); 

		if (udfoert.size() > 0)        
			row.put("object_production_place_udfoert", StringUtils.join(udfoert, Util.split_1_niv)); 

		row.remove("object_production_place");

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_production_place - csid:%s\r\n--------------", (String) row.get("csid")));

		return row;		

	}	

	static enum prodPlaceType {
		udgivelsessted, udfrelsessted, 		
		undefined;

		public static prodPlaceType toString(String str)
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
