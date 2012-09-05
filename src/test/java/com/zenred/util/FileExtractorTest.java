package com.zenred.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/* This class will filter and return files in the local directory
 * that have a ".txn" extenstion.  Subsequent calls to
 * extract returns individual files.
 */
public class FileExtractorTest  extends TestCase{
	public FileExtractorTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( FileExtractorTest.class );
	}
	public void testFileExtractor(){
		assertTrue( true );
	}
}
