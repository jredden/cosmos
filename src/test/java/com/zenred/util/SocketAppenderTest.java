package com.zenred.util;

import java.net.InetAddress;
import java.net.Socket;
import java.io.OutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.PrintWriter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



/**
 * an implementation of s Socket Based Logger that works with log4j
 * taken from log4j source distribution
    */

public class SocketAppenderTest  extends TestCase {
	public SocketAppenderTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( SocketAppenderTest.class );
	}
	public void testSocketAppender(){
		assertTrue( true );
	}
}
