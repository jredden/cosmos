// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   _BaseRootDAO.java

package cosmos.hibernate.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.type.Type;

public abstract class _BaseRootDAO {

	public _BaseRootDAO() {
	}

	public static void initialize() throws HibernateException {
		initialize((String) null);
	}

	public static void initialize(String configFileName)
			throws HibernateException {
		if (null == configFileName && sessionFactoryMap.size() > 0)
			return;
		if (null != sessionFactoryMap.get(configFileName))
			return;
		Configuration cfg = new Configuration();
		if (null == configFileName)
			cfg.configure();
		else
			cfg.configure(configFileName);
		setSessionFactory(configFileName, cfg.buildSessionFactory());
	}

	protected static void setSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory((String) null, sessionFactory);
	}

	protected static void setSessionFactory(String configFileName,
			SessionFactory sessionFactory) {
		sessionFactoryMap.put(configFileName, sessionFactory);
	}

	protected SessionFactory getSessionFactory() throws HibernateException {
		return getSessionFactory(getConfigurationFileName());
	}

	private static SessionFactory getSessionFactory(String configFile)
			throws HibernateException {
		if (sessionFactoryMap.size() == 1)
			return (SessionFactory) sessionFactoryMap.values().toArray()[0];
		SessionFactory sessionFactory = (SessionFactory) sessionFactoryMap
				.get(configFile);
		if (null == sessionFactory) {
			if (null == configFile)
				throw new RuntimeException(
						"The session factory has not been initialized.");
			else
				throw new RuntimeException((new StringBuilder()).append(
						"The session factory for '").append(configFile).append(
						"' has not been initialized.").toString());
		} else {
			return sessionFactory;
		}
	}

	protected Session getSession() throws HibernateException {
		return createSession();
	}

	public static Session createSession() throws HibernateException {
		return createSession(null);
	}

	public static Session createSession(String configFile)
			throws HibernateException {
		Stack sessionStack = (Stack) threadedSessions.get();
		Session session = null;
		if (null == sessionStack) {
			sessionStack = new Stack();
			threadedSessions.set(sessionStack);
		}
		if (sessionStack.size() > 0) {
			Object arr[] = (Object[]) (Object[]) sessionStack.peek();
			String cf = (String) arr[0];
			if (null == cf)
				session = (Session) arr[1];
			else if (null != cf && null != configFile && cf.equals(configFile))
				session = (Session) arr[1];
			if (null == session) {
				session = getSessionFactory(configFile).openSession();
				arr = new Object[2];
				arr[0] = configFile;
				arr[1] = session;
				sessionStack.push(((Object) (arr)));
			}
		} else {
			session = getSessionFactory(configFile).openSession();
			Object arr[] = new Object[2];
			arr = new Object[2];
			arr[0] = configFile;
			arr[1] = session;
			sessionStack.push(((Object) (arr)));
		}
		return session;
	}

	public String getConfigurationFileName() {
		return null;
	}

	protected abstract Class getReferenceClass();

	public void closeSession() throws HibernateException {
		Stack sessionStack = (Stack) threadedSessions.get();
		if (null != sessionStack) {
			Object arr[] = (Object[]) (Object[]) sessionStack.peek();
			String cf = (String) arr[0];
			if (null == cf) {
				Session session = (Session) arr[1];
				session.close();
				sessionStack.pop();
			} else {
				String configurationFile = getConfigurationFileName();
				if (null != configurationFile && configurationFile.equals(cf)) {
					Session session = (Session) arr[1];
					session.close();
					sessionStack.pop();
				}
			}
		}
	}

	public Transaction beginTransaction(Session s) throws HibernateException {
		return s.beginTransaction();
	}

	public void commitTransaction(Transaction t) throws HibernateException {
		t.commit();
	}

	public List find(String query) throws HibernateException {
		Session s = null;
		List list;
		s = getSession();
		list = find(query, s);
		closeSession();
		return list;
	}

	public List find(String query, Session s) throws HibernateException {
		return s.createQuery(query).list();
	}

	public List findAll() throws HibernateException {
		Session s = null;
		List list;
		s = getSession();
		list = findAll(s);
		closeSession();
		return list;
	}

	public List findAll(Session s) throws HibernateException {
		return findAll(s, getDefaultOrderProperty());
	}

	public List findAll(String orderProperty) throws HibernateException {
		Session s = null;
		List list;
		s = getSession();
		list = findAll(s, orderProperty);
		closeSession();
		return list;
	}

	public List findAll(Session s, String orderProperty)
			throws HibernateException {
		Criteria crit = createCriteria(s);
		if (null != orderProperty)
			crit.addOrder(Order.asc(orderProperty));
		return crit.list();
	}

	protected List findFiltered(String propName, Object filter)
			throws HibernateException {
		return findFiltered(propName, filter, getDefaultOrderProperty());
	}

	protected List findFiltered(String propName, Object filter,
			String orderProperty) throws HibernateException {
		Session s = null;
		List list;
		s = getSession();
		list = findFiltered(s, propName, filter, getDefaultOrderProperty());
		closeSession();
		return list;
	}

	protected List findFiltered(Session s, String propName, Object filter,
			String orderProperty) throws HibernateException {
		Criteria crit = createCriteria(s);
		crit.add(Expression.eq(propName, filter));
		if (null != orderProperty)
			crit.addOrder(Order.asc(orderProperty));
		return crit.list();
	}

	public List getNamedQuery(String name) throws HibernateException {
		Session s = null;
		List list;
		s = getSession();
		list = getNamedQuery(name, s);
		closeSession();
		return list;
	}

	public List getNamedQuery(String name, Session s) throws HibernateException {
		Query q = s.getNamedQuery(name);
		return q.list();
	}

	public List getNamedQuery(String name, Serializable params[])
			throws HibernateException {
		Session s = null;
		List list;
		s = getSession();
		list = getNamedQuery(name, params, s);
		closeSession();
		return list;
	}

	public List getNamedQuery(String name, Serializable params[], Session s)
			throws HibernateException {
		Query q = s.getNamedQuery(name);
		if (null != params) {
			for (int i = 0; i < params.length; i++)
				setParameterValue(q, i, params[i]);

		}
		return q.list();
	}

	public List getNamedQuery(String name, Map params)
			throws HibernateException {
		Session s = null;
		List list;
		s = getSession();
		list = getNamedQuery(name, params, s);
		closeSession();
		return list;
	}

	public List getNamedQuery(String name, Map params, Session s)
			throws HibernateException {
		Query q = s.getNamedQuery(name);
		if (null != params) {
			java.util.Map.Entry entry;
			for (Iterator i = params.entrySet().iterator(); i.hasNext(); setParameterValue(
					q, (String) entry.getKey(), entry.getValue()))
				entry = (java.util.Map.Entry) i.next();

		}
		return q.list();
	}

	public List find(String query, Object obj, Type type)
			throws HibernateException {
		Session s = null;
		List list;
		s = getSession();
		list = find(query, obj, type, s);
		closeSession();
		return list;
	}

	public List find(String query, Object obj, Type type, Session s)
			throws HibernateException {
		org.hibernate.classic.Session _depr_session = (org.hibernate.classic.Session) s;
		return _depr_session.find(query, obj, type);
	}

	public List find(String query, Object obj[], Type type[])
			throws HibernateException {
		Session s = null;
		List list;
		s = getSession();
		list = find(query, obj, type, s);
		closeSession();
		return list;
	}

	public List find(String query, Object obj[], Type type[], Session s)
			throws HibernateException {
		org.hibernate.classic.Session _depr_session = (org.hibernate.classic.Session) s;
		return _depr_session.find(query, obj, type);
	}

	protected Criteria createCriteria() throws HibernateException {
		Session s = getSession();
		return createCriteria(s);
	}

	protected Criteria createCriteria(Session s) throws HibernateException {
		return s.createCriteria(getReferenceClass());
	}

	public String getDefaultOrderProperty() {
		return null;
	}

	protected void setParameterValue(Query query, int position, Object value) {
		if (null == value)
			return;
		if (value instanceof Boolean)
			query.setBoolean(position, ((Boolean) value).booleanValue());
		else if (value instanceof String)
			query.setString(position, (String) value);
		else if (value instanceof Integer)
			query.setInteger(position, ((Integer) value).intValue());
		else if (value instanceof Long)
			query.setLong(position, ((Long) value).longValue());
		else if (value instanceof Float)
			query.setFloat(position, ((Float) value).floatValue());
		else if (value instanceof Double)
			query.setDouble(position, ((Double) value).doubleValue());
		else if (value instanceof BigDecimal)
			query.setBigDecimal(position, (BigDecimal) value);
		else if (value instanceof Byte)
			query.setByte(position, ((Byte) value).byteValue());
		else if (value instanceof Calendar)
			query.setCalendar(position, (Calendar) value);
		else if (value instanceof Character)
			query.setCharacter(position, ((Character) value).charValue());
		else if (value instanceof Timestamp)
			query.setTimestamp(position, (Timestamp) value);
		else if (value instanceof Date)
			query.setDate(position, (Date) value);
		else if (value instanceof Short)
			query.setShort(position, ((Short) value).shortValue());
	}

	protected void setParameterValue(Query query, String key, Object value) {
		if (null == key || null == value)
			return;
		if (value instanceof Boolean)
			query.setBoolean(key, ((Boolean) value).booleanValue());
		else if (value instanceof String)
			query.setString(key, (String) value);
		else if (value instanceof Integer)
			query.setInteger(key, ((Integer) value).intValue());
		else if (value instanceof Long)
			query.setLong(key, ((Long) value).longValue());
		else if (value instanceof Float)
			query.setFloat(key, ((Float) value).floatValue());
		else if (value instanceof Double)
			query.setDouble(key, ((Double) value).doubleValue());
		else if (value instanceof BigDecimal)
			query.setBigDecimal(key, (BigDecimal) value);
		else if (value instanceof Byte)
			query.setByte(key, ((Byte) value).byteValue());
		else if (value instanceof Calendar)
			query.setCalendar(key, (Calendar) value);
		else if (value instanceof Character)
			query.setCharacter(key, ((Character) value).charValue());
		else if (value instanceof Timestamp)
			query.setTimestamp(key, (Timestamp) value);
		else if (value instanceof Date)
			query.setDate(key, (Date) value);
		else if (value instanceof Short)
			query.setShort(key, ((Short) value).shortValue());
	}

	protected Object load(Class refClass, Serializable key)
			throws HibernateException {
		Session s = null;
		Object obj;
		s = getSession();
		obj = load(refClass, key, s);
		closeSession();
		return obj;
	}

	protected Object load(Class refClass, Serializable key, Session s)
			throws HibernateException {
		return s.load(refClass, key);
	}

	protected Serializable save(Object obj) throws HibernateException {
		Exception exception;
		Transaction t = null;
		Session s = null;
		Serializable serializable;
		try {
			s = getSession();
			t = beginTransaction(s);
			Serializable rtn = save(obj, s);
			commitTransaction(t);
			serializable = rtn;
		} catch (HibernateException e) {
			if (null != t)
				t.rollback();
			throw e;
		} finally {
			closeSession();
		}
		closeSession();
		return serializable;
	}

	protected Serializable save(Object obj, Session s)
			throws HibernateException {
		return s.save(obj);
	}

	protected void saveOrUpdate(Object obj) throws HibernateException {
		Exception exception;
		Transaction t = null;
		Session s = null;
		try {
			s = getSession();
			t = beginTransaction(s);
			saveOrUpdate(obj, s);
			commitTransaction(t);
		} catch (HibernateException e) {
			if (null != t)
				t.rollback();
			throw e;
		} finally {
			closeSession();
		}
		closeSession();
	}

	protected void saveOrUpdate(Object obj, Session s)
			throws HibernateException {
		s.saveOrUpdate(obj);
	}

	protected void update(Object obj) throws HibernateException {
		Exception exception;
		Transaction t = null;
		Session s = null;
		try {
			s = getSession();
			t = beginTransaction(s);
			update(obj, s);
			commitTransaction(t);
		} catch (HibernateException e) {
			if (null != t)
				t.rollback();
			throw e;
		} finally {
			closeSession();
		}
		closeSession();
	}

	protected void update(Object obj, Session s) throws HibernateException {
		s.update(obj);
	}

	protected void delete(Object obj) throws HibernateException {
		Exception exception;
		Transaction t = null;
		Session s = null;
		try {
			s = getSession();
			t = beginTransaction(s);
			delete(obj, s);
			commitTransaction(t);
		} catch (HibernateException e) {
			if (null != t)
				t.rollback();
			throw e;
		} finally {
			closeSession();
		}
		closeSession();
	}

	protected void delete(Object obj, Session s) throws HibernateException {
		s.delete(obj);
	}

	protected void refresh(Object obj, Session s) throws HibernateException {
		s.refresh(obj);
	}

	protected static Map sessionFactoryMap = new HashMap();

	protected static ThreadLocal threadedSessions = new ThreadLocal();

}
