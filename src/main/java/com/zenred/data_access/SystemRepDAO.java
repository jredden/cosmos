// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemRepDAO.java

package com.zenred.data_access;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.zenred.cosmos.SystemRepIF;

import cosmos.hibernate.SystemRep;

public class SystemRepDAO 
{

    public SystemRepDAO()
    {
    }
    
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public List<SystemRep> findAll() {
		return this.hibernateTemplate.find("from cosmos.hibernate.SystemRep system_rep order by ucoordinate, vcoordinate" );
	}
	
	public SystemRep findOne(String udim, String vdim) {
		Object[] params = {new Double(udim), new Double(vdim)};
		return (SystemRep) this.hibernateTemplate.find("from cosmos.hibernate.SystemRep system_rep WHERE ucoordinate = ? AND vcoordinate = ?", params ).get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<SystemRep> findExtent(String udim1, String vdim1, String udim2, String vdim2) {
		Object[] params = {new Double(udim1), new Double(vdim1),new Double(udim2), new Double(vdim2)};
		return this.hibernateTemplate.find("from cosmos.hibernate.SystemRep system_rep WHERE ucoordinate >= ? AND vcoordinate >= ? AND  ucoordinate <= ? AND vcoordinate <= ?", params );
	}

	
	@SuppressWarnings("unchecked")
	public String findSystemRep(String udim, String vdim) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session
				.createSQLQuery(
						"SELECT System_id FROM SystemRep WHERE ucoordinate = :ucoordinate AND vcoordinate = :vcoordinate")
				.addScalar("System_id", Hibernate.STRING);
		query.setDouble("ucoordinate", new Double(udim));
		query.setDouble("vcoordinate", new Double(vdim));
		List<String> systemIds = query.list();
		Hibernate.initialize(systemIds);
		transaction.commit();
		session.close();
		return systemIds.size() == 0 ? null : systemIds.get(0);
	}
	
	public void writeSystemRep(SystemRepIF systemRepIF){
		SystemRep systemRep = new SystemRep();
		systemRep.setDistanceToGalaxyCentre(systemRepIF.getDistanceToGalacticCentre());
		systemRep.setSystemId(systemRepIF.getSystemName());
		systemRep.setUcoordinate(systemRepIF.getUCoord());
		systemRep.setVcoordinate(systemRepIF.getVCoord());
		this.hibernateTemplate.save(systemRep);
	}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    
}
