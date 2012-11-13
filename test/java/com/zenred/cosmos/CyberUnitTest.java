//Source file: C:/VisualCafe/JAVA/LIB/cosmos/CyberUnit.java

package com.zenred.cosmos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class CyberUnitTest  extends TestCase {
	public CyberUnitTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( CyberUnitTest.class );
	}
	public void testCyberUnit(){
		assertTrue( true );
	}
}
