package com.zenred.cosmos.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.Query;

import com.zenred.cosmos.dao.base.FinderExecutor;
import com.zenred.cosmos.dao.base.GenericDao;

/**
 * GenericDao implementation using Hibernate.  This class implements the usual CRUD operation
 * and a generic finder method which finds and executes Hibernate named queries
 * (see executeFinder() for more details).
 * 
 * NOTE: HibernateSession-equivalent method calls on HibernateTemplate 
 * (e.g. getHibernateTemplate().save()) will open AND close the session, so it's best used for
 * single step ops.
 *  
 * @author JRedden from Ji Kim wlms project
 * 
 * @param <T>
 * @param <PK>
 */
public class GenericDaoHibernateImpl<T, PK extends Serializable> 
		extends HibernateDaoSupport implements GenericDao<T, PK>, FinderExecutor<T>
{
	private Class<T> type;

	public GenericDaoHibernateImpl(Class<T> type)
	{
		this.type = type;
	}

	public PK create(T o)
	{
		logger.debug("o = " + o);
		return (PK) getSession().save(o);
	}

	public T read(PK id)
	{
		logger.debug("id = " + id);
		return (T) getSession().get(type, id);
	}

	public void update(T o)
	{
		logger.debug("o = " + o);
		getSession().update(o);
	}

	public void delete(T o)
	{
		logger.debug("o = " + o);
		getSession().delete(o);
	}

	/**
	 * All findXXX calls to GenericDaos are routed to this method, which
	 * uses Hibernate named queries.  Queries are found based on the
	 * model class and method call; for example, if findByID that returns
	 * a User is called, the named query would be User.findByID.
	 */
	public List<T> executeFinder(final Method method, final Object[] queryArgs)
	{
		String queryName = queryNameFromMethod(method);
		logger.debug("method = " + method.getName() + ", " + "query = " + queryName);
		Query namedQuery = getSession().getNamedQuery(queryName);
		for(int i = 0; i < queryArgs.length; i++) 
		{
			Object arg = queryArgs[i];
			namedQuery.setParameter(i, arg);
		}
		return namedQuery.list();
	}
	
	private String queryNameFromMethod(Method finderMethod) {
		return type.getSimpleName() + "." + finderMethod.getName();
	}
}