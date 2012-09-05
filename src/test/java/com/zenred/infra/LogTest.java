
/**
 * Log.java
 * converted to single copy instance <br> 
 *
 * establishes a root category for log4j<br>
 * establishes a socket appender and writes messages to the log server<br>
 * each write to the log uses a <i> class for name </i> category when writing to the server<br>
 * priorities implemented  are <b>INFO</b>, <b>ERROR</b> and <b>DEBUG</b><br>
 * Created: Sat Jan 13 11:31:41 2001
 *
 * @author john redden
 * @version
 */
package com.zenred.infra;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



public class LogTest extends TestCase{
	public LogTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( LogTest.class );
	}
	public void testLog(){
		assertTrue( true );
	}
} // Log

