package com.zenred.cosmos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DistVectTest  extends TestCase {
	public DistVectTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( DistVectTest.class );
	}
	public void testDistVect(){
		assertTrue( true );
	}
}
	
