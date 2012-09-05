package com.zenred.cosmos;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StarSpreadProfilesTest  extends TestCase{
	public StarSpreadProfilesTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( StarSpreadProfilesTest.class );
	}
	public void testStarSpreadProfiles(){
		assertTrue( true );
	}
}

