package com.zenred.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
  * This is a container class for data that
  * is passed between strategy instances.
  */
public class DataObjectTest  extends TestCase {
	public DataObjectTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( DataObjectTest.class );
	}
	public void testDataObject(){
		assertTrue( true );
	}
}

