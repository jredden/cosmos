package com.zenred.cosmos.aop;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {
	private void displayController(ProceedingJoinPoint proceedingJoinPoint, Log logger){
		int argc = 0;
		for (Object _obj :proceedingJoinPoint.getArgs()){
			System.out.println("arg"+argc+":"+_obj+":"+_obj.getClass().getName());
			++argc;
		}
		System.out.println(Arrays.toString(proceedingJoinPoint.getArgs()));
		logger.info(Arrays.toString(proceedingJoinPoint.getArgs()));
	}
	private void displayService(ProceedingJoinPoint proceedingJoinPoint, Log logger){
		int argc = 0;
		for (Object _obj :proceedingJoinPoint.getArgs()){
			if(null == _obj){
				System.out.println("arg"+argc+":"+_obj+":");
			}
			else{
				System.out.println("arg"+argc+":"+_obj+":"+_obj.getClass().getName());
			}
			++argc;
		}
		System.out.println(Arrays.toString(proceedingJoinPoint.getArgs()));
		logger.info(Arrays.toString(proceedingJoinPoint.getArgs()));
	}
	
	private void displayDao(ProceedingJoinPoint proceedingJoinPoint, Log logger){
		int argc = 0;
		for (Object _obj :proceedingJoinPoint.getArgs()){
			if(null == _obj){
				System.out.println("arg"+argc+":"+_obj+":");
			}
			else{
				System.out.println("arg"+argc+":"+_obj+":"+_obj.getClass().getName());
			}
			++argc;
		}
		System.out.println(Arrays.toString(proceedingJoinPoint.getArgs()));
		logger.info(Arrays.toString(proceedingJoinPoint.getArgs()));
	}
	@Around("execution(* com.zenred.servlet.SpringappControllerSystemDetail2 .*(..))")
	public Object logSpringappControllerSystemDetail2(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String signatureString = proceedingJoinPoint.getSignature()
				.toLongString();
		System.out.println("before.SpringappControllerSystemDetail2:" + signatureString
				+ ":-:");
		Log logger = LogFactory
				.getLog("com.zenred.servlet.SpringappControllerSystemDetail2");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.SpringappControllerSystemDetail2:" + _ret + ":+:");
		return _ret;
	}
	
	@Around("execution(* com.zenred.cosmos.GenPlanets.*(..))")
	public Object logGenPlanets(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String signatureString = proceedingJoinPoint.getSignature()
				.toLongString();
		System.out.println("before.GenPlanets:" + signatureString
				+ ":-:");
		Log logger = LogFactory
				.getLog("com.zenred.cosmos.GenPlanets");
		displayService(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.GenPlanets:" + _ret + ":+:");
		return _ret;
	}
	@Around("execution(* com.zenred.service.MarshalSystemDetails.*(..))")
	public Object logMarshalSystemDetails(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String signatureString = proceedingJoinPoint.getSignature()
				.toLongString();
		System.out.println("before.MarshalSystemDetails:" + signatureString
				+ ":-:");
		Log logger = LogFactory
				.getLog("com.zenred.service.MarshalSystemDetails");
		displayService(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.MarshalSystemDetails:" + _ret + ":+:");
		return _ret;
	}
	@Around("execution(* com.zenred.service.GeneratePlanets.*(..))")
	public Object logGeneratePlanets(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String signatureString = proceedingJoinPoint.getSignature()
				.toLongString();
		System.out.println("before.GeneratePlanets:" + signatureString
				+ ":-:");
		Log logger = LogFactory
				.getLog("com.zenred.service.GeneratePlanets");
		displayService(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.GeneratePlanets:" + _ret + ":+:");
		return _ret;
	}
	@Around("execution(* com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem.*(..))")
	public Object logMarshallClustersAndStarsAndPlanetsInOneSystem(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String signatureString = proceedingJoinPoint.getSignature()
				.toLongString();
		System.out.println("before.MarshallClustersAndStarsAndPlanetsInOneSystem:" + signatureString
				+ ":-:");
		Log logger = LogFactory
				.getLog("com.zenred.service.MarshallClustersAndStarsAndPlanetsInOneSystem");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.MarshallClustersAndStarsAndPlanetsInOneSystem:" + _ret + ":+:");
		return _ret;
	}
	@Around("execution(* com.zenred.data_access.PlanetoidRepDAO.*(..))")
	public Object logPlanetoidRepDAO(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String signatureString = proceedingJoinPoint.getSignature()
				.toLongString();
		System.out.println("before.PlanetoidRepDAO:" + signatureString
				+ ":-:");
		Log logger = LogFactory
				.getLog("com.zenred.service.PlanetoidRepDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PlanetoidRepDAO:" + _ret + ":+:");
		return _ret;
	}


}
