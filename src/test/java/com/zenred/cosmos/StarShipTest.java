//Source file: C:/VisualCafe/JAVA/LIB/cosmos/StarShip.java

package com.zenred.cosmos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class StarShipTest  extends TestCase {
	public StarShipTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( StarShipTest.class );
	}
	public void testStarShip(){
		assertTrue( true );
	}
}
