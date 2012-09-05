// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseStarRepDAO.java

package cosmos.hibernate.base;

import cosmos.hibernate.StarRep;
import cosmos.hibernate.dao.StarRepDAO;
import cosmos.hibernate.dao._RootDAO;
import org.hibernate.*;

public abstract class BaseStarRepDAO extends _RootDAO {

	public BaseStarRepDAO() {
	}

	public static StarRepDAO getInstance() {
		if (null == instance)
			instance = new StarRepDAO();
		return instance;
	}

	public Class getReferenceClass() {
		return cosmos.hibernate.StarRep.class;
	}

	public StarRep load(StarRep key) throws HibernateException {
		return (StarRep) load(getReferenceClass(),
				((java.io.Serializable) (key)));
	}

	public StarRep load(StarRep key, Session s) throws HibernateException {
		return (StarRep) load(getReferenceClass(),
				((java.io.Serializable) (key)), s);
	}

	public StarRep loadInitialize(StarRep key, Session s)
			throws HibernateException {
		StarRep obj = load(key, s);
		if (!Hibernate.isInitialized(obj))
			Hibernate.initialize(obj);
		return obj;
	}

	public StarRep save(StarRep starRep) throws HibernateException {
		return (StarRep) super.save(starRep);
	}

	public StarRep save(StarRep starRep, Session s) throws HibernateException {
		return (StarRep) super.save(starRep, s);
	}

	public void saveOrUpdate(StarRep starRep) throws HibernateException {
		super.saveOrUpdate(starRep);
	}

	public void saveOrUpdate(StarRep starRep, Session s)
			throws HibernateException {
		super.saveOrUpdate(starRep, s);
	}

	public void update(StarRep starRep) throws HibernateException {
		super.update(starRep);
	}

	public void update(StarRep starRep, Session s) throws HibernateException {
		super.update(starRep, s);
	}

	public void delete(StarRep starRep) throws HibernateException {
		super.delete(starRep);
	}

	public void delete(StarRep starRep, Session s) throws HibernateException {
		super.delete(starRep, s);
	}

	public void refresh(StarRep starRep, Session s) throws HibernateException {
		super.refresh(starRep, s);
	}

	public String getDefaultOrderProperty() {
		return null;
	}

	public static StarRepDAO instance;
}
