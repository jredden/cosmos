package com.zenred.cosmos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * StarRep.java
 *
 *
 * Created: Wed Apr 03 08:59:40 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class StarRepTest  extends TestCase {
	public StarRepTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( StarRepTest.class );
	}
	public void testStarRep(){
		assertTrue( true );
	}
}// StarRep
