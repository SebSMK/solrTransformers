package org.smk.solr.transformer.enb;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.smk.solr.transformer.fullexport.*;

public class Enb_Test {	

	@Test
	public void test_Process_enb_clean_a(){
		
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		
		String id = "KMSa";
		row.put("id", id);
		String ENBStatus = "ENB-værk";
		row.put("sikkerhedstatus", ENBStatus);
		
		Process_enb_clean transf = new Process_enb_clean();
		
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		
		/*status should NOT be removed if ENB or NB*/
		String res = ((String) rowmodif.get("ENBStatus"));
		org.junit.Assert.assertEquals("ENB-værk", res);
		
		res = ((String) rowmodif.get("id"));
		org.junit.Assert.assertEquals("KMSa", res);
		
	}
	
	@Test
	public void test_Process_enb_clean_b(){
		
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		
		String id = "KMSa";
		row.put("id", id);
		String ENBStatus = "Må updeponeres";
		row.put("sikkerhedstatus", ENBStatus);
		
		Process_enb_clean transf = new Process_enb_clean();
		
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		
		/*status should be removed if not ENB or NB*/
		String res = ((String) rowmodif.get("ENBStatus"));
		org.junit.Assert.assertEquals(null, res);
		
		/*nothing else should be touched*/
		res = ((String) rowmodif.get("id"));
		org.junit.Assert.assertEquals("KMSa", res);
	}	
}
