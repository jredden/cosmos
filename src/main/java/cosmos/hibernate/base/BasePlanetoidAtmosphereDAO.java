// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasePlanetoidAtmosphereDAO.java

package cosmos.hibernate.base;

import cosmos.hibernate.PlanetoidAtmosphere;
import cosmos.hibernate.dao.PlanetoidAtmosphereDAO;
import cosmos.hibernate.dao._RootDAO;
import org.hibernate.*;

public abstract class BasePlanetoidAtmosphereDAO extends _RootDAO {

	public BasePlanetoidAtmosphereDAO() {
	}

	public static PlanetoidAtmosphereDAO getInstance() {
		if (null == instance)
			instance = new PlanetoidAtmosphereDAO();
		return instance;
	}

	public Class getReferenceClass() {
		return cosmos.hibernate.PlanetoidAtmosphere.class;
	}

	public PlanetoidAtmosphere load(PlanetoidAtmosphere key)
			throws HibernateException {
		return (PlanetoidAtmosphere) load(getReferenceClass(),
				((java.io.Serializable) (key)));
	}

	public PlanetoidAtmosphere load(PlanetoidAtmosphere key, Session s)
			throws HibernateException {
		return (PlanetoidAtmosphere) load(getReferenceClass(),
				((java.io.Serializable) (key)), s);
	}

	public PlanetoidAtmosphere loadInitialize(PlanetoidAtmosphere key, Session s)
			throws HibernateException {
		PlanetoidAtmosphere obj = load(key, s);
		if (!Hibernate.isInitialized(obj))
			Hibernate.initialize(obj);
		return obj;
	}

	public PlanetoidAtmosphere save(PlanetoidAtmosphere planetoidAtmosphere)
			throws HibernateException {
		return (PlanetoidAtmosphere) super.save(planetoidAtmosphere);
	}

	public PlanetoidAtmosphere save(PlanetoidAtmosphere planetoidAtmosphere,
			Session s) throws HibernateException {
		return (PlanetoidAtmosphere) super.save(planetoidAtmosphere, s);
	}

	public void saveOrUpdate(PlanetoidAtmosphere planetoidAtmosphere)
			throws HibernateException {
		super.saveOrUpdate(planetoidAtmosphere);
	}

	public void saveOrUpdate(PlanetoidAtmosphere planetoidAtmosphere, Session s)
			throws HibernateException {
		super.saveOrUpdate(planetoidAtmosphere, s);
	}

	public void update(PlanetoidAtmosphere planetoidAtmosphere)
			throws HibernateException {
		super.update(planetoidAtmosphere);
	}

	public void update(PlanetoidAtmosphere planetoidAtmosphere, Session s)
			throws HibernateException {
		super.update(planetoidAtmosphere, s);
	}

	public void delete(PlanetoidAtmosphere planetoidAtmosphere)
			throws HibernateException {
		super.delete(planetoidAtmosphere);
	}

	public void delete(PlanetoidAtmosphere planetoidAtmosphere, Session s)
			throws HibernateException {
		super.delete(planetoidAtmosphere, s);
	}

	public void refresh(PlanetoidAtmosphere planetoidAtmosphere, Session s)
			throws HibernateException {
		super.refresh(planetoidAtmosphere, s);
	}

	public String getDefaultOrderProperty() {
		return null;
	}

	public static PlanetoidAtmosphereDAO instance;
}
