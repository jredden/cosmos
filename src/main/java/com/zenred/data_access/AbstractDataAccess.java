package com.zenred.data_access;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractDataAccess {

	protected static final Logger logger = Logger
			.getLogger(AbstractDataAccess.class);

	protected ClassPathXmlApplicationContext ctx;

	public void init() {
//		org.apache.log4j.BasicConfigurator.configure();
		ctx = new ClassPathXmlApplicationContext("spring-dao-context.xml");
		SessionFactory session = (SessionFactory) ctx.getBean("sessionFactory");
		
	}

}
