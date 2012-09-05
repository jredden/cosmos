package com.zenred.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Enumeration;

import com.zenred.infra.Log;

/**
* maps a HttpServletRequest to a HashMap object
*/
public class ParamNamesToHashMap{
    
    /**
     * builds a hashtable map from the servlet request object
     * @param ipip HttpServletRequest instance
     * @return Hashtable with key value pairs from the invoking web page
     */
    public static HashMap  mapReqeustToHashMap(HttpServletRequest ipip){
        HashMap _reqmap = new HashMap();
        Enumeration _ip_array_list = ipip.getParameterNames();
        
        while(_ip_array_list.hasMoreElements()){
            String _key = (String)_ip_array_list.nextElement();
            String _value [] = ipip.getParameterValues(_key);
            _reqmap.put(_key, _value[0]); // only one value per key expected
        }        
        return _reqmap;
    }
}
