//      Copyright 1996-2000, International Business Machines 
//      Corporation. All Rights Reserved.

//      Copyright 2000, Ceki Gulcu. All Rights Reserved.

//      See the LICENCE file for the terms of usage and distribution.


package com.zenred.util;

import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * taken from log4j source distribution  
*/
public class SocketNodeTest  extends TestCase{
	public SocketNodeTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( SocketNodeTest.class );
	}
	public void testSocketNode(){
		assertTrue( true );
	}
}
