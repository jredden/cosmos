package com.zenred.data_access;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmos.hibernate.Planetoid;

public class PlanetoidsTest {
	
	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;

	@Before
	public void setUp() throws Exception {
		marshallClustersAndStarsAndPlanetsInOneSystem = new MarshallClustersAndStarsAndPlanetsInOneSystem();
	}

	@Test
	public void testPlanetoids0() {
		short starId = 124;
		List<Planetoid> planetoidsList = marshallClustersAndStarsAndPlanetsInOneSystem.getPlanetsByClusterAndStar("U100007V100009_0", starId);
		for(Planetoid planetoid : planetoidsList){System.out.println(planetoid);}
		assertNotNull(planetoidsList);
	}

}
