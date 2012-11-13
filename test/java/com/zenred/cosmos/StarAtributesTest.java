package com.zenred.cosmos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * StarAtributes.java
 *
 *
 * Created: Mon Jul 22 07:06:30 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class StarAtributesTest  extends TestCase{
	public StarAtributesTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( StarAtributesTest.class );
	}
	public void testStarAtributes(){
		assertTrue( true );
	}
}// StarAtributes
