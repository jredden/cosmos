package com.zenred.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class DrawRandomAlphaNumericTest  extends TestCase{
	public DrawRandomAlphaNumericTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( DrawRandomAlphaNumericTest.class );
	}
	public void testDrawRandomAlphaNumeric(){
		assertTrue( true );
	}
}

