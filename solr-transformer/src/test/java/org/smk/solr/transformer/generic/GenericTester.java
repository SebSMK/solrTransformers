import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import org.smk.solr.transformer.generic.*;

public class GenericTester {

	@Test
	public void test_Util_codepoint(){
		String test = "udf�rt";
		String expected = "udfU+F8rt";
		String res = Util.codepoints(test);
		org.junit.Assert.assertEquals(res, expected);

	}

	@Test
	public void test_Util_isValidDataText(){
		String test = "coco";
		Boolean res = Util.isValidDataText(test);
		org.junit.Assert.assertTrue(res);
		test = "original";
		res = Util.isValidDataText(test, "role");
		org.junit.Assert.assertTrue(!res);

	}

	@Test
	public void test_Util_isPersonData(){
		String[] test = {"person", "2", "frfr"};
		Boolean res = Util.isPersonData(test);
		org.junit.Assert.assertTrue(!res);
		test = new String[]{"original"};
		res = Util.isPersonData(test, "producent");
		org.junit.Assert.assertTrue(!res);
		test = new String[]{"person"};
		res = Util.isPersonData(test, "producent");
		org.junit.Assert.assertTrue(res);

	}

	@Test
	public void test_Process_capitalize() {
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = " coco  ";
		row.put("acq_method", teststring);
		row.put("acq_source", teststring);
		Process_capitalize transf = new Process_capitalize();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		org.junit.Assert.assertEquals(StringUtils.capitalize(teststring), rowmodif.get("acq_method"));
		org.junit.Assert.assertEquals(StringUtils.capitalize(teststring), rowmodif.get("acq_source"));
	}

	@Test
	public void test_Process_location(){
		Map<String, Object> row = new HashMap();
		Map<String, Object> rowmodif = new HashMap();
		String teststring = "sAl 201";
		row.put("location_name", teststring);
		Process_location transf = new Process_location();
		rowmodif = (Map<String, Object>) transf.transformRow(row);
		org.junit.Assert.assertEquals(null, rowmodif.get("location_name"));		
	}
}
