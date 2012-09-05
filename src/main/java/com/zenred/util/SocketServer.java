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


/**
 * Simle socket server derived from log4j source distribution.
 * It loops forever trying to accept connections.
   */

public class SocketServer  {

    static private boolean DEBUG = true;
  static SocketServer server;
  static int port;


    /**
     * main method, no arguments
     */
  public 
  static 
  void main(String argv[]) {
    
    try {
     
        // look in standard java.net.*
      ServerSocket serverSocket = new ServerSocket(port);
      while(true) {
	if(DEBUG)System.out.println("Waiting to accept a new client.");
	Socket socket = serverSocket.accept();
	InetAddress inetAddress =  socket.getInetAddress();
	if(DEBUG)System.out.println("Connected to client at " + inetAddress);

	new Thread(new SocketNode(socket)).start();
      }
    }
    catch(Exception egen) {
      egen.printStackTrace();
    }
  }

    /**
     * init is a logical extension of class construction - calling instance
     * delegates the port number and then launches an instance of the class
     * 
     */ 
  static
  void init(String portStr) {
    try {
      port = Integer.parseInt(portStr);      
    }
    catch(java.lang.NumberFormatException nfe) {
      nfe.printStackTrace();
      System.out.println("Could not interpret port number ["+ portStr +"].");
    }

    server = new SocketServer();
  }

    /**
     * construction - no arguments
     */ 
  public
  SocketServer() {
  }


}
