package com.zenred.cosmos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Collection;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.zenred.util.OrderedArrayListCollection;

/*
 * BuildSystems.java
 *
 * Created on April 6, 2001, 8:59 PM
 */



/**
 *
 * @author  jredden
 * @version 
 */
public class BuildSystemsTest  extends TestCase {
	public BuildSystemsTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( BuildSystemsTest.class );
	}
	public void testBuildSystems(){
		assertTrue( true );
	}
}
