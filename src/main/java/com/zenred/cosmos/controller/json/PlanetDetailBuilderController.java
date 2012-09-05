package com.zenred.cosmos.controller.json;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.service.MarshalPlanetAndPlanetoids;
import com.zenred.visualization.PlanetDetailResponse;

import cosmos.hibernate.Planetoid;
import cosmos.hibernate.PlanetoidAtmosphereRep;
import cosmos.hibernate.StarRep;

public class PlanetDetailBuilderController implements Controller {
	
	private MarshalPlanetAndPlanetoids marshalPlanetAndPlanetoids;
	private static double EARTHRADIUS = 3958.0;

	public void setMarshalPlanetAndPlanetoids(
			MarshalPlanetAndPlanetoids marshalPlanetAndPlanetoids) {
		this.marshalPlanetAndPlanetoids = marshalPlanetAndPlanetoids;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String planetName = request.getParameter("planetName"); 
		String starId = request.getParameter("starId");
		StarRep starRep = marshalPlanetAndPlanetoids.fetchPlanetsStar(starId);
		Planetoid planetoid = marshalPlanetAndPlanetoids.fetchPlanetoidByPlanetNameAndStarId(planetName, starId);
		List<PlanetoidAtmosphereRep> planetoidAtmosphereRepsList = marshalPlanetAndPlanetoids.fetchPlanetsAtmosphere(planetName, starId);
		List<Planetoid> planetsMoons = marshalPlanetAndPlanetoids.fetchPlanetsMoons(planetName, starId);
		PlanetDetailResponse planetDetailResponse = new PlanetDetailResponse();		
		planetDetailResponse.setStarColor(starRep.getStarColor());
		planetDetailResponse.setStarLuminosity(starRep.getLuminosity());
		planetDetailResponse.setStarSize(starRep.getStarSize());
		planetDetailResponse.setRadius(planetoid.getRadius()/EARTHRADIUS);
		planetDetailResponse.setDistance(1.0/planetoid.getDistanceToPimary());
		planetDetailResponse.setDegree(planetoid.getDegree());
		planetDetailResponse.setTemperature(planetoid.getTemperature());
		for(PlanetoidAtmosphereRep planetoidAtmosphereRep : planetoidAtmosphereRepsList ){
			planetDetailResponse.getChemName().add(planetoidAtmosphereRep.getChem_name());
			planetDetailResponse.getPercentage().add(planetoidAtmosphereRep.getPercentage()+"");
		}
		for(Planetoid moon : planetsMoons){
			planetDetailResponse.getMoonDegree().add(moon.getDegree()+"");
			planetDetailResponse.getMoonDistance().add(moon.getDistanceToPimary()+"");
		}
		ModelAndView modelAndView = new ModelAndView(new PlanetoidDetailView());
		modelAndView.addObject(PlanetoidDetailView.JSON_ROOT, planetDetailResponse);
		return modelAndView;
	}

}
