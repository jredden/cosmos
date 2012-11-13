package com.zenred.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
  * This class is a generic Factory that
  * employs Java reflection to create objects.
  */
public class ObjectFactoryTest  extends TestCase {
	public ObjectFactoryTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( ObjectFactoryTest.class );
	}
	public void testObjectFactory(){
		assertTrue( true );
	}
}
