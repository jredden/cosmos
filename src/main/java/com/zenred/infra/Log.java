
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

import org.apache.log4j.Category;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Appender;
import org.apache.log4j.net.SocketAppender;
import org.apache.log4j.Priority;
import org.apache.log4j.NDC;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zenred.config.Config;
import com.zenred.config.ConfigIF;

public class Log extends Object
    implements LogAPI {
    
    private static Category cat = Category.getInstance(LogAPI.class.getName());
    private static Category root;
    private static SocketAppender sapp;

    private static Log loginstance = new Log();

    /**
     *
     * getLoginstance  - returns singleton of Log
     *@returns the instance
     */
    public static Log Instance(){
	return loginstance;
    }


    
/**
 * init socket appender
 * @param host - host IP address of log4j server
 * @param postStr - port number 0f log4j server
 */
    private static void init(String host, String portStr) {
	root = Category.getRoot();
	BasicConfigurator.configure();
	try {
	    int port   = Integer.parseInt(portStr);
	    cat.info("Creating socket appender ("+host+","+port+").");
	    sapp = new SocketAppender(host, port);
	    sapp.setName("Sapp");
	    root.addAppender(sapp);
	}
	catch(java.lang.NumberFormatException efe) {
	    efe.printStackTrace();
	    usage("Could not interpret port number ["+ portStr +"].");
	}
	catch(Exception ge) {
	    System.err.println("Could not start!");
	    ge.printStackTrace();
	}
    }

/**
 * usage - faliure on startup 
 * @param - message string
 */
    private static void usage(String msg) {
	System.out.println("<BR> " + msg);
    }

    /**
     * @return log server host
     */
    private String getLogServerHost(){
	return (null == Config.Instance().getProperty(ConfigIF.SERVER))? 
	    LogAPI.defaultHost : Config.Instance().getProperty(ConfigIF.SERVER);
    }

    /**
     * @return log server port
     */
    private String getLogServerPort(){
	return (null == Config.Instance().getProperty(ConfigIF.LOG4JPORT))? 
	    LogAPI.defaultHost : Config.Instance().getProperty(ConfigIF.LOG4JPORT);
    }

/**
 * no arg constructor
 */
    private Log() {
	init(LogAPI.defaultHost, LogAPI.defaultPort);
    }

/**
 * implements WriteDebug
 * @PARAM debug message
 */
    public void WriteDebug(String message){
	cat.debug(message);
    }
/**
 * implements WriteError
 * @PARAM debug message
 */
    public void WriteError(String message){
	cat.error(message);
    }

/**
 * implements WriteInfo
 * @PARAM debug message
 */
    public void WriteInfo(String message){
	cat.info(message);
    }


/**
 * implements WriteDebug
 * @PARAM debug message
 */
    public void WriteDebug(String category, String message){
	Category cat = Category.getInstance(category);
	cat.debug(message);
    }
/**
 * implements WriteError
 * @PARAM debug message
 */
    public void WriteError(String category, String message){
	Category cat = Category.getInstance(category);
	cat.error(message);
    }

/**
 * implements WriteError
 * @PARAM debug message
 */
    public void WriteInfo(String category, String message){
	Category cat = Category.getInstance(category);
	cat.info(message);
    }



} // Log

