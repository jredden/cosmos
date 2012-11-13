/*
 * ClassName.java
 *
 * Created on April 7, 2001, 9:07 PM
 */

package com.zenred.cosmos;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author  jredden
 * @version 
 */
public class BuilderTest  extends TestCase{
	public BuilderTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( BuilderTest.class );
	}
	public void testBuilder(){
		assertTrue( true );
	}
}