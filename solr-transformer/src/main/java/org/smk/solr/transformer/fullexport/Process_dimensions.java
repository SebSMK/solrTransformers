package org.smk.solr.transformer.fullexport;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.smk.solr.transformer.generic.Util;

public class Process_dimensions{

	protected final static Logger log = Logger .getLogger(Process_dimensions.class);

	/**	 
	 * 
	 **/            
	public Object transformRow(Map<String, Object> row){
		if(log.isDebugEnabled())
			log.debug(String.format("--------\r\nstart Process_dimensions - csid:%s", (String) row.get("csid")));

		if ((String)row.get("meas_all") == null)
			return row;

		String[] dates_split = ((String) row.get("meas_all")).split(Util.split_1_niv);                      				
		int arrayLength = dates_split.length;  			        

		for(int i = 0; i < arrayLength; i++) {         
			String[] values = dates_split[i].split(Util.split_2_niv);
			String dimension_type = Util.getValueFromSplit(values, 0);                         
			String dimension_datas = concat_dimension_datas(values);
			String dimension_weight_datas = concat_weight_datas(values);
			String dimension_diameter_datas = concat_diameter_datas(values);

			//* there should be only one dimension of each type - if there are more than that, only the last one is taken into account 
			if (Util.isValidDataText(dimension_datas))
				row.put("dimension_" + dimension_type, dimension_datas);

			if (Util.isValidDataText(dimension_weight_datas))
				row.put("dimension_weight", dimension_weight_datas);

			if (Util.isValidDataText(dimension_diameter_datas))
				row.put("dimension_diameter", dimension_diameter_datas);  
		}		

		row.remove("meas_all"); 

		if(log.isDebugEnabled())
			log.debug(String.format("finish Process_dimensions - csid:%s\r\n--------------", (String) row.get("csid")));

		return row;		

	}	

	/**
	 * Concat weight data
	 * */
	private String concat_weight_datas(String[] values){
		String weight = Util.getValueFromSplit(values, 9);
		String weight_unit = Util.getValueFromSplit(values, 10);
		return Util.isValidDataText(weight) ? String.format("%s %s", weight, weight_unit): "";        
	};

	/**
	 * Concat diameter data
	 * */
	private String concat_diameter_datas(String[] values){ 
		String diameter = Util.getValueFromSplit(values, 7);
		String diameter_unit = Util.getValueFromSplit(values, 8);
		return Util.isValidDataText(diameter) && Util.isValidDataText(diameter_unit) ? String.format("%s %s", diameter, diameter_unit): "";
	};

	/**
	 * Concat dimensions data
	 * */
	private String concat_dimension_datas(String[] values){
		String hoj_unit_value = !Util.getValueFromSplit(values, 2).equals("") ? Util.getValueFromSplit(values, 2) : "(?)";
		String hoj = Util.getValueFromSplit(values, 1);
		String hoj_value = Util.isValidDataText(hoj) ? String.format("%s", hoj): "";		
		String bred = Util.getValueFromSplit(values, 3);
		String bred_value = Util.isValidDataText(bred) ? String.format(" x %s", bred) : "";
		String dyb = Util.getValueFromSplit(values, 5);
		String dyb_value = Util.isValidDataText(dyb) ? String.format(" x %s", dyb) : "";

		return Util.isValidDataText(hoj_value) || Util.isValidDataText(bred_value) || Util.isValidDataText(dyb_value) ? String.format("%s%s%s %s", hoj_value, bred_value, dyb_value, hoj_unit_value) : "";
	};
}
