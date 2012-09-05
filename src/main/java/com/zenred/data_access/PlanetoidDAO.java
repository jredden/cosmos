package com.zenred.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import org.apache.log4j.Logger;

import cosmos.hibernate.Planetoid;

public class PlanetoidDAO {
	
	private HibernateTemplate hibernateTemplate;
	
	protected static final Logger logger = Logger
	.getLogger(PlanetoidDAO.class);
	
	public PlanetoidDAO(){
//		org.apache.log4j.BasicConfigurator.configure();
	}
	
	public List<Planetoid> findByPlanetoidId(String planetoid_id) {
		return this.hibernateTemplate.find("from cosmos.hibernate.Planetoid planetoid where planetoid.planetoidId=?", planetoid_id);
	}

	public void savePlanetoid(Planetoid planetoid){
		this.hibernateTemplate.save(planetoid);
	}
	
	public List<Planetoid> findPlanetsByClusterAndStar(String clusterId,
			Short starId) {
		String queryPlanets = "SELECT " + "pl.planetoid_id" + ", pl.Radius"
				+ ", pl.DistanceToPimary" + ", pl.Degree" + ", pl.Temperature "
				+ ", pl.PercentWater " + ", pl.Datestamp " + "FROM"
				+ " PlanetoidRep pr JOIN Planetoid pl "
				+ "ON pr.planetoid_id = pl.planetoid_id "
				+ "WHERE pr.cluster_id = :clusterId"
				+ " AND pr.itsStar = :itsStar"
				+ " AND pr.planetoid_id NOT LIKE '%PLANETOIDLET'";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Query query = session.createSQLQuery(queryPlanets).addEntity(
				Planetoid.class);
		query.setParameter("clusterId", clusterId);
		query.setParameter("itsStar", starId);
		List<Planetoid> planetoidList = query.list();
		if (null != session) {
			session.close();
		}
		return planetoidList;
	}
	
	public List<Planetoid> findPlanetoidletsByProfile(String planetoidId){
		String queryPlanets = "SELECT " + "pl.planetoid_id" + ", pl.Radius"
				+ ", pl.DistanceToPimary" + ", pl.Degree" + ", pl.Temperature "
				+ ", pl.PercentWater " + ", pl.Datestamp " + "FROM"
				+ " PlanetoidRep pr JOIN Planetoid pl "
				+ "ON pr.planetoid_id = pl.planetoid_id "
				+ "WHERE pr.profile = :profile";
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Query query = session.createSQLQuery(queryPlanets).addEntity(
				Planetoid.class);
		query.setParameter("profile", planetoidId);
		List<Planetoid> planetoidList = query.list();
		if (null != session) {
			session.close();
		}
		return planetoidList;
	}
   	
   public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


}
