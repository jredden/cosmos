//Source file: C:/VisualCafe/JAVA/LIB/com.zenred.cosmos/DrawRolls.java

package com.zenred.cosmos;
import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * draws random rolls based on imputs
 */ 

public class DrawRollsTest extends TestCase{
	DrawRolls drawRolls = new DrawRolls();
	
	public DrawRollsTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( DrawRollsTest.class );
	}
	public void testDrawRolls(){
		for(int idex = 0;idex < 100 ;idex++){
			System.out.println("next:"+drawRolls.getD1000());
		}
		assertTrue( true );
	}
}
