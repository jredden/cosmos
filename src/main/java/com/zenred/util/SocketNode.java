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


/**
 * taken from log4j source distribution  
*/
public class SocketNode implements Runnable {

  Socket socket;
  ObjectInputStream ois;

  
    /**
     * construction
     * @param socket object with available ObjectInputStream
     */
  public 
  SocketNode(Socket socket) {
    this.socket = socket;

    try {
      ois = new ObjectInputStream(socket.getInputStream());
    }
    catch(Exception egen) {
      System.err.println("Could not open ObjectInputStream to " + socket  + ":" 
			 + egen.toString());
    }
  }

    /**
     * runs somewhere around garbage collection time
     */
  public
  void finalize() {
  System.err.println("-------------------------Finalize called");
  System.err.flush();
  }

    /**
     * implemnts runnable interface
     * reads continuously from ObjectInputStream 
     */
  public void run() {
      Object event;
    try {
      while(true) {	
	event =  ois.readObject();	
      }
    }
    catch(java.io.EOFException e) {
      System.out.println("Caught java.io.EOFException closing conneciton.");
    }
    catch(java.net.SocketException e) {
      System.out.println("Caught java.net.SocketException closing conneciton.");
    }
    catch(Exception egen) {
      System.err.println("Unexpected exception. Closing conneciton." + ":" + egen.toString());
    }
    
    try {
      ois.close();
    }
    catch(Exception egen) {
      System.out.println("Could not close connection." + ":" + egen.toString());	
    }  
  }
}
