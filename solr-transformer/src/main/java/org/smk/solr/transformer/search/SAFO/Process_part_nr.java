package org.smk.solr.transformer.search.SAFO;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_part_nr{

	protected final static Logger log = Logger .getLogger(Process_producents.class);
	
	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {										
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_part_nr - id:%s", (String) row.get("id")));
		
		String id = ((String) row.get("id")); 			
		// get second part of id (after "/")
		String rank = id.split("/").length < 2 ? "0" : id.split("/")[1];
		// remove all non numeric char (e.g. "verso")
		rank = rank.replaceAll("\\D+", "");
		
		if (rank != "0")			
			row.put("part_nr", Integer.parseInt(rank));					

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_part_nr - id:%s\r\n--------------", (String) row.get("id")));
		
		return row;

	}	
}
