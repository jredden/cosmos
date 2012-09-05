package com.zenred.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.xml.sax.Attributes;

/**
 * This class represents a bank builder.  It builds
 * the extract, translate and load components as
 * defined in the DataSystemuration file.
 */
public class DataSystemTest  extends TestCase{
	public DataSystemTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( DataSystemTest.class );
	}
	public void testDataSystem(){
		assertTrue( true );
	}
}
