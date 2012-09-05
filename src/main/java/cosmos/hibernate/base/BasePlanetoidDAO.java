// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasePlanetoidDAO.java

package cosmos.hibernate.base;

import cosmos.hibernate.Planetoid;
import cosmos.hibernate.dao.PlanetoidDAO;
import cosmos.hibernate.dao._RootDAO;
import org.hibernate.*;

public abstract class BasePlanetoidDAO extends _RootDAO {

	public BasePlanetoidDAO() {
	}

	public static PlanetoidDAO getInstance() {
		if (null == instance)
			instance = new PlanetoidDAO();
		return instance;
	}

	public Class getReferenceClass() {
		return cosmos.hibernate.Planetoid.class;
	}

	public Planetoid load(Planetoid key) throws HibernateException {
		return (Planetoid) load(getReferenceClass(),
				((java.io.Serializable) (key)));
	}

	public Planetoid load(Planetoid key, Session s) throws HibernateException {
		return (Planetoid) load(getReferenceClass(),
				((java.io.Serializable) (key)), s);
	}

	public Planetoid loadInitialize(Planetoid key, Session s)
			throws HibernateException {
		Planetoid obj = load(key, s);
		if (!Hibernate.isInitialized(obj))
			Hibernate.initialize(obj);
		return obj;
	}

	public Planetoid save(Planetoid planetoid) throws HibernateException {
		return (Planetoid) super.save(planetoid);
	}

	public Planetoid save(Planetoid planetoid, Session s)
			throws HibernateException {
		return (Planetoid) super.save(planetoid, s);
	}

	public void saveOrUpdate(Planetoid planetoid) throws HibernateException {
		super.saveOrUpdate(planetoid);
	}

	public void saveOrUpdate(Planetoid planetoid, Session s)
			throws HibernateException {
		super.saveOrUpdate(planetoid, s);
	}

	public void update(Planetoid planetoid) throws HibernateException {
		super.update(planetoid);
	}

	public void update(Planetoid planetoid, Session s)
			throws HibernateException {
		super.update(planetoid, s);
	}

	public void delete(Planetoid planetoid) throws HibernateException {
		super.delete(planetoid);
	}

	public void delete(Planetoid planetoid, Session s)
			throws HibernateException {
		super.delete(planetoid, s);
	}

	public void refresh(Planetoid planetoid, Session s)
			throws HibernateException {
		super.refresh(planetoid, s);
	}

	public String getDefaultOrderProperty() {
		return null;
	}

	public static PlanetoidDAO instance;
}
