//Source file: C:/VisualCafe/JAVA/LIB/cosmos/StarSystem.java

package com.zenred.cosmos;
/**
 * cluster rep answers question if cluster of Stars can have a set of planets
 * in its domain
 */

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StarSystemTest  extends TestCase {
	public StarSystemTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( StarSystemTest.class );
	}
	public void testStarSystem(){
		assertTrue( true );
	}
}
