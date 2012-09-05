// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClusterRepDAO.java

package com.zenred.data_access;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cosmos.hibernate.ClusterRep;

// Referenced classes of package com.zenred.cosmos:
//            OraAbstractDAO

public class ClusterRepDAO 
{
	private HibernateTemplate hibernateTemplate;
	protected static final Logger logger = Logger
	.getLogger(ClusterRepDAO.class);
	
	public List<ClusterRep> findBySystemId(String key) {
		return this.hibernateTemplate.find("from cosmos.hibernate.ClusterRep cluster_rep where cluster_rep.systemId=?", key);
	}
	
	public int findNumberStars(String key) {
		Session session =  this.hibernateTemplate.getSessionFactory().openSession();
		Query query = session.createSQLQuery("SELECT number_stars_in_cluster FROM ClusterRep WHERE system_Id = :systemId").addScalar("number_stars_in_cluster", Hibernate.INTEGER);
		query.setString("systemId", key);
		int numberStars = (Integer) query.uniqueResult();
		if(null != session){session.close();}
		return numberStars;

	}
	
    public void writeClusterRep(ClusterRep clusterRep){
    	this.hibernateTemplate.save(clusterRep);
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    

    
}
