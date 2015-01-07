package org.smk.solr.transformer.fullexport;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_production_place{

	protected final static Logger log = Logger .getLogger(Process_production_place.class);

	/**	 
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row){
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_production_place - csid:%s", (String) row.get("csid")));

		if ((String)row.get("object_production_place") == null)
			return row;

		String[] places_split = ((String) row.get("object_production_place")).split(Util.split_1_niv);                      				
		int arrayLength = places_split.length;        

		ArrayList<String> udgivet = new ArrayList<String> ();	
		ArrayList<String> udfoert = new ArrayList<String> ();	

		for(int i = 0; i < arrayLength; i++) {         
			String[] values = places_split[i].split(Util.split_2_niv);			         			      					
			String place = Util.getValueFromSplit(values, 0);
			String type = Util.getValueFromSplit(values, 1);	

			if(Util.isValidDataText(place)){
				switch(prodPlaceType.toString(type.replaceAll("[^A-Za-z0-9 ]", ""))) {
				case udgivelsessted:       					
					udgivet.add(place);	
					break;			       
				case udfrelsessted:                            

					udfoert.add(place);	
					break;			                               
				}				
			}			 
		}

		if (udfoert.size() > 0)        
			row.put("object_production_place_udgivet", StringUtils.join(udgivet, Util.split_1_niv)); 

		if (udgivet.size() > 0)        
			row.put("object_production_place_udfoert", StringUtils.join(udfoert, Util.split_1_niv)); 

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
