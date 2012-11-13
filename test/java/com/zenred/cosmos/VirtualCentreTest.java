//Source file: C:/VisualCafe/JAVA/LIB/cosmos/VirtualCentre.java

package com.zenred.cosmos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
the center of something in hyperspace
*/
public class VirtualCentreTest  extends TestCase {
	public VirtualCentreTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( VirtualCentreTest.class );
	}
	public void testVirtualCentre(){
		assertTrue( true );
	}
}
