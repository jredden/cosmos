package com.zenred.util;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 * BuilderReaderIF.java
 *
 *
 * Created: Wed Aug 20 19:20:19 2003
 *
 * @author <a href="mailto:jredden@ZENRED-Q4TEOFDA"></a>
 * @version
 */

public interface BuilderReaderIF {

    public void parse(String filename) throws IOException, 
	SecurityException, SAXException; 

    public void setBuilder(Builder builder);
    
}// BuilderReaderIF
