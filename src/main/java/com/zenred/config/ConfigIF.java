
/**
 * Title:        ConfigIF<p>
 * Description:  common configuration IF
 * Copyright:    
 * @author jredden
 * @version 
 */
package com.zenred.config;

public interface ConfigIF {

    /**
     * token values for Config invokers
     */
    public final static String SERVER = "SERVER";
    public final static String LOG4JPORT = "LOG4JPORT";
    public final static String POSTFAXPERL = "POSTFAXPERL";
    public final static String SUZUKIDB = "SUZUKIDB";
    public final static String DBLOGIN = "DBLOGIN";
    public final static String DBPASSWORD = "DBPASSWORD";

    /**
     * domain values - where the key comes from or what property file
     * it was once used in
     */
    public final static String ANTDOMAIN = "ant";
    public final static String JSPDOMAIN = "jsp";
    public final static String APPLICATIONDOMAIN = "application";
    public final static String DEFAULTDOMAIN = ANTDOMAIN;
    
    /**
     *sole purpose is to define path of config.xml
     */
    public static final String PROPERTIES_FILE_NAME = "config.properties";
    public static final boolean DEBUG = false;
    //    private static final boolean DEBUG = true;

    // key of config.xml 
    public static final String CONFIG_FILENAME = "config.xml";

    /**
     * Allows domain to be modal
     */ 
    public void useDomain(String domain);
    
    /**
     * access a system property
     * @param property key
     * @param property domain 
     * @return property value
     */
    public String getProperty(String key, String domain);
    /**
     * access a system property
     * @param property key
     * @return property value
     */
    public String getProperty(String key);

}
