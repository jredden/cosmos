// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StarsAvecPlanetsDAO.java

package com.zenred.data_access;

import com.zenred.cosmos.dao.base.IStarsAvecPlanetsFund;
import com.zenred.util.VectToList;



import cosmos.hibernate.ClusterRep;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;


public class StarsAvecPlanetsFundDAO implements IStarsAvecPlanetsFund{
	
	private HibernateTemplate hibernateTemplate;
	

	public List<ClusterRep> findByPlanetsAllowed() {
		return this.hibernateTemplate.find("from cosmos.hibernate.ClusterRep cluster_rep where cluster_rep.planetsAllowed='T'");
	}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    

}
