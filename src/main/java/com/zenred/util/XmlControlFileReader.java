package com.zenred.util;
/**
 * proivdes a common set of methods for SAX 2 based control and config file
 * readers
 */

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
import org.xml.sax.ErrorHandler;

/**
 * XmlControlFileReader.java
 *
 */
public abstract class XmlControlFileReader{

    // private static final boolean DEBUG = false;
    private static final boolean DEBUG = true;
 
    /**
     * extend prototype for Content handler
     */
    public interface ControlBuilder extends ContentHandler {
        public LinkedList getResult();
    }


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

    private ControlBuilder builder;

    public static void addOneToCount(){++ elementCount;}
    public static int getCount(){return elementCount;}


    /**
     *
     */
    public void setBuilder(ControlBuilder builder){
        this.builder = builder;
    }

    /**
     *@return ControlBuilder implementation
     */
    public ControlBuilder getBuilder(){
        return builder;
    }

    protected XmlControlFileReader(){}

         /**
     * encapsulates the various exceptions which can occur as a result of parsing
     * using SAX
     */
    protected static class ParseException extends Exception{


        /**
         * creates a ParseException containing the given exception
         * @param e The actual Exception.
         */
        public ParseException(Exception egen){
            super(egen.toString());
        }
    }

    /**
     * constructs the Reader from the given stream
     *
     * @param input the stream the MessagesFileReader will Read from.
     * @throws ParseException if the initialization of the parser fails
     */
    public XmlControlFileReader(InputStream input)
    throws ParseException{
        InputStream xmlInput = disguiseAsValidXML(input);

        try {
            this.inputSource = new InputSource(xmlInput);
        }
        catch (Exception gen){
            throw new ParseException(gen);
        }
    }


    /**
     * reads every Usermessage object contained in the file
     *
     * @return a list of Usermessages objects
     * @throws ParseException if the parsing of the file fails.
     */
    public LinkedList readAll()
    throws ParseException {
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
        xr.setErrorHandler(getErrorHandler(builder));

        if(DEBUG)System.out.println("<DO_PARSE/>");
        try{
            xr.parse(inputSource);
        }
        catch (Exception egen){
            throw new ParseException(egen);
        }
        if(DEBUG)System.out.println("</START_PARSE>");
        return builder.getResult();
    }

    /**
     * @return ErrorHanlder instance
     */
    private ErrorHandler getErrorHandler(Object error_handler){
        return (ErrorHandler)error_handler;
    }

    /** closes the stream */
    public void close()    {
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
    private static InputStream disguiseAsValidXML(InputStream in){
        Vector inputStreams = new Vector(3);
        inputStreams.addElement(new ByteArrayInputStream(startXML.getBytes()));
        inputStreams.addElement(in);
        inputStreams.addElement(new ByteArrayInputStream(finishXML.getBytes()));
        return new SequenceInputStream(inputStreams.elements());
    }

}
