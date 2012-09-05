// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StarsAvecPlanetsDAO.java

package com.zenred.data_access;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.zenred.cosmos.dao.base.IStarsFund;

import cosmos.hibernate.StarRep;


public class StarsFundDAO implements IStarsFund{
	
	private HibernateTemplate hibernateTemplate;
	

	public List<StarRep> findStars(String key) {
		return this.hibernateTemplate.find("from cosmos.hibernate.StarRep star_rep where star_rep.systemId = ?", key);
	}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	public List<StarRep> findStarsInCluster(String clusterId) {
		return this.hibernateTemplate.find("from cosmos.hibernate.StarRep star_rep where star_rep.clusterId = ?", clusterId);
	}
    
	public StarRep findStarByStarId(Short starId){
		return (StarRep) this.hibernateTemplate.find("from cosmos.hibernate.StarRep star_rep where star_rep.starId = ?", starId).get(0);
	}

}
