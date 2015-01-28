package org.smk.solr.transformer.search.SAFO.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.smk.solr.transformer.search.SAFO.*;

public class SAFO_Test {	
	
	@Test
	public void test_Process_first(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		ArrayList<String> teststring = new ArrayList<String>();
		teststring.add(" åertrude Markus");
		teststring.add("aertrude Markus");
		row.put("artist_name", teststring);
		Process_first_producent transf = new Process_first_producent();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		org.junit.Assert.assertEquals("ÅERTRUDE MARKUS", rowmodif.get("artist_first_name"));
	}
}
