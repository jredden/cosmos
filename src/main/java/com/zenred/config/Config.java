package com.zenred.config;

import java.util.Properties;
import java.util.HashMap;
import java.io.*;
import java.text.*;


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

public class Config implements ConfigIF {


    private ConfigFileReader configfilereader = new ConfigFileReader();

    private HashMap configcollection;

    private static Config config_instance;

    private String default_domain = ConfigIF.DEFAULTDOMAIN;

    /**
     * single copy instantiator
     * @return Config instance
     */
    public static Config Instance(){
        if(config_instance == null){
            config_instance = new Config();
        }
        return config_instance;
    }

    /**
     * no arg private constructor
     */
    private  Config(){
        // parse the user config
        configfilereader.main(new String []{ConfigIF.CONFIG_FILENAME});


        // returned obejct has config and associated Icon levels
        configcollection =  configfilereader.getConfigMap();
        if(DEBUG)Log.Instance().WriteDebug(configcollection.toString());
    }


    /**
     * access a system property
     * @param property key
     * @param property domain 
     * @return property value
     */
    public String getProperty(String key, String domain){
        ConfigFileReader.ConfigSet  _configset = (ConfigFileReader.ConfigSet)configcollection.get(key);
        return _configset.config_content;
    }
    /**
     * access a system property
     * @param property key
     * @param property domain 
     * @return property value
     */
    public String getProperty(String key){
        return getProperty(key, default_domain);
    }

    /**
     * sets the modal domain
     * @param String
     */
    public void useDomain(String domain){
	default_domain = domain;
    }

    /**
     * main - test driver
     */
    public static void main (String [] argv){
        Config _fmc = Config.Instance();
        String domain = "default";
        if(argv.length == 2){
            domain = argv[1];
        }
        System.out.println ("property:" + argv[0] + " value:" + _fmc.getProperty(argv[0], domain));

    } 
}
