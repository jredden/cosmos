package com.zenred.data_access;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.AssertThrows;

import cosmos.hibernate.ClusterRep;

public class ClusterRepDAOTest {
	
	private MarshallStars marsallstars;

	@Before
	public void setUp() throws Exception {
		/*
		clusterRepDAO = (ClusterRepDAO) ctx
				.getBean("clusterRepDAO");
				*/
		marsallstars = new MarshallStars();
		
	}
/*
	@Test
	public void test1() {
		List<ClusterRep> clusterRepList = clusterRepDAO.findBySystemId("U100009V100003");
		assertFalse(clusterRepList == null);
	}
	*/
	
	
	@Test
	public void test2() {
		
		Short numberStars = marsallstars.getNumberStars("U100009V100003");
		System.out.println("numberStars:"+numberStars);
		assertNotNull(numberStars);
	}

}
