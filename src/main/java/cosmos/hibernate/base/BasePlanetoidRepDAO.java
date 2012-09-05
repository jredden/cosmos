// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasePlanetoidRepDAO.java

package cosmos.hibernate.base;

import cosmos.hibernate.PlanetoidRep;
import cosmos.hibernate.dao.PlanetoidRepDAO;
import cosmos.hibernate.dao._RootDAO;
import org.hibernate.*;

public abstract class BasePlanetoidRepDAO extends _RootDAO {

	public BasePlanetoidRepDAO() {
	}

	public static PlanetoidRepDAO getInstance() {
		if (null == instance)
			instance = new PlanetoidRepDAO();
		return instance;
	}

	public Class getReferenceClass() {
		return cosmos.hibernate.PlanetoidRep.class;
	}

	public PlanetoidRep load(PlanetoidRep key) throws HibernateException {
		return (PlanetoidRep) load(getReferenceClass(),
				((java.io.Serializable) (key)));
	}

	public PlanetoidRep load(PlanetoidRep key, Session s)
			throws HibernateException {
		return (PlanetoidRep) load(getReferenceClass(),
				((java.io.Serializable) (key)), s);
	}

	public PlanetoidRep loadInitialize(PlanetoidRep key, Session s)
			throws HibernateException {
		PlanetoidRep obj = load(key, s);
		if (!Hibernate.isInitialized(obj))
			Hibernate.initialize(obj);
		return obj;
	}

	public PlanetoidRep save(PlanetoidRep planetoidRep)
			throws HibernateException {
		return (PlanetoidRep) super.save(planetoidRep);
	}

	public PlanetoidRep save(PlanetoidRep planetoidRep, Session s)
			throws HibernateException {
		return (PlanetoidRep) super.save(planetoidRep, s);
	}

	public void saveOrUpdate(PlanetoidRep planetoidRep)
			throws HibernateException {
		super.saveOrUpdate(planetoidRep);
	}

	public void saveOrUpdate(PlanetoidRep planetoidRep, Session s)
			throws HibernateException {
		super.saveOrUpdate(planetoidRep, s);
	}

	public void update(PlanetoidRep planetoidRep) throws HibernateException {
		super.update(planetoidRep);
	}

	public void update(PlanetoidRep planetoidRep, Session s)
			throws HibernateException {
		super.update(planetoidRep, s);
	}

	public void delete(PlanetoidRep planetoidRep) throws HibernateException {
		super.delete(planetoidRep);
	}

	public void delete(PlanetoidRep planetoidRep, Session s)
			throws HibernateException {
		super.delete(planetoidRep, s);
	}

	public void refresh(PlanetoidRep planetoidRep, Session s)
			throws HibernateException {
		super.refresh(planetoidRep, s);
	}

	public String getDefaultOrderProperty() {
		return null;
	}

	public static PlanetoidRepDAO instance;
}
