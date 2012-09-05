// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemRepDAO.java

package com.zenred.data_access;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sun.org.apache.regexp.internal.recompile;
import com.zenred.cosmos.SystemRepIF;

import cosmos.hibernate.SystemRep;

public class SystemRepDAO 
{

    public SystemRepDAO()
    {
    }
    
	private HibernateTemplate hibernateTemplate;
	
	public List<SystemRep> findAll() {
		return this.hibernateTemplate.find("from cosmos.hibernate.SystemRep system_rep" );
	}
	
	
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
