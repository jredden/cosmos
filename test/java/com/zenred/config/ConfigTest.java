package com.zenred.config;

import java.util.Properties;
import java.util.HashMap;
import java.io.*;
import java.text.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


import com.zenred.infra.Log;

/**
 * Config.java
 *
 *
 * Created: Fri Oct 19 13:00:17 2001
 *
 * @author <a href="mailto:John_Redden@AOL.com "</a>
 * @version 12
 */

public class ConfigTest  extends TestCase {
	public ConfigTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( ConfigTest.class );
	}
	public void testConfig(){
		assertTrue( true );
	}
}
