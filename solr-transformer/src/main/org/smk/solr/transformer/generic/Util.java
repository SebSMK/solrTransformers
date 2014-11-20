package org.smk.solr.transformer.generic;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

public class Util {	
	
	public static String codepoints(String str) {
		if (str == null)
			return "";

		ArrayList res = new ArrayList();
		for(int i = 0; i < str.length(); i++) {	      		
			String chr =  str.substring(i, i + 1);
			int codepoint = Character.codePointAt(str, i);
			res.add((codepoint >= 33 && codepoint <= 126) ?
					chr :
						"U+" + Integer.toHexString(codepoint).toUpperCase());      		
		};
		return StringUtils.join(res, ""); //.replaceAll("/"/g", "");
	}

	public static boolean isValidDataText(String text){
		return isValidDataText(text, "def");
	}

	public static boolean isValidDataText(String text, String field){
		if (text == null)
			return false;
		text = text.toLowerCase();

		dataType field_expr = dataType.toString(field);
		switch(field_expr) {
		case role:
			if(text == "undefined"
			|| text == "null" 
			|| text == ""
			|| text == "original")
				return false;
			break;              
		case agent:
			if(text == "undefined"
			|| text == "null" 
			|| text == ""
			|| text == "unknown")
				return false;
			break;
		case date:
			if(text == "undefined"
			|| text == "null" 
			|| text == ""
			|| text == "(?)")
				return false;
			break;
		case orga_place:
			if(text == "undefined"
			|| text == "null" 
			|| text == ""
			|| text == "(?)")
				return false;
			break;
		case undefined:
		default:
			if(text == "undefined" 
			|| text == "null"
			|| text == "")
				return false;
		} 
		return true;
	}       

	/**
	 * Return true if $data matches a person, not an organisation  
	 **/		
	
	public static boolean isPersonData(String[] personArray){ 
		return isPersonData(personArray, "def");
	}  
	
	public static boolean isPersonData(String[] personArray, String type){
		producentType field_expr = producentType.toString(type);

		switch(field_expr) {
		case producent:
			if (personArray != null 
			&& personArray.length > 0
			&& personArray[0] == "person")
				return true; 
			break;       
		case undefined:
		default:
			return false;
		}

		return false;
	};  
	
	public final static String split_1_niv = new String(";-;");
	public final static String split_2_niv = new String(";--;");
	public final static String split_3_niv = new String(";---;");
	public final static String split_4_niv = new String(";-v;");
	
	static enum producentType {
		producent, 		
		undefined;
		
		public static producentType toString(String str)
	    {
	        try {
	            return valueOf(str);
	        } 
	        catch (Exception ex) {
	            return undefined;
	        }
	    }   
	}
	
	static enum dataType {
		role, agent, date, orga_place, 		
		undefined;
		
		public static dataType toString(String str)
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
