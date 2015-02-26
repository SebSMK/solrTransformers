package org.smk.solr.transformer.enb;

import java.util.Map;

import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_enb_clean {

	protected final static Logger log = Logger .getLogger(Process_enb_clean.class);
	
	/**
	 * 
	 **/
	public Object transformRow(Map<String, Object> row) {
		if(log.isDebugEnabled()){
			log.debug(String.format("--------\r\nstart Process_enb_clean - id:%s", (String) row.get("id")));
		}
		String ENBStatus = (String)row.get("sikkerhedstatus");
		if(log.isDebugEnabled()){
			log.debug("ENBStatus: " + ENBStatus);
		}
		if (ENBStatus != null && (ENBStatus.equals("ENB-værk") || ENBStatus.equals("NB-værk"))){
			row.put("ENBStatus", ENBStatus);
			if(log.isDebugEnabled()){
				log.debug("remove ENBStatus");
			}
		}
		if(log.isDebugEnabled()){
			log.debug(String.format("finish Process_enb_clean - id:%s\r\n--------------", (String) row.get("id")));
		}
		return row;
	}
}
