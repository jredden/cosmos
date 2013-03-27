package com.zenred;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class RunTimeLikeSetup {
	private GenericApplicationContext ctx = new GenericApplicationContext();
	private XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
	private static RunTimeLikeSetup singleRuntimeLikeSetup;
	
	// singleton since used for unit tests and is memory intensive
	public static RunTimeLikeSetup instance(){
		if(null == singleRuntimeLikeSetup){
			singleRuntimeLikeSetup = new RunTimeLikeSetup();
		}
		return singleRuntimeLikeSetup;
	}
	
	private RunTimeLikeSetup (){
		xmlReader.loadBeanDefinitions(new FileSystemResource("./src/main/resources/spring-dao-context.xml"));
	}

	public Object getBean(String name) {
		return ctx.getBean(name);
	}
}
