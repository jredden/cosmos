package com.zenred.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.xml.sax.Attributes;

/**
 * This class represents a bank builder.  It builds
 * the extract, translate and load components as
 * defined in the ObjectBuilderuration file.
 */
public class ObjectBuilderTest  extends TestCase{
	public ObjectBuilderTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( ObjectBuilderTest.class );
	}
	public void testObjectBuilder(){
		assertTrue( true );
	}
}
