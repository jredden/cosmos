package com.zenred.infra;

import com.zenred.config.*;
/**
 * LogAPI.java
 *
 *
 * Created: Sat Jan 13 11:33:04 2001
 *
 * @author john redden
 * @version
 */

public interface LogAPI  {

    public static final String defaultHost = Config.Instance().getProperty(ConfigIF.SERVER, "servlet");
    public static final String defaultPort = Config.Instance().getProperty(ConfigIF.LOG4JPORT, "servlet");
    
    public void WriteDebug(String message);
    public void WriteError(String message);
    public void WriteInfo(String message);
    
    public void WriteDebug(String catagoryname, String message);
    public void WriteError(String catagoryname, String message);
    public void WriteInfo(String catagoryname, String message);
    
} // LogAPI
