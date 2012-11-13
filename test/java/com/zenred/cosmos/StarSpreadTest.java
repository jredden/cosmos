package com.zenred.cosmos;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.zenred.util.OrderedArrayListCollection;
import com.zenred.util.OrderedListCollection;

public class StarSpreadTest  extends TestCase{
	public StarSpreadTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( StarSpreadTest.class );
	}
	public void testStarSpread(){
		assertTrue( true );
	}
}
