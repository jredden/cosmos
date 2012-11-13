package com.zenred.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Enumeration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.zenred.infra.Log;

/**
* maps a HttpServletRequest to a HashMap object
*/
public class ParamNamesToHashMapTest  extends TestCase{
	public ParamNamesToHashMapTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( ParamNamesToHashMapTest.class );
	}
	public void testParamNamesToHashMapTest(){
		assertTrue( true );
	}

}
