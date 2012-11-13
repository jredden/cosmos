package com.zenred.util;
/**
 * proivdes a common set of methods for SAX 2 based control and XmlControlFileReader file
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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
public abstract class XmlControlFileReaderTest  extends TestCase{
	public XmlControlFileReaderTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( XmlControlFileReaderTest.class );
	}
	public void testXmlControlFileReader(){
		assertTrue( true );
	}
}
