package com.zenred.cosmos;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * SystemDisplayNode.java
 *
 *
 * Created: Tue Jul 30 07:07:23 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class SystemDisplayNodeTest  extends TestCase{
	public SystemDisplayNodeTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( SystemDisplayNodeTest.class );
	}
	public void testSystemDisplayNode(){
		assertTrue( true );
	}
}// SystemDisplayNode
