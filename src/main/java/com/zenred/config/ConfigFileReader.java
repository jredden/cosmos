package com.zenred.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.SequenceInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.AttributesImpl;

/**
 * ConfigFileReader.java
 *
 *
 * Created: Tue May 08 12:37:23 2001
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class ConfigFileReader {



    /**
     * users a sax parser to get the user messages
     */ 
    private static final boolean DEBUG = true;
    // private static final boolean DEBUG = false;

    private static final String  DOMAIN = "domain";

    /** Default parser name. */
    private static final String 
        DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";
    private static String parserName = DEFAULT_PARSER_NAME;

    private static boolean setValidation    = false; //defaults
    private static boolean setNameSpaces    = true;
    private static boolean setSchemaSupport = true;
    private static int elementCount = 0;

    /** parses the xml file into a sequence of SAX callback events */
    private XMLReader xr;
    /** inputs raw data to the parser */
    private InputSource inputSource;

    private ConfigBuilder builder = new ConfigBuilder();

    private static HashMap configurations = new HashMap();

    protected ConfigFileReader(){}
    
    /**
     * helper class for content and severity
     */ 
    public class ConfigSet{
        protected String config_content;
        protected String config_domain;
    }

    public ConfigSet getNewConfigSet(){
        return new ConfigSet();
    }

    public static ConfigSet frontNewConfigSet(){
        ConfigFileReader configfilereader = new ConfigFileReader();
        return configfilereader.getNewConfigSet(); 
    } 

     /**
     * encapsulates the various exceptions which can occur as a result of parsing
     * using SAX
     */
    static class ParseException extends Exception{


        /**
         * creates a ParseException containing the given exception
         * @param e The actual Exception.
         */
        public ParseException(Exception egen){
            super(egen.toString());
        }
    }

    /**
     * constructs a LinkedList of LogConfig objects from callbacks generated by a
     * SAX parser
     */
    private static class ConfigBuilder extends DefaultHandler implements ContentHandler
    {
        /** result of a particular parse */
        private LinkedList result = new LinkedList();
        
       /** Usermessage under construction */
        private ConfigSet configset = null;

        private String configuration_domain = null;

        /** Buffers up the PCDATA contents of elements */
        private StringBuffer contents = new StringBuffer();

        /**
         * gets the resultant list of Usermessages
         *
         * @return the generated list of Usermessages
         */
        public LinkedList getResult(){
            return result;
        }

        /** called to notify this object of the start of the document */
        public void startDocument(){
        }   

        /** called to notify this object of the end of the document */
        public void endDocument(){
        }   

        /** called to notify this object when a processing instruction is encountered */
        public void processingInstruction(String ai,String bi){
        }

        /** called to set the DocumentLocator of this object */
        public void setDocumentLocator(Locator loc){
        }   

        /**
         * called to notify this object of the start of an element
         *
         * @param name the name of the element
         * @param attributes the element's attributes
         */
        public void startElement(String namespace_uri, String local_name, String name, Attributes attributes){

            

            int _num_attributes = attributes.getLength();
            if( _num_attributes > 0){
                configuration_domain = attributes.getValue(DOMAIN);
            }

            elementCount++;

         }

        /** called to notify this object of the end of an element
         *
         * @param name the name of the element
         */
        public void endElement(String namespace_uri, String local_name, String name){
            if(DEBUG) System.out.println("<nextelement><count>" + elementCount +  "</count><local_name>" + local_name + "</local_name><name>" + name + "</name><configuration_domain>" + configuration_domain + "</configuration_domain><content>" + contents.toString() + "</content></nextelement>");
           
           ConfigSet configset = frontNewConfigSet();
           configset.config_content = (contents.toString()).trim();
           configset.config_domain =  configuration_domain;
           configurations.put(name, configset);

           contents.setLength(0);
        }   

        /**  called to notify this object of PCDATA characters
         *
         * @param chars the array of characters
         * @param start the offset into the characters array to start at
         * @param length the number of characters to use
         */
        public void characters(char[] chars, int start, int length){
            this.contents.append(chars,start,length);
        }

        /** called to notify this object of PCDATA whitespace
         *
         * @param chars the array of characters
         * @param start the offset into the characters array to start at
         * @param length the number of characters to use
         */
        public void ignorableWhitespace(char[] chars, int start, int length){
            this.contents.append(chars,start,length);   
        }

        public void skippedEntity(java.lang.String name){
        }

        public void endPrefixMapping(String prefix)
                      throws SAXException {
        }

        public void startPrefixMapping(String prefix,
                               String uri)
                        throws SAXException {
        }

    }   
    
    /**
     * constructs the Reader from the given stream
     *
     * @param input the stream the ConfigFileReader will Read from.
     * @throws ParseException if the initialization of the parser fails
     */
    public ConfigFileReader(InputStream input)
    throws ParseException
    {
        InputStream xmlInput = disguiseAsValidXML(input);

        try
        {
            this.inputSource = new InputSource(xmlInput);
        }
        catch (Exception e)
        {
            throw new ParseException(e);
        }
    }

    /**
     * reads every Usermessage object contained in the file
     *
     * @return a list of Usermessages objects
     * @throws ParseException if the parsing of the file fails.
     */
    public LinkedList readAll()
    throws ParseException
    {
	if(DEBUG)System.out.println("<START_PARSE>");

        try {
          xr = (XMLReader)Class.forName(parserName).newInstance();
          }
        catch (ClassNotFoundException cnfe) {
            if(DEBUG)System.out.println("<SAX_cnfe_exception/>");
            System.err.println(cnfe.getMessage());
            return null;
          }
        catch (InstantiationException ine) {
            if(DEBUG)System.out.println("<SAX_ine_exception/>");
            System.err.println(ine.getMessage());
            return null;
          }
        catch (IllegalAccessException iae) {
            if(DEBUG)System.out.println("<SAX_iae_exception/>");
            System.err.println(iae.getMessage());
            return null;
          }

	// message handler setter methods
	xr.setContentHandler(builder);
	xr.setErrorHandler(builder);

    if(DEBUG)System.out.println("<DO_PARSE/>");
        try
        {
            xr.parse(inputSource);
        }
        catch (Exception egen)
        {
            throw new ParseException(egen);
        }
	if(DEBUG)System.out.println("</START_PARSE>");
        return builder.getResult();
    }

    /** closes the stream */
    public void close()
    {
        inputSource = null;
    }

    /** A Prefix, to make the input stream into proper XML. */
    private static String startXML = "<?xml version=\"1.0\"?>\n<logsequence>\n";
    /** A Suffix, to make the input stream into proper XML. */
    private static String finishXML = "</logsequence>\n";
    /**
     * appends and prepends streams to the argument input stream so that it
     * can be parsed as an XML document by SAX  \
     *
     * @param in the stream to be disguised as XML
     * @return the disguised stream
     */
    private static InputStream disguiseAsValidXML(InputStream in)
    {
        Vector inputStreams = new Vector(3);
        inputStreams.addElement(new ByteArrayInputStream(startXML.getBytes()));
        inputStreams.addElement(in);
        inputStreams.addElement(new ByteArrayInputStream(finishXML.getBytes()));
        return new SequenceInputStream(inputStreams.elements());
    }

    /**
     * return message map
     */
    public static HashMap getConfigMap(){
        return configurations;
    }

    /**
     * tests the reader by reading UserConfigs from a named file, then 
     * printing information from them on System.out.
     */
    public static void main(String[] args)
    {
        InputStream input = ConfigFileReader.class.getResourceAsStream(args[0]);
        try{
            //            input = new FileInputStream(args[0]);

            ConfigFileReader parser = new ConfigFileReader(input);
            LinkedList longlist = parser.readAll();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (Iterator i = longlist.iterator();
                 i.hasNext();
                 ){
                in.readLine();
            }
        }
        catch (Exception egen){
            System.err.println("hideous config reader error: " + egen.getMessage());
        }
    }
}
