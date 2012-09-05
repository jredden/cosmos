/*
 * BDGList.java
 *
 * Created on May 17, 2002 by Barry Gold
 */

package com.zenred.util;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author  barry gold
 * Extends ArrayList to allow construction with or adding an array.
 */

public class BDGListTest  extends TestCase{
	public BDGListTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( BDGListTest.class );
	}
	public void testBDGList(){
		assertTrue( true );
	}
}
