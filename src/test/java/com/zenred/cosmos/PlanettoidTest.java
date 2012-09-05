

//Source file: C:/VisualCafe/JAVA/LIB/cosmos/Planettoid.java

package com.zenred.cosmos;

import java.lang.reflect.Array;

import com.zenred.cosmos.Planettoid;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PlanettoidTest  extends TestCase {
	
	public PlanettoidTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( PlanettoidTest.class );
	}
	public void testPlanettoid(){
		Planettoid _planetoid = new Planettoid();
		_planetoid.setRadius(new Double(1.0));
		Double _ans = _planetoid.getRadius();
		System.out.println("PlanettoidTest.results:" + _ans + ":" );
		assertEquals(_ans, new Double(1.0));

	}
}
