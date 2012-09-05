package com.zenred.cosmos.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.service.MarshalClusterDetails;
import com.zenred.visualization.StarsResponse;

import cosmos.hibernate.StarRep;

public class ClusterDetailController implements Controller {
	
	private MarshalClusterDetails marshalClusterDetails;

	public void setMarshalClusterDetails(MarshalClusterDetails marshalClusterDetails) {
		this.marshalClusterDetails = marshalClusterDetails;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String clusterId = request.getParameter("cluster");
		List<StarRep> listStarRep = marshalClusterDetails.starsInCluster(clusterId);
		System.out.println("listStarRep:"+listStarRep+" : " + clusterId);
		List<StarsResponse> listStarsResponse = new ArrayList<StarsResponse>();
		for(StarRep starRep : listStarRep){
			StarsResponse starResponse = new StarsResponse();
			starResponse.setAngleInDegreesS(starRep.getAngleInRadiansS());
			starResponse.setDistanceClustVirtCentre(starRep.getDistanceClustVirtCentre());
			starResponse.setLuminosity(starRep.getLuminosity());
			starResponse.setStarColor(starRep.getStarColor());
			starResponse.setStarSize(starRep.getStarSize());
			starResponse.setStarType(starRep.getStarType());
			starResponse.setStar_id(starRep.getStarId());
			listStarsResponse.add(starResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new StarsView());
		modelAndView.addObject(StarsView.JSON_ROOT, listStarsResponse);
		return modelAndView;
	}

}
