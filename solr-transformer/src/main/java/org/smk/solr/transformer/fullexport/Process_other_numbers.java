package org.smk.solr.transformer.fullexport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_other_numbers{

	protected final static Logger log = Logger .getLogger(Process_other_numbers.class);

	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_other_numbers - id:%s", (String) row.get("id")));

		if ((String)row.get("other_numbers" ) == null)
			return row;

		String[] oth_numb_split = ((String) row.get("other_numbers")).split(Util.split_1_niv);                      				
		int arrayLength = oth_numb_split.length;        

		ArrayList<String>  andet_inventar = new ArrayList<String> ();
		ArrayList<String>  beckett = new ArrayList<String> ();
		ArrayList<String>  gernsheim = new ArrayList<String> ();
		ArrayList<String>  vaerkfortegn = new ArrayList<String> ();
		ArrayList<String>  tidligere = new ArrayList<String> ();
		ArrayList<String>  bladnummer = new ArrayList<String> ();	
		ArrayList<String>  sidetal = new ArrayList<String> ();
		ArrayList<String>  foto = new ArrayList<String> ();		

		for(int i = 0; i < arrayLength; i++) {         
			String[] values = oth_numb_split[i].split(Util.split_2_niv);			         			      		
			String oth_numb_type = Util.getValueFromSplit(values, 0);
			String num = Util.getValueFromSplit(values, 1);

			if(Util.isValidDataText(num)){
				switch(otherNumbersType.toString(oth_numb_type.replaceAll("[^A-Za-z0-9]", "").toLowerCase())) {

				case andetinvnr:
					andet_inventar.add(num);
					break;
				case beckettnr:
					beckett.add(num);
					break;             
				case gernsheim1:
					gernsheim.add(num);
					break;
				case vrkfortegnelsesnr:
					vaerkfortegn.add(num);
					break;
				case tidligereplacering:
					tidligere.add(num);
					break;  
				case bladnummer:
					bladnummer.add(num);
					break;  
				case sidetal:
					sidetal.add(num);
					break;              
				case fotonummer:
					foto.add(num);
					break;                  						
				}    
			}           					
		}                                                                                                        

		if (andet_inventar.size() > 0)			
			row.put("other_numbers_andet_inventar", StringUtils.join(andet_inventar, Util.split_1_niv));			

		if (beckett.size() > 0)			
			row.put("other_numbers_beckett", StringUtils.join(beckett, Util.split_1_niv));			

		if (gernsheim.size() > 0)			
			row.put("other_numbers_gernsheim", StringUtils.join(gernsheim, Util.split_1_niv));			

		if (vaerkfortegn.size() > 0)			
			row.put("other_numbers_vaerkfortegn", StringUtils.join(vaerkfortegn, Util.split_1_niv));			

		if (tidligere.size() > 0)			
			row.put("other_numbers_tidligere", StringUtils.join(tidligere, Util.split_1_niv));			

		if (bladnummer.size() > 0)			
			row.put("other_numbers_bladnummer", StringUtils.join(bladnummer, Util.split_1_niv));			

		if (sidetal.size() > 0)			
			row.put("other_numbers_sidetal", StringUtils.join(sidetal, Util.split_1_niv));	

		if (foto.size() > 0)			
			row.put("foto_nr", StringUtils.join(foto, Util.split_1_niv));	

		row.remove("other_numbers"); 

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_other_numbers - id:%s\r\n--------------", (String) row.get("id")));

		return row;		

	}		

	static enum otherNumbersType {
		andetinvnr, 
		beckettnr, 
		gernsheim1, 
		vrkfortegnelsesnr,
		tidligereplacering,
		bladnummer,
		sidetal, 
		fotonummer,
		undefined;	

		public static otherNumbersType toString(String str)
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
