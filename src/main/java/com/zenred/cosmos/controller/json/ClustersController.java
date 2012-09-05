package com.zenred.cosmos.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.visualization.ClusterResponse;

import cosmos.hibernate.ClusterRep;

public class ClustersController implements Controller {
	
	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;

	public void setMarshallClustersAndStarsAndPlanetsInOneSystem(
			MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem) {
		this.marshallClustersAndStarsAndPlanetsInOneSystem = marshallClustersAndStarsAndPlanetsInOneSystem;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String systemId = (String) request.getSession().getAttribute("systemId");
		if(null == systemId){systemId = request.getParameter("systemId");}
		List <ClusterRep> listClusterRep = marshallClustersAndStarsAndPlanetsInOneSystem.getClusters(systemId);
		List<ClusterResponse> listClusterResponse = new ArrayList<ClusterResponse>();
		for(ClusterRep clusterRep : listClusterRep){
			ClusterResponse clusterResponse = new ClusterResponse();
			clusterResponse.setAngleDegrees(Math.toDegrees(clusterRep.getAngleInRadians()));
			clusterResponse.setClusterDescription(clusterRep.getClusterDescription());
			clusterResponse.setClusterId(clusterRep.getClusterId());
			clusterResponse.setDistanceSysVirtCentre(clusterRep.getDistanceSysVirtCentre());
			clusterResponse.setNumberStarsInCluster(clusterRep.getNumberStarsInCluster());
			clusterResponse.setPlanetsAllowed(clusterRep.getPlanetsAllowed());
			clusterResponse.setScaledX(clusterRep.scaledX());
			clusterResponse.setScaledY(clusterRep.scaledY());
			listClusterResponse.add(clusterResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new ClustersView());
		modelAndView.addObject(ClustersView.JSON_ROOT, listClusterResponse);
		return modelAndView;
	}

}
