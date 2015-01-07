package org.smk.solr.transformer.fullexport;

import java.util.Map;

import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Process_producents;
import org.smk.solr.transformer.generic.Util;

public class Process_capitalize {

	protected final static Logger log = Logger .getLogger(Process_producents.class);
	
	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_capitalize - id:%s", (String) row.get("id")));
		
		String acq_method = (String)row.get("acq_method");
		String acq_source = (String)row.get("acq_source");
		
		if (Util.isValidDataText(acq_method))
			row.put("acq_method", Util.firstUpper(acq_method));
		
		if (Util.isValidDataText(acq_source))
			row.put("acq_source", Util.firstUpper(acq_source));
					
		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_capitalize - id:%s\r\n--------------", (String) row.get("id")));
		
		return row; 
	}	
}
