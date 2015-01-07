package org.smk.solr.transformer.fullexport;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_materiale{

	protected final static Logger log = Logger .getLogger(Process_materiale.class);

	/**	 
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row){
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_materiale - csid:%s", (String) row.get("csid")));

		if ((String)row.get("materiale") == null)
			return row;

		String[] materiale_split = ((String) row.get("materiale")).split(Util.split_1_niv);                      				
		int arrayLength = materiale_split.length;        

		ArrayList<String> materiales_data = new ArrayList<String> ();
		ArrayList<String> materiales_data_en = new ArrayList<String> ();

		for(int i = 0; i < arrayLength; i++) {         
			String[] values = materiale_split[i].split(Util.split_2_niv);			         			      					
			String mat = Util.getValueFromSplit(values, 0);
			String mat_en = Util.getValueFromSplit(values, 3);
			String type = Util.getValueFromSplit(values, 2);	

			if(Util.isValidDataText(mat))
				materiales_data.add(String.format("%s%s%s", mat, Util.split_2_niv ,type));
			
			if(Util.isValidDataText(mat_en))
				materiales_data_en.add(String.format("%s%s%s", mat_en, Util.split_2_niv ,type));
		}

		row.remove("materiale");

		if (materiales_data.size() > 0)        
			row.put("materiale", StringUtils.join(materiales_data, Util.split_1_niv)); 
		
		if (materiales_data_en.size() > 0)        
			row.put("materiale_en", StringUtils.join(materiales_data_en, Util.split_1_niv)); 

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_materiale - csid:%s\r\n--------------", (String) row.get("csid")));

		return row;		

	}		
}
