package com.zenred.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.service.MarshalClusterDetails;
import com.zenred.visualization.ClusterPlusSomeDetails;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.Planetoid;
import cosmos.hibernate.PlanetoidAtmosphereRep;
import cosmos.hibernate.PlanetoidRep;
import cosmos.hibernate.StarRep;
@Deprecated
public class SpringappControllerSystemDetail extends MultiActionController {
	
	private static final Logger logger = Logger.getLogger(SpringappControllerSystemDetail.class);

	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
//		org.apache.log4j.BasicConfigurator.configure();
		String _systemId = request.getParameter("systemId");
		logger.debug("In com.zenred.servlet.SpringappControllerSystemDetail:" +_systemId);

		HashMap<String, Object> map = new HashMap<String, Object>();

		MarshallClustersAndStarsAndPlanetsInOneSystem _clust_plan_one_sys = new MarshallClustersAndStarsAndPlanetsInOneSystem();
		List <ClusterRep> _cluster_rep_list = _clust_plan_one_sys.getClusters(_systemId);
		logger.debug("com.zenred.servlet.SpringappControllerSystemDetail._cluster_rep_list.size:" +_cluster_rep_list.size());

//		for(ClusterRep _cluster_rep : _cluster_rep_list){
//			logger.debug("ClusterDescription" +_cluster_rep.getClusterDescription());
//		}
		MarshalClusterDetails _cluster_details = new MarshalClusterDetails();
		List<ClusterPlusSomeDetails> _cluster_plus_details_list = _cluster_details.addStars(_cluster_rep_list);

		map.put("cluster_list", _cluster_rep_list);	
		map.put("cluster_plus_list", _cluster_plus_details_list);
		List <StarRep> _star_rep_list = _clust_plan_one_sys.getStars(_systemId);
		map.put("star_list", _star_rep_list);
		List <PlanetoidRep> _planetoid_rep_list = _clust_plan_one_sys.getPlanetoids(_systemId);
		
		logger.debug("planetoid_rep_list.size():" + _planetoid_rep_list.size());
		for(PlanetoidRep _planetoid_rep : _planetoid_rep_list){
			logger.debug("PlanetiodRep:" +_planetoid_rep);
		}

		List <PlanetoidPlusAtmosphere> _planetoid_plus_atmosphere_list = new ArrayList<PlanetoidPlusAtmosphere>();
		for(PlanetoidRep _planetoid_rep : _planetoid_rep_list){
			if(null == _planetoid_rep || null ==_planetoid_rep.getPlanetoidId()){
				logger.warn("planetoidId is null:" + _planetoid_rep);
				continue;
				}
			List <Planetoid> _planetoid_list = _clust_plan_one_sys.getPlanetoid(_planetoid_rep.getPlanetoidId());
			List <PlanetoidAtmosphereRep> _planetoid_atmosphere_rep_list = _clust_plan_one_sys.getAtmosphere(_planetoid_rep.getPlanetoidId());
			PlanetoidPlusAtmosphere _planetoid_plus_atmosphere = new PlanetoidPlusAtmosphere();
			_planetoid_plus_atmosphere.setPlanetoid_rep(_planetoid_rep);
			_planetoid_plus_atmosphere.setPlanetoid(_planetoid_list);
			_planetoid_plus_atmosphere.setPlanetoid_atmosphere_rep(_planetoid_atmosphere_rep_list);
			logger.debug("SpringappControllerSystemDetail->add_atmosphere_list:" +_planetoid_atmosphere_rep_list.size());
			_planetoid_plus_atmosphere_list.add(_planetoid_plus_atmosphere);
		}
		map.put("planetoid_list", _planetoid_plus_atmosphere_list);
		logger.debug("SpringappControllerSystemDetail:to the page->" +_planetoid_plus_atmosphere_list);
		return new ModelAndView("systems_detail", map);
	}

}
