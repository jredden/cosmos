package com.zenred.cosmos.dao.base;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

public class FinderInterceptor implements IntroductionInterceptor {
	/**
	 * Interceptor used with Spring that captures all <code>findXXX</code> calls
	 * to GenericDao objs and forwards them to a FinderExecutor obj which handles the calls.
	 * 
	 * Currently, <code>findXXX</code> calls are handled by Hibernate via named queries.
	 * 
	 * @see GenericDaoHibernateImpl
	 * @author Ji Kim odified by jredden
	 *
	 */
	public Object invoke(MethodInvocation mi) throws Throwable {
		if (implementsInterface(mi.getThis().getClass())) {
			FinderExecutor genericDao = (FinderExecutor) mi.getThis();
			String methodName = mi.getMethod().getName();
			if (methodName.startsWith("find")) {
				Object[] arguments = mi.getArguments();
				return genericDao.executeFinder(mi.getMethod(), arguments);
			}
		}
		return mi.proceed();
	}

	public boolean implementsInterface(Class clazz) {
		return FinderExecutor.class.isAssignableFrom(clazz);
	}
}