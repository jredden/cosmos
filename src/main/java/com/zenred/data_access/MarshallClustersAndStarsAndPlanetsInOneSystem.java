package com.zenred.data_access;

import java.util.List;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.Planetoid;
import cosmos.hibernate.PlanetoidAtmosphereRep;
import cosmos.hibernate.PlanetoidRep;
import cosmos.hibernate.StarRep;

public class MarshallClustersAndStarsAndPlanetsInOneSystem extends
		AbstractDataAccess {

	public MarshallClustersAndStarsAndPlanetsInOneSystem() {
		init();
		// org.apache.log4j.BasicConfigurator.configure();
	}

	public List<ClusterRep> getClusters(String system_id) {
		ClusterRepDAO _cluster_rep_dao = (ClusterRepDAO) ctx
				.getBean("clusterRepDAO");
		List<ClusterRep> _cluster_rep_list = _cluster_rep_dao
				.findBySystemId(system_id);
		return _cluster_rep_list;
	}

	public void writeClusterRep(ClusterRep clusterRep) {
		ClusterRepDAO _cluster_rep_dao = (ClusterRepDAO) ctx
				.getBean("clusterRepDAO");
		_cluster_rep_dao.writeClusterRep(clusterRep);
	}

	public List<StarRep> getStars(String system_id) {
		StarRepDAO _star_rep_dao = (StarRepDAO) ctx.getBean("starRepDAO");
		List<StarRep> _star_rep_list = _star_rep_dao.findBySystemId(system_id);
		return _star_rep_list;
	}
	public List<StarRep> getStarsByClusterId(String cluster_id) {
		StarRepDAO _star_rep_dao = (StarRepDAO) ctx.getBean("starRepDAO");
		List<StarRep> _star_rep_list = _star_rep_dao.findByClusterId(cluster_id);
		return _star_rep_list;
	}
	
	public void writeStarRep (StarRep starRep){
		StarRepDAO _star_rep_dao = (StarRepDAO) ctx.getBean("starRepDAO");
		_star_rep_dao.writeStarRep(starRep);
	}

	public List<StarRep> getClusterStars(String cluster_id) {
		StarRepDAO _star_rep_dao = (StarRepDAO) ctx.getBean("starRepDAO");
		List<StarRep> _star_rep_list = _star_rep_dao
				.findByClusterId(cluster_id);
		return _star_rep_list;
	}

	public List<PlanetoidRep> getPlanetoids(String system_id) {
		PlanetoidRepDAO _planetoid_rep_dao = (PlanetoidRepDAO) ctx
				.getBean("planetoidRepDAO");
		List<PlanetoidRep> _planetoid_rep_list = _planetoid_rep_dao
				.findBySystemIdRaw(system_id);
		return _planetoid_rep_list;
	}

	public List<Planetoid> getPlanetoid(String planetoid_id) {
		PlanetoidDAO _planetoid_dao = (PlanetoidDAO) ctx
				.getBean("planetoidDAO");
		List<Planetoid> _planetoid_list = _planetoid_dao
				.findByPlanetoidId(planetoid_id);
		return _planetoid_list;
	}
	public Planetoid getOnePlanetoid(String planetoid_id) {
		PlanetoidDAO _planetoid_dao = (PlanetoidDAO) ctx
				.getBean("planetoidDAO");
		List<Planetoid> _planetoid_list = _planetoid_dao
				.findByPlanetoidId(planetoid_id);
		return _planetoid_list.get(0);
	}
	
	public List<Planetoid> getPlanetsMoons(String planetoidId){
		PlanetoidDAO _planetoid_dao = (PlanetoidDAO) ctx
				.getBean("planetoidDAO");
		List<Planetoid> _planetoid_list = _planetoid_dao
				.findPlanetoidletsByProfile(planetoidId);
		return _planetoid_list;
		
	}
	
	public List<Planetoid> getPlanetsByClusterAndStar(String cluster_id, Short starId){
		PlanetoidDAO _planetoid_dao = (PlanetoidDAO) ctx
				.getBean("planetoidDAO");
		List<Planetoid> _planetoid_list = _planetoid_dao
				.findPlanetsByClusterAndStar(cluster_id, starId);
		return _planetoid_list;
	}

	public List<PlanetoidAtmosphereRep> getAtmosphere(String planetoid_id) {
		logger.info(this.getClass().getName() + ":planetoid_id");
		PlanetoidAtmosphereRepDAO _planetoid_atmosphere_rep_dao = (PlanetoidAtmosphereRepDAO) ctx
				.getBean("planetoidAtmosphereRepDAO");
		List<PlanetoidAtmosphereRep> _planetoid_atmosphere_rep_list = _planetoid_atmosphere_rep_dao
				.findByPlanetoidIDRaw(planetoid_id);
		return _planetoid_atmosphere_rep_list;
	}
}
