package com.zenred.data_access;

import java.util.ArrayList;
import java.util.List;

import com.zenred.cosmos.dao.base.IStarsAvecPlanetsFund;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.StarRep;

public class MarshallStars extends AbstractDataAccess {
	
	public MarshallStars(){
		init();
	}

	public List<StarRep> getStarsWithPlanets() {
		List<StarRep> _star_rep_list = new ArrayList();
		StarsAvecPlanetsFundDAO _starsavecplanetsfund_dao = (StarsAvecPlanetsFundDAO) ctx
				.getBean("starsAvecPlanetsFundDAO");
		StarsFundDAO _starsfund_dao = (StarsFundDAO) ctx
				.getBean("starsFundDAO");
		List<ClusterRep> _list = _starsavecplanetsfund_dao.findByPlanetsAllowed();
		for(ClusterRep _next_clusterrep :_list ){
			List<StarRep> _one_star_rep_list = _starsfund_dao.findStars(_next_clusterrep.getSystemId());
			for(StarRep _one_star_rep : _one_star_rep_list){
				logger.debug("StarRep::"+_one_star_rep.getClusterId());
				_star_rep_list.add(_one_star_rep);
			}
		}
		return _star_rep_list;
	}
	
	public Short getNumberStars(String systemId){
		ClusterRepDAO _cluster_rep_dao = (ClusterRepDAO)ctx
		.getBean("clusterRepDAO");
		short starNumber = (short) _cluster_rep_dao.findNumberStars(systemId);
		System.out.println("starNumber:"+ starNumber+":"+systemId);
		return starNumber;
	}
	
	public List<StarRep> starsInCluster(String clusterId){
		StarsFundDAO starsFundDAO = (StarsFundDAO)ctx.getBean("starsFundDAO");
		return starsFundDAO.findStarsInCluster(clusterId);
	}
	
	public StarRep fetchPlanetsStar(String starId){
		StarsFundDAO starsFundDAO = (StarsFundDAO)ctx.getBean("starsFundDAO");
		return starsFundDAO.findStarByStarId(Short.parseShort(starId));
	}


}
