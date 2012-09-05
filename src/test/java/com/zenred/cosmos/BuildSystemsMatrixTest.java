package com.zenred.cosmos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BuildSystemsMatrixTest  extends TestCase{
	public BuildSystemsMatrixTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( BuildSystemsMatrixTest.class );
	}
	public void testBuildSystemsMatrix(){
		assertTrue( true );
	}
}
