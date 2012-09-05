package com.zenred.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.util.List;
import java.util.ListIterator;
import java.util.Observer;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

// import com.zenred.vehicletest.BuilderReader;

/** This class is an abstract "helper" class that is
 *  responsible for initializing the xml parser and
 *  managing Builder creation.
 */
public abstract class BuilderClientTest  extends TestCase{
	public BuilderClientTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( BuilderClientTest.class );
	}
	public void testBuilderClient(){
		assertTrue( true );
	}
}

