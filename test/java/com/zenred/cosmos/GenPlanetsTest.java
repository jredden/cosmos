//Source file: C:/VisualCafe/JAVA/LIB/cosmos/Atmosphere.java

package com.zenred.cosmos;



import com.zenred.util.VectToList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GenPlanetsTest extends TestCase {

	public static Test suite() {
		return new TestSuite(GenPlanetsTest.class);
	}
	
	public void testBode(){
		GenPlanets genPlanets = new GenPlanets();
		double b0 = genPlanets.genBode(0);
		double b1 = genPlanets.genBode(1);
		double b2 = genPlanets.genBode(2);
		System.out.println("bode:" + b0 + ":" + b1 + ":" + b2 +":");
		assertTrue(true);
	}

	public void testGenPlanets() {
		GenPlanets genPlanets = new GenPlanets();
		genPlanets.generatePlanets(null);
	    VectToList.print(genPlanets.getStarSystemsList());
	    System.out.println("number systems:" + genPlanets.getStarSystemsList().size() + ":");

		assertTrue(true);
	}

}
