package com.zenred.data_access;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cosmos.hibernate.StarRep;

public class StarsDAOFundTest  extends TestCase {
	

		 private ClassPathXmlApplicationContext ctx;
		 
			public void testReadStarSystems()	{
				 ctx = new ClassPathXmlApplicationContext("spring-dao-context.xml");
				 StarsFundDAO _starsfund_dao = (StarsFundDAO) ctx.getBean("starsFundDAO");
				 short id = 84;
				 StarRep _srep = _starsfund_dao.findStarByStarId(id);
				 System.out.println("_srep:"+_srep);				 
				 assertNotNull(_srep);
			}
}
