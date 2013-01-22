package com.zenred.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import org.apache.log4j.Logger;

import cosmos.hibernate.PlanetoidRep;

public class PlanetoidRepDAO {
	
	private HibernateTemplate hibernateTemplate;
	
	protected static final Logger logger = Logger
	.getLogger(ClusterRepDAO.class);
	
	public PlanetoidRepDAO(){
//		org.apache.log4j.BasicConfigurator.configure();
	}
	
	public void savePlanetoidRep(PlanetoidRep planetoid_rep){
		System.out.println("PlanetoidRep:"+planetoid_rep);
		this.hibernateTemplate.save(planetoid_rep);
	}
	
	public List<PlanetoidRep> findBySystemId(String system_id) {
		return this.hibernateTemplate.find("from cosmos.hibernate.PlanetoidRep planetoid_rep where planetoid_rep.systemId=?", system_id);
	}
	public List<PlanetoidRep> findBySystemId2(String system_id) {
		logger.debug("findBySystemId2.system_id:" + system_id);
		SessionFactory _sessf = this.hibernateTemplate.getSessionFactory();
		Session _sess =  _sessf.openSession();
		List<PlanetoidRep> _list = _sess.createQuery("from cosmos.hibernate.PlanetoidRep planetoid_rep where planetoid_rep.systemId=?").setString(0, system_id).list();
		if(null != _sess){_sess.close();}
		return _list;
	}
	
	public List<PlanetoidRep> findBySystemIdRaw(String system_id){
		PreparedStatement _prepared_statement = null;
		ResultSet _result_set = null;
		logger.debug("findBySystemIdRaw.system_id:" + system_id);
		SessionFactory _sessf = this.hibernateTemplate.getSessionFactory();
		Session _sess =  _sessf.openSession();
		Connection _conn = _sess.connection();
		try {
			_prepared_statement = _conn.prepareStatement("SELECT System_id, cluster_id, planetoid_id FROM PlanetoidRep WHERE System_id = ?");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		try {
			_prepared_statement.setString(1, system_id);
			_result_set = _prepared_statement.executeQuery();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		List<PlanetoidRep> _list = new ArrayList<PlanetoidRep>();
		try {
			while (_result_set.next()) {
				PlanetoidRep _planetoid_rep = new PlanetoidRep();
				_planetoid_rep.setClusterId(_result_set.getString("cluster_id"));
				_planetoid_rep.setSystemId(_result_set.getString("System_id"));
				_planetoid_rep.setPlanetoidId(_result_set.getString("planetoid_id"));
				_list.add(_planetoid_rep);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		if(null != _conn){
			try {
				_conn.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			
		}
		if(null != _sess){
			_sess.close();
		}

		return _list;
	}
	
   public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

}
