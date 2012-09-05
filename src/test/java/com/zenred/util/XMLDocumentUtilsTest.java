package com.zenred.util;

import java.io.ByteArrayOutputStream;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
  * This class contains utility methods for creating
  * xml documents
  */
public final class XMLDocumentUtilsTest  extends TestCase {
	public XMLDocumentUtilsTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( XMLDocumentUtilsTest.class );
	}
	public void testXMLDocumentUtils(){
		assertTrue( true );
	}
}