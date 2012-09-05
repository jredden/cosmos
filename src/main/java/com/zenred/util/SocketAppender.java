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



/**
 * an implementation of s Socket Based Logger that works with log4j
 * taken from log4j source distribution
    */

public class SocketAppender {

  InetAddress address;
  int port = 4560;
  String hostName;
  ObjectOutputStream oos;
  int reconnectionDelay = 30000;
  boolean locationInfo = false;

  private Connector connector;

  int counter = 0;
    boolean closed = true; 
  

  // reset the ObjectOutputStream every 70 calls
  //private static final int RESET_FREQUENCY = 70;
  private static final int RESET_FREQUENCY = 1;

  /**
     A string constant used in naming the option for setting the the
     host name of the remote server.  Current value of this string
     constant is <b>RemoteHost</b>. See the {@link #setOption} method
     for the meaning of this option.

  */
  public static final String REMOTE_HOST_OPTION = "RemoteHost";

 /**
     A string constant used in naming the option for setting the the
     port to contect on the remote server.  Current value of this string
     constant is <b>Port</b>.  See the {@link #setOption} method
     for the meaning of this option.

  */
  public static final String PORT_OPTION = "Port";

  /**
     A string constant used in naming the option for setting the the
     location information flag.  Current value of this string
     constant is <b>LocationInfo</b>.  See the {@link #setOption} method
     for the meaning of this option.

  */
  public static final String LOCATION_INFO_OPTION = "LocationInfo";

  /**
     A string constant used in naming the option for setting the delay
     between each reconneciton attempt to remote server.  Current
     value a of this string constant is <b>ReconnectionDelay</b>.  See
     the {@link #setOption} method for the meaning of this option.

  */
  public static final String RECONNECTION_DELAY_OPTION = "ReconnectionDelay";

  public SocketAppender() {
  }

  /**
     Connects to remote server at <code>address</code> and <code>port</code>.
  */
  public
  SocketAppender(InetAddress address, int port) {
    this.address = address;
    this.port = port;
    this.hostName = address.getHostName();
    connect(address, port);
  }

  /**
     Connects to remote server at <code>host</code> and <code>port</code>.
  */
  public
  SocketAppender(String host, int port) { 
    this.port = port;
    this.hostName = host;
    this.address = getAddressByName(host);
    connect(address, port);
  }

  /**
     Connect to the specified <b>RemoteHost</b> and <b>Port</b>. 
  */
  public
  void activateOptions() {
    connect(address, port);
  }

  /**
     Close this appender. 
     <p>This will mark the appender as closed and
     call then {@link #cleanUp} method.
  */
  public
  void close() {
    this.closed = true;
    cleanUp();
  }

  /**
     Drop the connection to the remote host and release the underlying
     connector thread if it has been created
   */
  public 
  void cleanUp() {
    if(oos != null) {
      try {
	oos.close();
      }
      catch(IOException ioe) {
	System.err.println("Could not close oos." + ":" + ioe.toString());
      }
      oos = null;      
    }
    if(connector != null) {
      System.out.println("Interrupting the connector.");      
      connector.interrupt();
      connector = null;  // allow gc
    }
  }

  void connect(InetAddress address, int port) {
    if(this.address == null)
      return;
    try {
      // First, close the previous connection if any.
      cleanUp();          
      oos = new ObjectOutputStream(new Socket(address, port).getOutputStream());
    }
    catch(IOException ioe) {
      System.err.println("Could not connect to remote log4j server at ["
		   +address.getHostName()+"]. We will try again later." + ":" 
			 + ioe.toString());
      fireConnector();
    }
  }


  public
  void append(Object event) {

    if(address==null) {
      System.err.println("No remote host is set for SocketAppedender .");
      return;
    }

    if(oos != null) {
      try {
	oos.writeObject(event);
	System.out.println("=========Flushing.");
	oos.flush();
	if(++counter >= RESET_FREQUENCY) {
	  counter = 0;
	  // Failing to reset the object output stream every now and
	  // then creates a serious memory leak.
	  //System.err.println("Doing oos.reset()");
	  oos.reset();
	}
      }
      catch(IOException e) {
	oos = null;
	System.out.println("Detected problem with connection: "+e);
	fireConnector();
      }
    }
  }

  void fireConnector() {
    if(connector == null) {
      System.out.println("Starting a new connector thread.");
      connector = new Connector();
      connector.setDaemon(true);
      connector.setPriority(Thread.MIN_PRIORITY);
      connector.start();      
    }
  }

  InetAddress getAddressByName(String host) {
    try {
      return InetAddress.getByName(host);
    }	
    catch(Exception egen) {
      System.err.println("Could not find address of ["+host+"]." + ":" 
			 + egen.toString());
      return null;
    }
  }


  /**
     The Connector will reconnect when the server becomes available
     again.  It does this by attempting to open a new connection every
     <code>reconnectionDelay</code> milliseconds.

     <p>It stops trying whenever a connection is established. It will
     restart to try reconnect to the server when previpously open
     connection is droppped.

     @author  Ceki G&uuml;lc&uuml; 
     @since 0.8.4
  */
  class Connector extends Thread {

    public
    void run() {
      Socket socket;      
      while(!isInterrupted()) {
	try {
	  sleep(reconnectionDelay);
	  System.out.println("Attempting connection to "+address.getHostName());
	  socket = new Socket(address, port);
	  synchronized(this) {
	    oos = new ObjectOutputStream(socket.getOutputStream()); 
	    connector = null;
	    break;
	  }
	}
	catch(InterruptedException ie) {
	  System.out.println("Connector interrupted. Leaving loop.");
	  return;
	}
	catch(java.net.ConnectException ce) {
	  System.out.println("Remote host "+address.getHostName()
		       +" refused connection.");
	}
	catch(IOException ioe) {	  
	  System.out.println("Could not connect to " + address.getHostName()+
		       ". Exception is " + ioe.toString());
	}
      }
      System.out.println("Exiting Connector.run() method.");
    }
    
      /**
       * runs <I>some time</I> near garbage collection
       */ 
       public
       void finalize() {
       System.out.println("Connector finalize() has been called.");
       }

  }

}
