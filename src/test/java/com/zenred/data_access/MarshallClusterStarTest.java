package com.zenred.data_access;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import junit.framework.TestCase;

import cosmos.hibernate.StarRep;

public class MarshallClusterStarTest extends TestCase {
	private MarshallStars marshall_stars;
	
	private static List<String> fullOfStars = new ArrayList<String>();
	static {
		fullOfStars.add("U100004V100011_2");
		fullOfStars.add("U100004V100012_0");
		fullOfStars.add("U100004V100013_0");
		fullOfStars.add("U100005V100004_0");
		fullOfStars.add("U100005V100004_2");
		fullOfStars.add("U100005V100004_3");
		fullOfStars.add("U100005V100005_0");
		fullOfStars.add("U100005V100008_0");
		fullOfStars.add("U100005V100009_0");
		fullOfStars.add("U100005V100012_0");
		fullOfStars.add("U100006V100005_0");
		fullOfStars.add("U100006V100006_0");
		fullOfStars.add("U100006V100010_0");
		fullOfStars.add("U100006V100011_0");
		fullOfStars.add("U100007V100003_0");
		fullOfStars.add("U100007V100004_0");
		fullOfStars.add("U100007V100005_0");
		fullOfStars.add("U100007V100005_2");
		fullOfStars.add("U100007V100005_3");
		fullOfStars.add("U100007V100005_4");
		fullOfStars.add("U100007V100007_0");
		fullOfStars.add("U100007V100008_0");
		fullOfStars.add("U100007V100009_0");
		fullOfStars.add("U100007V100009_2");
		fullOfStars.add("U100007V100009_3");		
	}

	@Before
	public void setUp() throws Exception {
		marshall_stars = new MarshallStars();
	}

		
	public void testMarshallClusterStar(){
		for(String clusterId: fullOfStars){
		List<StarRep> _list = marshall_stars.starsInCluster(clusterId);	
		System.out.println("Stars in Cluster:"+_list);
		assertNotNull(_list);
		}
	}

}
