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
		teststring = new ArrayList<String>();
		teststring.add(" Markus åertrude");
		teststring.add("Markus aertrude ");
		row.put("artist_surname_firstname", teststring);
		Process_first_producent transf = new Process_first_producent();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		org.junit.Assert.assertEquals("ÅERTRUDE MARKUS", rowmodif.get("artist_first_name"));
		org.junit.Assert.assertEquals("MARKUS ÅERTRUDE", rowmodif.get("artist_first_surname_firstname"));		
	}
	
	@Test
	public void test_Process_part_nr(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "kms1/5";
		row.put("id", teststring);
		Process_part_nr transf = new Process_part_nr();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		org.junit.Assert.assertEquals(5, rowmodif.get("part_nr"));
		
		row = new HashMap();
		rowmodif = new HashMap();
		teststring = "kms1";
		row.put("id", teststring);
		transf = new Process_part_nr();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		org.junit.Assert.assertNull(rowmodif.get("part_nr"));
		
		row = new HashMap();
		rowmodif = new HashMap();
		teststring = "kms1/3 verso";
		row.put("id", teststring);
		transf = new Process_part_nr();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		org.junit.Assert.assertEquals(3, rowmodif.get("part_nr"));
	}
	
	@Test
	public void test_Process_materiale(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "lærred;--;bærende underlag (material);-;olie;--;farvelag (medium);-;papir;--;farvelag (medium)";		
		row.put("materiale", teststring);
		Process_materiale transf = new Process_materiale();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		ArrayList<String> res = ((ArrayList<String>) rowmodif.get("materiale"));		
		org.junit.Assert.assertEquals("lærred;--;bærende underlag (material)", (String)res.get(0));
		org.junit.Assert.assertEquals("olie;--;farvelag (medium)", (String)res.get(1));
		
		row = new HashMap();
		teststring = "lærred;--;bærende underlag (material);-;olie;--;farvelag (medium)";
		row.put("materiale_en", teststring);
		transf = new Process_materiale();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		res = ((ArrayList<String>) rowmodif.get("materiale_en"));		
		org.junit.Assert.assertEquals("lærred;--;bærende underlag (material)", (String)res.get(0));
		org.junit.Assert.assertEquals("olie;--;farvelag (medium)", (String)res.get(1));		
	}
	
	@Test
	public void test_Process_portrait(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "Christian VI;--;;--;Christian VI;-;Adolph Hans Holsten;--;Adolph Hans;--;Holsten;--;";
		row.put("portrait_person", teststring);
		Process_portrait transf = new Process_portrait();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		ArrayList<String> portrait_person = ((ArrayList<String>) rowmodif.get("portrait_person"));		
		org.junit.Assert.assertEquals("Christian VI", (String)portrait_person.get(0));
		org.junit.Assert.assertEquals("Adolph Hans Holsten", (String)portrait_person.get(1));
		
		ArrayList<String> portrait_person_surname = ((ArrayList<String>) rowmodif.get("portrait_person_surname_firstname"));		
		org.junit.Assert.assertEquals("Christian VI ", (String)portrait_person_surname.get(0));
		org.junit.Assert.assertEquals("Holsten Adolph Hans", (String)portrait_person_surname.get(1));	
	}
	
	@Test
	public void test_Process_motiv(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "Frankrig;-;Paris";
		row.put("topografisk_motiv", teststring);
		Process_topo_motiv transf = new Process_topo_motiv();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		ArrayList<String> res = ((ArrayList<String>) rowmodif.get("topografisk_motiv"));		
		org.junit.Assert.assertEquals("Frankrig", (String)res.get(0));
		org.junit.Assert.assertEquals("Paris", (String)res.get(1));						
	}
	
	@Test
	public void test_Process_vaerkstatus(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "delværk;-;forlæg (for)";
		row.put("vaerkstatus", teststring);
		Process_vaerkstatus transf = new Process_vaerkstatus();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		ArrayList<String> res = ((ArrayList<String>) rowmodif.get("vaerkstatus"));		
		org.junit.Assert.assertEquals("delværk", (String)res.get(0));
		org.junit.Assert.assertEquals("forlæg (for)", (String)res.get(1));						
	} 
}
