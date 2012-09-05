package com.zenred.cosmos;

import java.util.*;
//import com.zenred.database.DBObject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DBCosmosTest  extends TestCase{
	public DBCosmosTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( DBCosmosTest.class );
	}
	public void testDBCosmos(){
		assertTrue( true );
	}
}
