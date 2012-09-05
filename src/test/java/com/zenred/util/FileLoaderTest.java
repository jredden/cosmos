package com.zenred.util;

import java.io.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.w3c.dom.Document;

/**
  * Load input data to a file
  */
public class FileLoaderTest  extends TestCase{
	public FileLoaderTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( FileLoaderTest.class );
	}
	public void testFileLoader(){
		assertTrue( true );
	}
}
