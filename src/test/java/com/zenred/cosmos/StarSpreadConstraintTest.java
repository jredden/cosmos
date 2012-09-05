package com.zenred.cosmos;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.zenred.util.OrderedLinkedListCollection;
import com.zenred.util.AnonBlock;

public class StarSpreadConstraintTest  extends TestCase{
	public StarSpreadConstraintTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( StarSpreadConstraintTest.class );
	}
	public void testStarSpreadConstraint(){
		assertTrue( true );
	}
}

