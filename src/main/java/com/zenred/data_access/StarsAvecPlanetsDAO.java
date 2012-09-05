// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StarsAvecPlanetsDAO.java

package com.zenred.data_access;

import com.zenred.cosmos.dao.base.IStarsAvecPlanets;
import com.zenred.util.VectToList;



import cosmos.hibernate.ClusterRep;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class StarsAvecPlanetsDAO {
	
	private SessionFactory sessionFactory;
	private IStarsAvecPlanets istarsavecplanets;
	
	public StarsAvecPlanetsDAO() {
	}

	public List<ClusterRep> readStarSystems() {
		List<ClusterRep> _list = null;
		String system_id = null;
		Session session = sessionFactory.openSession();
//		Clusterrep cluster_rep = (Clusterrep)session.load(Clusterrep.class, system_id);
		_list = istarsavecplanets.findByPlanetsAllowed();
		if(null != session){session.close();}
		return _list;
	}

	private static final String PLANETSALLOWED = "planetsallowed";

	private static final String CLUSTERID = "clusterId";

	private static final String NUMBERSTARSINCLUSTER = "numberStarsInCluster";
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void setStarsAvecPlanets(IStarsAvecPlanets istarsavecplanets){
    	this.istarsavecplanets = istarsavecplanets;
    }

}
