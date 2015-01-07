package org.smk.solr.transformer.generic.fullexport;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.smk.solr.transformer.fullexport.*;

public class FullExport_Test {	

	@Test
	public void test_Process_titles(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "De tyske byers underkastelse;--;Titel oversat fra Veldman (The New Hollstein);--;urn:cspace:smk.dk:vocabularies:name(languages):item:name(dansk)'dansk';--;trans_eng;-v;engelsk;-;The submission of the German cities;--;Eng. titel som hos Veldman (The New Hollstein);--;urn:cspace:smk.dk:vocabularies:name(languages):item:name(engelsk)'engelsk';--;;-v;;-;Kejser Karl V's sejre;--;Titel oversat fra Veldman (The New Hollstein);--;urn:cspace:smk.dk:vocabularies:name(languages):item:name(dansk)'dansk';--;trans_eng;-v;engelsk;-;The victories of Emperor Charles V;--;Eng. titel som hos Veldman (The New Hollstein);--;urn:cspace:smk.dk:vocabularies:name(languages):item:name(engelsk)'engelsk';--;;-v;";
		row.put("title_all", teststring);
		Process_titles transf = new Process_titles();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		String title_first = ((String) rowmodif.get("title_first"));		
		org.junit.Assert.assertEquals("De tyske byers underkastelse", title_first);			
		String title_eng = ((String) rowmodif.get("title_eng"));		
		org.junit.Assert.assertEquals("trans_eng", title_eng);	
				
		teststring = "Udklip på papir med kvindetegnet;--;;--;urn:cspace:smk.dk:vocabularies:name(languages):item:name(dansk)'dansk';--;";
		row.put("title_all", teststring);
		transf = new Process_titles();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		title_first = ((String) rowmodif.get("title_first"));		
		org.junit.Assert.assertEquals("Udklip på papir med kvindetegnet", title_first);
		String title_dk = ((String) rowmodif.get("title_dk"));		
		org.junit.Assert.assertEquals("Udklip på papir med kvindetegnet", title_dk);				
	}
	
	@Test
	public void test_Process_capitalize(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "åben";
		row.put("acq_source", teststring);
		teststring = "ødsel";
		row.put("acq_method", teststring);
		Process_capitalize transf = new Process_capitalize();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		String source = ((String) rowmodif.get("acq_source"));		
		org.junit.Assert.assertEquals("Åben", source);
		String method = ((String) rowmodif.get("acq_method"));		
		org.junit.Assert.assertEquals("Ødsel", method);
	}
	
	@Test
	public void test_Process_prod_place(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "paris;--;udgivelsessted;-;berlin;--;udførelsessted;-;london;--;kouk;-;rome;--;udførelsessted";
		row.put("object_production_place", teststring);
		Process_production_place transf = new Process_production_place();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		String udgivet = ((String) rowmodif.get("object_production_place_udgivet"));		
		org.junit.Assert.assertEquals("paris", udgivet);
		String udfoert = ((String) rowmodif.get("object_production_place_udfoert"));		
		org.junit.Assert.assertEquals("berlin;-;rome", udfoert);
	}
	
	@Test
	public void test_Process_prod_date(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "udført;--;Efter 1900 ;--;1900-01-01 00:00:00;--;2000-01-01 00:00:00;--;After 1900 ;--;1900-01-01 00:00:00;--;2000-01-01 00:00:00;-;udgivet;--;Ca. 1800 ;--;1798-01-01 00:00:00;--;1802-01-01 00:00:00;--;Circa 1800 ;--;1798-01-01 00:00:00;--;1802-01-01 00:00:00;-;verk datering;--;1588-1590;--;1588-01-01 01:00:00;--;1590-12-31 01:00:00;--;1588-1590;--;1588-01-01 01:00:00;--;1590-12-31 01:00:00";
		row.put("object_all_production_dates", teststring);
		Process_production_date transf = new Process_production_date();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		String date = ((String) rowmodif.get("object_production_date"));		
		org.junit.Assert.assertEquals("1588-1590", date);
		String date_en = ((String) rowmodif.get("object_production_date_eng"));		
		org.junit.Assert.assertEquals("1588-1590", date_en);
		String udgivet = ((String) rowmodif.get("object_production_date_udgivet"));		
		org.junit.Assert.assertEquals("Ca. 1800 ", udgivet);
		String udfoert = ((String) rowmodif.get("object_production_date_udfoert"));		
		org.junit.Assert.assertEquals("Efter 1900 ", udfoert);	
		
	}
	
	@Test
	public void test_Process_techniques(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "olie;--;xxxengelsk;-;træ;--;hhhdansk;-;;--;dansk";
		row.put("prod_technique_all", teststring);
		Process_techniques transf = new Process_techniques();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		String res = ((String) rowmodif.get("prod_technique"));		
		org.junit.Assert.assertEquals("Træ", res);
		res = ((String) rowmodif.get("prod_technique_eng"));		
		org.junit.Assert.assertEquals("Olie", res);		
	}
	
	@Test
	public void test_Process_dimensions(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "diameter;--;239;--;cm;--;307;--;cm;--;;--;;--;20;--;cm;--;10;--;kg;-;vaegt;--;239;--;cm;--;307;--;cm;--;;--;;--;;--;;--;10;--;kg;-;netto;--;239;--;cm;--;307;--;cm;--;;--;;--;;--;;--;;--;;-;plademaal;--;23;--;cm;--;30;--;cm;";
		row.put("meas_all", teststring);
		Process_dimensions transf = new Process_dimensions();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		String res = ((String) rowmodif.get("dimension_netto"));		
		org.junit.Assert.assertEquals("239 x 307 cm", res);
		res = ((String) rowmodif.get("dimension_plademaal"));		
		org.junit.Assert.assertEquals("23 x 30 cm", res);	
		res = ((String) rowmodif.get("dimension_weight"));		
		org.junit.Assert.assertEquals("10 kg", res);
		res = ((String) rowmodif.get("dimension_diameter"));		
		org.junit.Assert.assertEquals("20 cm", res);
	}
	
	@Test
	public void test_Process_inscriptions(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "trykt tekst;--;f;--;;--;;--;Ovenfor billedet til højre: 74.;--;;--;;--;;-;samlermærke;--;t;--;;--;;--;Det Danske Kunstindustrimuseum, jf. Lugt 701;--;;--;;--;;-;trykt tekst;--;f;--;;--;;--;F.n.t.v.:C. D. Gebauer pinx 1813\"; t.h. herfor: \"I. W. Tegner & Kittendorff lith. Inst.\"; herunder: \"Kosakker og Baschkirer / i Nærheden af Dresden / Maleriet 17-13 1/2\" \"; Herunder blindstempel: \"J. W. Tegner & Kittendorff / Kjøbenhavn / Litographisk Institut\";--;;--;;--;;-;signatur;--;f;--;f.n.t.h.;--;;--;\"Ad. Kiffendorff lith.\";--;;--;;--;";
		row.put("inscription_data", teststring);
		Process_inscriptions transf = new Process_inscriptions();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		String res = ((String) rowmodif.get("inscription_samlermaerke"));		
		org.junit.Assert.assertEquals("Det Danske Kunstindustrimuseum, jf. Lugt 701", res);
		res = ((String) rowmodif.get("inscription_trykttekst"));		
		org.junit.Assert.assertEquals(": Ovenfor billedet til højre: 74.;-;: F.n.t.v.:C. D. Gebauer pinx 1813\"; t.h. herfor: \"I. W. Tegner & Kittendorff lith. Inst.\"; herunder: \"Kosakker og Baschkirer / i Nærheden af Dresden / Maleriet 17-13 1/2\" \"; Herunder blindstempel: \"J. W. Tegner & Kittendorff / Kjøbenhavn / Litographisk Institut\"", res);
		res = ((String) rowmodif.get("inscription_signatur"));		
		org.junit.Assert.assertEquals("f.n.t.h.: \"Ad. Kiffendorff lith.\"", res);
	}
	
	@Test
	public void test_Process_other_numbers(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "Andet inv. nr.;--;381 c; KAS381/3;-;Beckettnr;--;419;-;Fotonummer;--;16034;-;Gernsheim 1;--;81973";
		row.put("other_numbers", teststring);
		Process_other_numbers transf = new Process_other_numbers();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		String res = ((String) rowmodif.get("other_numbers_andet_inventar"));		
		org.junit.Assert.assertEquals("381 c; KAS381/3", res);
		res = ((String) rowmodif.get("other_numbers_beckett"));		
		org.junit.Assert.assertEquals("419", res);
		res = ((String) rowmodif.get("foto_nr"));		
		org.junit.Assert.assertEquals("16034", res);
		res = ((String) rowmodif.get("other_numbers_gernsheim"));		
		org.junit.Assert.assertEquals("81973", res);
	}
	
	
	@Test
	public void test_Process_materiale(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "lærred;--;Materiale;--;bærende underlag (material);--;canvas;-;olie;--;Medium;--;farvelag (medium);--;oil";
		row.put("materiale", teststring);
		Process_materiale transf = new Process_materiale();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		String res = ((String) rowmodif.get("materiale"));		
		org.junit.Assert.assertEquals("lærred;--;bærende underlag (material);-;olie;--;farvelag (medium)", res);
		res = ((String) rowmodif.get("materiale_en"));		
		org.junit.Assert.assertEquals("canvas;--;bærende underlag (material);-;oil;--;farvelag (medium)", res);
		
	}
		
	
}
