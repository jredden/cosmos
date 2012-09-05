package com.zenred.data_access;


import java.util.List;

import org.junit.Before;
import junit.framework.TestCase;

import cosmos.hibernate.StarRep;

public class MarshallStarsTest extends TestCase {
	private MarshallStars marshall_stars;

	@Before
	public void setUp() throws Exception {
		marshall_stars = new MarshallStars();
	}

		
	public void testMarshallStars(){
		List<StarRep> _list = marshall_stars.getStarsWithPlanets();
		assertNotNull(_list);
	}
	/*
	public void testMarshallStars2(){
		Short _ans = marshall_stars.getNumberStars("U100017V100019");
		assertNotNull(_ans);
	}
	*/
}
