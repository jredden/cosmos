package com.zenred.data_access;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cosmos.hibernate.StarRep;

public class StarRepDAO {

	private HibernateTemplate hibernateTemplate;
	
	public List<StarRep> findBySystemId(String key) {
		return this.hibernateTemplate.find("from cosmos.hibernate.StarRep star_rep where star_rep.systemId=?", key);
	}
	
	public List<StarRep> findByClusterId(String key) {
		return this.hibernateTemplate.find("from cosmos.hibernate.StarRep star_rep where star_rep.clusterId=?", key);
	}
	
	public void writeStarRep(StarRep starRep){
		this.hibernateTemplate.save(starRep);
	}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
}
