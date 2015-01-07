package org.smk.solr.transformer.fullexport;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_techniques{

	protected final static Logger log = Logger .getLogger(Process_techniques.class);

	/**	 
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row){
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_techniques - csid:%s", (String) row.get("csid")));

		if ((String)row.get("prod_technique_all") == null)
			return row;

		String[] techniques_split = ((String) row.get("prod_technique_all")).split(Util.split_1_niv);                      				
		int arrayLength = techniques_split.length;        

		ArrayList<String> techniques_data = new ArrayList<String> ();	
		ArrayList<String> techniques_data_eng = new ArrayList<String> ();	

		for(int i = 0; i < arrayLength; i++) {         			
			String[] values = techniques_split[i].split(Util.split_2_niv);			         			      					
			String technique = Util.firstUpper(Util.getValueFromSplit(values, 0));
			String lang = Util.getValueFromSplit(values, 1);	

			if (Util.isValidDataText(technique)){
				if(lang != null && lang.indexOf("dansk") > -1)				
					techniques_data.add(technique);

				if(lang != null && lang.indexOf("engelsk") > -1)				
					techniques_data_eng.add(technique);
			}
		}

		if (techniques_data.size() > 0)        
			row.put("prod_technique", StringUtils.join(techniques_data, Util.split_1_niv)); 

		if (techniques_data_eng.size() > 0)        
			row.put("prod_technique_eng", StringUtils.join(techniques_data_eng, Util.split_1_niv)); 

		row.remove("prod_technique_all");

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_techniques - csid:%s\r\n--------------", (String) row.get("csid")));

		return row;		

	}		
}
