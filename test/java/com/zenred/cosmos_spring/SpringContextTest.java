package com.zenred.cosmos_spring;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextTest extends TestCase
{
	 private ClassPathXmlApplicationContext ctx;
	 
	public void testBeans()	{
//		org.apache.log4j.BasicConfigurator.configure();
		 ctx = new ClassPathXmlApplicationContext("spring-dao-context.xml");
		 SessionFactory session = (SessionFactory)ctx.getBean("sessionFactory");
		 assertTrue( true );
	}
}
