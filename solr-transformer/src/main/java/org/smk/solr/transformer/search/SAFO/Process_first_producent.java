package org.smk.solr.transformer.search.SAFO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_first_producent{

	protected final static Logger log = Logger .getLogger(Process_first_producent.class);

	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_first_producent - id:%s", (String) row.get("id")));

		if (row.get("artist_name") == null)
			return row;

		ArrayList<String> artists = ((ArrayList<String>) row.get("artist_name"));  				
		
		if( artists.size() > 0 && Util.isValidDataText(artists.get(0)))
			row.put("artist_first_name", artists.get(0).trim().toUpperCase());
						
		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_first_producent - id:%s\r\n--------------", (String) row.get("id")));
		
		return row;		
	}	
}
