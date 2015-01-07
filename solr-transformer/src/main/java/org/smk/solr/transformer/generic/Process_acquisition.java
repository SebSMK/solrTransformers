package org.smk.solr.transformer.generic;

import java.util.Map;

import org.apache.log4j.Logger;

public class Process_acquisition {

	protected final static Logger log = Logger .getLogger(Process_producents.class);
	
	/**
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row) {
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_acquisition - id:%s", (String) row.get("id")));
		
		String acq_method = (String)row.get("acq_method");
		String acq_source = (String)row.get("acq_source");
		String acq_date = (String)row.get("acq_date");
		String acq_date_eng = (String)row.get("acq_date_eng");
		
		String acq_for_skilt = Util.isValidDataText(acq_source) || Util.isValidDataText(acq_source) || Util.isValidDataText(acq_date) ?
	        	String.format(";<1-;%s%s%s;-1>;%s",
		        	Util.isValidDataText(acq_method) ? String.format("%s", acq_method) : "",
		        	Util.isValidDataText(acq_source) ? String.format(" %s", acq_source) : "",
		        	Util.isValidDataText(acq_date) ? String.format(" %s", acq_date) : "",
		        	Util.isValidDataText(acq_date_eng) ? String.format(" / Acquired %s", acq_date_eng) : ""
		         )
		     :
		     	"";  
		     	
		String acq_for_ext = Util.isValidDataText(acq_source) || Util.isValidDataText(acq_source) || Util.isValidDataText(acq_date) ?
				String.format("%s%s%s",
		        	Util.isValidDataText(acq_method) ? String.format("%s", acq_method) : "",
		        	Util.isValidDataText(acq_date) ? String.format(" (%s)", acq_date) : "",
		        	Util.isValidDataText(acq_source) ? String.format(", %s", acq_source) : ""
		         )
		     :
		     	"";  	        
        
        if (Util.isValidDataText(acq_for_skilt))
        	row.put("acq_for_skilt", acq_for_skilt);
        	
       	if (Util.isValidDataText(acq_for_ext))
        	row.put("acq_for_ext", acq_for_ext);
				
		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_acquisition - id:%s\r\n--------------", (String) row.get("id")));
		
		return row; 
	}	
}
