package com.zenred.cosmos.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.visualization.StarsResponse;

import cosmos.hibernate.StarRep;

public class StarsController implements Controller {

	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;

	public void setMarshallClustersAndStarsAndPlanetsInOneSystem(
			MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem) {
		this.marshallClustersAndStarsAndPlanetsInOneSystem = marshallClustersAndStarsAndPlanetsInOneSystem;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String systemId = request.getParameter("systemId");
		List<StarRep> starRepList = marshallClustersAndStarsAndPlanetsInOneSystem.getStars(systemId);
		List<StarsResponse> listStarResponse = new ArrayList<StarsResponse>();
		for(StarRep starRep : starRepList){
			StarsResponse starResponse = new StarsResponse();
			starResponse.setAngleInDegreesS(starRep.getAngleInRadiansS());
			starResponse.setDistanceClustVirtCentre(starRep.getDistanceClustVirtCentre());
			starResponse.setLuminosity(starRep.getLuminosity());
			starResponse.setNoPlanetsAllowed(starRep.getNoPlanetsAllowed());
			starResponse.setStarColor(starRep.getStarColor());
			starResponse.setStarSize(starRep.getStarSize());
			starResponse.setStarType(starRep.getStarType());
			listStarResponse.add(starResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new StarsView());
		modelAndView.addObject(StarsView.JSON_ROOT, listStarResponse);
		return modelAndView;
	}

}
