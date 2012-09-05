package com.zenred.data_access;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cosmos.hibernate.ClusterRep;

import junit.framework.TestCase;

public class StarsAvecPlanetsDAOFundTest  extends TestCase {
	
	private static final Logger logger = Logger.getLogger(StarsAvecPlanetsDAOFundTest.class);

		 private ClassPathXmlApplicationContext ctx;
		 
			public void testReadStarSystems()	{
//				org.apache.log4j.BasicConfigurator.configure();
				 ctx = new ClassPathXmlApplicationContext("spring-dao-context.xml");
				 SessionFactory session = (SessionFactory)ctx.getBean("sessionFactory");
				 StarsAvecPlanetsFundDAO _starsavecplanetsfund_dao = (StarsAvecPlanetsFundDAO) ctx.getBean("starsAvecPlanetsFundDAO");
				 List<ClusterRep> _list = _starsavecplanetsfund_dao.findByPlanetsAllowed();
				 logger.debug("Cluster Rep Size:"+_list.size());
				 ClusterRep _crep = _list.get(0);
				 
				 assertNotNull(_crep);
			}
}
