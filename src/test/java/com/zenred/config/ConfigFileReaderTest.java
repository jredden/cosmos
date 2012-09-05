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

/**
 * ConfigFileReader.java
 *
 *
 * Created: Tue May 08 12:37:23 2001
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class ConfigFileReaderTest  extends TestCase {
	public ConfigFileReaderTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( ConfigFileReaderTest.class );
	}
	public void testConfigFileReader(){
		assertTrue( true );
	}
}
