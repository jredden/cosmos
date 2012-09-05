package com.zenred.cosmos;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.MessageFormat;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.zenred.util.OrderedArrayListCollection;
import com.zenred.util.OrderedListCollection;
import com.zenred.util.AnonBlock;


/**
 * EmitSql.java
 *
 *
 * Created: Mon Aug 12 07:51:00 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class EmitSqlTest  extends TestCase{
	public EmitSqlTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( EmitSqlTest.class );
	}
	public void testEmitSql(){
		assertTrue( true );
	}

}// EmitSql
