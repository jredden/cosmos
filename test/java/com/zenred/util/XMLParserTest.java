package com.zenred.util;

import org.xml.sax.helpers.XMLReaderAdapter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.io.CharArrayWriter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
  * This class parses XML XmlParseruration files and
  * notifies observers (builders and the client of
  * the builders) of specific events.
  */
public class XMLParserTest  extends TestCase {
	public XMLParserTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( XMLParserTest.class );
	}
	public void testXMLParser(){
		assertTrue( true );
	}
 }

