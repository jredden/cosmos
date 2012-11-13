package com.zenred.data_access;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cosmos.hibernate.ClusterRep;

import junit.framework.TestCase;

public class StarsAvecPlanetsDAOTest  extends TestCase {
	 private ClassPathXmlApplicationContext ctx;
	  @Ignore	 
	  @Test public void testReadStarSystems()	{
//			org.apache.log4j.BasicConfigurator.configure();
			 ctx = new ClassPathXmlApplicationContext("spring-dao-context.xml");
			 SessionFactory session = (SessionFactory)ctx.getBean("sessionFactory");
//			 StarsAvecPlanetsDAO _starsavecplanets_dao = (StarsAvecPlanetsDAO) ctx.getBean("starsAvecPlanetsDAO");

//			 _starsavecplanets_dao.setSessionFactory(session);
//			 List<ClusterRep> _list = _starsavecplanets_dao.readStarSystems();
			 assertTrue( true );
		}
}
