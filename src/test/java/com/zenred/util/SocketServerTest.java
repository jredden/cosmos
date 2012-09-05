/*
 * Scarfed from The Apache Software Foundation. All rights reserved by them ...
 * a logging server that fires off a new thread listening for logging requests.
 *
 */

package com.zenred.util;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.File;
import java.util.Hashtable;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Simle socket server derived from log4j source distribution.
 * It loops forever trying to accept connections.
   */

public class SocketServerTest  extends TestCase  {
	public SocketServerTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( SocketServerTest.class );
	}
	public void testSocketServer(){
		assertTrue( true );
	}
}
