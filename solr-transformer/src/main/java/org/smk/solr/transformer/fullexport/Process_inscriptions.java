package org.smk.solr.transformer.fullexport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_inscriptions{

	protected final static Logger log = Logger .getLogger(Process_inscriptions.class);

	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_inscriptions - id:%s", (String) row.get("id")));

		if ((String)row.get("inscription_data" ) == null)
			return row;

		String[] inscriptions_split = ((String) row.get("inscription_data")).split(Util.split_1_niv);                      				
		int arrayLength = inscriptions_split.length;        

		ArrayList<String>  signatur = new ArrayList<String> ();
		ArrayList<String>  stoebemaerke = new ArrayList<String> ();
		ArrayList<String>  tryktsignatur = new ArrayList<String> ();
		ArrayList<String>  stoebenummer = new ArrayList<String> ();
		ArrayList<String>  trykttekst = new ArrayList<String> ();
		ArrayList<String>  samlermaerke = new ArrayList<String> ();	
		ArrayList<String>  paaskrift = new ArrayList<String> ();

		for(int i = 0; i < arrayLength; i++) {         
			String[] values = inscriptions_split[i].split(Util.split_2_niv);			         			      		
			String inscription_type = new String(values[0]);
			String sign = new String();
			
			switch(inscriptionsType.toString(inscription_type.replaceAll("[^A-Za-z0-9]", ""))) {

			case signatur:
				sign = concat_inscription_data(values);                
				if(Util.isValidDataText(sign))
					signatur.add(sign);
				break;
			case pskrift:
				sign = concat_inscription_data(values);                
				if(Util.isValidDataText(sign))
					paaskrift.add(sign);
				break;             
			case tryktsignatur:
				sign = concat_inscription_data(values);                
				if(Util.isValidDataText(sign))
					tryktsignatur.add(sign);
				break;
			case trykttekst:
				sign = concat_inscription_data(values);                
				if(Util.isValidDataText(sign))
					trykttekst.add(sign);
				break;  
			case stbenummer:
				sign = concat_inscription_data_inhold(values);                
				if(Util.isValidDataText(sign))
					stoebenummer.add(sign);
				break;  
			case stbemrke:
				sign = concat_inscription_data_inhold(values);                
				if(Util.isValidDataText(sign))
					stoebemaerke.add(sign);
				break;              
			case samlermrke:
				sign = concat_inscription_data_inhold(values);                
				if(Util.isValidDataText(sign))
					samlermaerke.add(sign);
				break;                  						
			} 
		}                                                                                                        

		if (signatur.size() > 0)			
			row.put("inscription_signatur", StringUtils.join(signatur, Util.split_1_niv));			

		if (stoebemaerke.size() > 0)			
			row.put("inscription_stoebemaerke", StringUtils.join(stoebemaerke, Util.split_1_niv));			

		if (tryktsignatur.size() > 0)			
			row.put("inscription_tryktsignatur", StringUtils.join(tryktsignatur, Util.split_1_niv));			

		if (paaskrift.size() > 0)			
			row.put("inscription_paaskrift", StringUtils.join(paaskrift, Util.split_1_niv));			

		if (stoebenummer.size() > 0)			
			row.put("inscription_stoebenummer", StringUtils.join(stoebenummer, Util.split_1_niv));			

		if (trykttekst.size() > 0)			
			row.put("inscription_trykttekst", StringUtils.join(trykttekst, Util.split_1_niv));			

		if (samlermaerke.size() > 0)			
			row.put("inscription_samlermaerke", StringUtils.join(samlermaerke, Util.split_1_niv));					

		row.remove("inscription_data"); 

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_inscriptions - id:%s\r\n--------------", (String) row.get("id")));

		return row;		

	}	

	/**
	 * Concat inscriptions data
	 * */
	private String concat_inscription_data(String[] values){    		          
		String verso = Util.getValueFromSplit(values, 1) != null && Util.getValueFromSplit(values, 1).equals("t") ? String.format("%s: ", "verso") : "";
		String placering = Util.getValueFromSplit(values, 2) != null ? String.format("%s", Util.getValueFromSplit(values, 2)) : "";
		String maade = Util.getValueFromSplit(values, 3) != null ? String.format(": %s", Util.getValueFromSplit(values, 3)) : "";        
		String indhold = Util.getValueFromSplit(values, 4) != null ? String.format(": %s", Util.getValueFromSplit(values, 4)) : "";
		String dansk = Util.getValueFromSplit(values, 5) != null? String.format(": %s", Util.getValueFromSplit(values, 5)) : "";
		String note = Util.getValueFromSplit(values, 6) != null ? String.format(": %s", Util.getValueFromSplit(values, 6)) : "";
		String datering = Util.getValueFromSplit(values, 7) != null ? String.format(": %s", Util.getValueFromSplit(values, 7)) : "";

		return  Util.isValidDataText(verso) || 
				Util.isValidDataText(placering) ||
				Util.isValidDataText(maade) ||
				Util.isValidDataText(datering) ||
				Util.isValidDataText(indhold) ||
				Util.isValidDataText(dansk) ||
				Util.isValidDataText(note) ?

						String.format("%s%s%s%s%s%s%s",
								verso,
								placering,                         
								maade,
								datering,                             
								indhold,                           
								dansk,                             
								note )
								:
									"";                                       
	}

	/**
	 * Concat inscriptions data - just inhold
	 * */
	private String concat_inscription_data_inhold(String[] values){
		return  Util.getValueFromSplit(values, 4) != null ? Util.getValueFromSplit(values, 4) : "";                                
	}      

	static enum inscriptionsType {
		signatur, 
		pskrift, 
		tryktsignatur, 
		trykttekst,
		stbenummer,
		stbemrke,
		samlermrke, 
		undefined;

		public static inscriptionsType toString(String str)
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
