package com.zenred.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cosmos.hibernate.PlanetoidAtmosphere;
import cosmos.hibernate.PlanetoidAtmosphereRep;

public class PlanetoidAtmosphereRepDAO {

	private HibernateTemplate hibernateTemplate;
	protected static final Logger logger = Logger
			.getLogger(PlanetoidAtmosphereRepDAO.class);
	
	public List<PlanetoidAtmosphereRep> findByPlanetoidID(String planetoid_id) {
		return this.hibernateTemplate
				.find(
						"from cosmos.hibernate.PlanetoidAtmosphereRep planetoid_atmosphere_rep where planetoid_atmosphere_rep.planetoidId=?",
						planetoid_id);

	}

	public List<PlanetoidAtmosphereRep> findByPlanetoidID2(String planetoid_id) {
		logger.debug("findByPlanetoId2.planetoid_id:" + planetoid_id);
		SessionFactory _sessf = this.hibernateTemplate.getSessionFactory();
		Session _sess = _sessf.openSession();
		List<PlanetoidAtmosphereRep> _list = _sess
				.createQuery(
						"from cosmos.hibernate.PlanetoidAtmosphereRep planetoid_atmosphere_rep where planetoid_atmosphere_rep.planetoidId=?")
				.setString(0, planetoid_id).list();
		if(null != _sess){_sess.close();}
		return _list;
	}

	public List<PlanetoidAtmosphereRep> findByPlanetoidIDRaw(String planetoid_id) {
		logger.debug("findByPlanetoIdRaw.planetoid_id:" + planetoid_id);

		PreparedStatement _prepared_statement = null;
		ResultSet _result_set = null;

		SessionFactory _sessf = this.hibernateTemplate.getSessionFactory();
		Session _sess = _sessf.openSession();
		Connection _conn = _sess.connection();
		try {
			_prepared_statement = _conn
					.prepareStatement("SELECT planetoid_id, chem_name, percentage FROM PlanetoidAtmosphere WHERE planetoid_id = ?");
			logger.debug("findByPlanetoIdRaw.prepare.select:" + planetoid_id);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		try {
			_prepared_statement.setString(1, planetoid_id);
			logger.debug("findByPlanetoIdRaw.prepare.exec:" + _prepared_statement);
			_result_set = _prepared_statement.executeQuery();
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		List<PlanetoidAtmosphereRep> _list = new ArrayList<PlanetoidAtmosphereRep>();
		try {
			while (_result_set.next()) {
				PlanetoidAtmosphereRep _planetoid_atmosphere_rep = new PlanetoidAtmosphereRep();
				_planetoid_atmosphere_rep.setPlanetoidId(_result_set.getString("planetoid_id"));
				_planetoid_atmosphere_rep.setChem_name(_result_set.getString("chem_name"));
				_planetoid_atmosphere_rep.setPercentage(new Double(_result_set.getDouble("percentage")).doubleValue());
				_list.add(_planetoid_atmosphere_rep);
				logger.debug("findByPlanetoIdRaw.planetoid_id:" + _planetoid_atmosphere_rep.getPlanetoidId());
				logger.debug("findByPlanetoIdRaw.chem_name:" + _planetoid_atmosphere_rep.getChem_name());
				logger.debug("findByPlanetoIdRaw.percentage:" + _planetoid_atmosphere_rep.getPercentage());
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
		
		logger.debug("findByPlanetoIdRaw->exiting:" + _list);
		return _list;
	}
	public void savePlanetoidAtmosphereRep(PlanetoidAtmosphereRep planetoid_atmosphere_rep){
		PlanetoidAtmosphere _planetoid_atmosphere = new PlanetoidAtmosphere(
					planetoid_atmosphere_rep.getPlanetoidId(),
					planetoid_atmosphere_rep.getChem_name(),
					planetoid_atmosphere_rep.getPercentage(),
					planetoid_atmosphere_rep.getDatestamp()
					);
		this.hibernateTemplate.save(_planetoid_atmosphere);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

}
