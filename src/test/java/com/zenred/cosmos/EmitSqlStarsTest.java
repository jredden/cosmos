package com.zenred.cosmos;
import java.io.PrintWriter;
import java.text.MessageFormat;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.zenred.util.OrderedArrayListCollection;
import com.zenred.util.OrderedListCollection;
import com.zenred.util.AnonBlock;

/**
 * EmitSqlStars.java
 *
 *
 * Created: Thu Aug 22 07:05:04 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class EmitSqlStarsTest  extends TestCase{
	public EmitSqlStarsTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( EmitSqlStarsTest.class );
	}
	public void testEmitSqlStars(){
		assertTrue( true );
	}
}// EmitSqlStars
