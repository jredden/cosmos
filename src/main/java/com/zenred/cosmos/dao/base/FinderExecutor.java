package com.zenred.cosmos.dao.base;

import java.lang.reflect.Method;
import java.util.List;

public interface FinderExecutor<T>
{
	List<T> executeFinder(Method method, final Object[] queryArgs);
}
