package org.smk.solr.transformer.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Process_producents{

	protected final static Logger log = Logger .getLogger(Process_producents.class);

	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_producents - id:%s", (String) row.get("id")));

		if ((String)row.get("producents_data") == null)
			return row;

		String[] producents_split = ((String) row.get("producents_data")).split(Util.split_1_niv);                      				
		int arrayLength = producents_split.length;        

		ArrayList<String>  artist_name = new ArrayList<String> ();
		ArrayList<String>  artist_birth_en = new ArrayList<String> ();
		ArrayList<String>  artist_birth_dk = new ArrayList<String> ();
		ArrayList<String>  artist_death_en = new ArrayList<String> ();
		ArrayList<String>  artist_death_dk = new ArrayList<String> ();
		ArrayList<String>  artist_natio = new ArrayList<String> ();
		ArrayList<String>  artist_natio_en = new ArrayList<String> ();
		ArrayList<String>  artist_auth = new ArrayList<String> ();
		ArrayList<String>  artist_surname_firstname = new ArrayList<String> ();

		HashMap<String, ArrayList<String>> artists_data = new HashMap<String, ArrayList<String>>();
		artists_data.put("artist_name", artist_name);
		artists_data.put("artist_birth_en", artist_birth_en);
		artists_data.put("artist_birth_dk", artist_birth_dk);
		artists_data.put("artist_death_en", artist_death_en);
		artists_data.put("artist_death_dk", artist_death_dk);
		artists_data.put("artist_natio_dk", artist_natio);
		artists_data.put("artist_natio_en", artist_natio_en);
		artists_data.put("artist_auth", artist_auth);
		artists_data.put("artist_surname_firstname", artist_surname_firstname);		

		for(int i = 0; i < arrayLength; i++) {         
			String[] values = producents_split[i].split(Util.split_2_niv);			         			      			
			concat_artists_data(values, artists_data);  			
		}                                                                                                        

		if (artists_data.get("artist_name").size() > 0){
			row.put("artist_name", artists_data.get("artist_name"));

			if (artists_data.get("artist_birth_en").size() > 0)        
				row.put("artist_birth_en", artists_data.get("artist_birth_en"));

			if (artists_data.get("artist_birth_dk").size() > 0)        
				row.put("artist_birth_dk", artists_data.get("artist_birth_dk"));

			if (artists_data.get("artist_death_en").size() > 0)        
				row.put("artist_death_en", artists_data.get("artist_death_en"));

			if (artists_data.get("artist_death_dk").size() > 0)        
				row.put("artist_death_dk", artists_data.get("artist_death_dk"));

			if (artists_data.get("artist_natio_dk").size() > 0)        
				row.put("artist_natio_dk", artists_data.get("artist_natio_dk"));

			if (artists_data.get("artist_natio_en").size() > 0)        
				row.put("artist_natio_en", artists_data.get("artist_natio_en"));

			if (artists_data.get("artist_auth").size() > 0)        
				row.put("artist_auth", artists_data.get("artist_auth"));
			
			if (artists_data.get("artist_surname_firstname").size() > 0)        
				row.put("artist_surname_firstname", artists_data.get("artist_surname_firstname"));
		}        

		row.remove("producents_data"); 
		
		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_producents - id:%s\r\n--------------", (String) row.get("id")));
		
		return row;		

	}	


	/**
	 * Concat artist data 
	 *        
	 * @datas                   artist data  
	 * @all_artists_data        object of arrays (passed by reference)
	 * 
	 * Return: copy concatened artist data in @all_artists_data (passed by reference)                           
	 **/
	private void concat_artists_data(String[] values, HashMap<String, ArrayList<String>> artists_data) {    			         			      			    	
		String role = Util.getValueFromSplit(values, 1);			
		String name = Util.getValueFromSplit(values, 2);
		String birth = Util.getValueFromSplit(values, 4);
		String birth_en = Util.getValueFromSplit(values, 5);
		String death = Util.getValueFromSplit(values, 7);
		String death_en = Util.getValueFromSplit(values, 8);
		String natio = Util.getValueFromSplit(values, 9);
		String natio_eng = Util.getValueFromSplit(values, 10);
		String forname = Util.getValueFromSplit(values, 11);
		String surname = Util.getValueFromSplit(values, 12);		

		if(Util.isValidDataText(name)){
			artists_data.get("artist_name").add(name.trim());                                  
			artists_data.get("artist_auth").add(Util.isValidDataText(role)? role : "original");
			artists_data.get("artist_birth_dk").add(Util.isValidDataText(birth)? birth : "");            
			artists_data.get("artist_birth_en").add(Util.isValidDataText(birth_en)? birth_en : "");
			artists_data.get("artist_death_dk").add(Util.isValidDataText(death)? death : "");
			artists_data.get("artist_death_en").add(Util.isValidDataText(death_en)? death_en : "");
			artists_data.get("artist_natio_dk").add(Util.isValidDataText(natio)? natio : "");
			artists_data.get("artist_natio_en").add(Util.isValidDataText(natio_eng)? natio_eng : "");
			artists_data.get("artist_surname_firstname").add(Util.isValidDataText(surname) || Util.isValidDataText(forname)? String.format("%s %s", surname, forname) : "");
		}        	        
	}  

	static enum producent_type {
		trykker, udgiver, forfatterredaktr, undefined;

		public static producent_type toString(String str)
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
