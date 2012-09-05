package com.zenred.cosmos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
*
*/
public class HyperspaceCoordTest  extends TestCase{
	public HyperspaceCoordTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( HyperspaceCoordTest.class );
	}
	public void testHyperspaceCoord(){
		assertTrue( true );
	}
}
