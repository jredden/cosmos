package com.zenred.cosmos.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.service.MarshalClusterDetails;
import com.zenred.visualization.PlanetoidResponse;

import cosmos.hibernate.Planetoid;
@Deprecated
public class StarDetailController implements Controller {
	
	private MarshalClusterDetails marshalClusterDetails;

	public void setMarshalClusterDetails(MarshalClusterDetails marshalClusterDetails) {
		this.marshalClusterDetails = marshalClusterDetails;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String clusterId = request.getParameter("cluster");
		String systemId = clusterId.split("_")[0];
		String starId = request.getParameter("starNumber");
		String starColor = request.getParameter("starColor");
		List<Planetoid> planetoidList = marshalClusterDetails.planetsWithStar(clusterId, starId);
		List<PlanetoidResponse> planetoidResponseList = new ArrayList<PlanetoidResponse>();
		for(Planetoid planetoid : planetoidList){
			PlanetoidResponse planetoidResponse = new PlanetoidResponse();
			planetoidResponse.setDegree(planetoid.getDegree());
			double radians = Math.toRadians(planetoid.getDegree());
			planetoidResponse.setPlanetPosX(Math.cos(radians));
			planetoidResponse.setPlanetPosY(Math.sin(radians));
			planetoidResponse.setDistanceToPimary(planetoid.getDistanceToPimary());
			planetoidResponse.setPlanetoidId(planetoid.getPlanetoidId());
			planetoidResponse.setTemperature(planetoid.getTemperature());
			planetoidResponseList.add(planetoidResponse);
		}
		ModelAndView modelAndView = new ModelAndView(new PlanetoidView());
		modelAndView.setViewName("/star");
		modelAndView.addObject(PlanetoidView.JSON_ROOT, planetoidResponseList);
		modelAndView.addObject("systemId", systemId);
		modelAndView.addObject("starColor", starColor);
		return modelAndView;
	}

}
