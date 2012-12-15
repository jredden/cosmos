package com.zenred.cosmos.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Deprecated
public class PlanetDetailController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String planetoidId = request.getParameter("planet");
		String[] vars = planetoidId.split("_");
		String systemName = vars[0];
		String clusterName = systemName+'_'+vars[1];
		String planetNumber = vars[2];
		String planetName = clusterName+'_'+planetNumber;
		String starId = vars[3];
		request.setAttribute("systemName", systemName);
		request.setAttribute("clusterName", clusterName);
		request.setAttribute("planetNumber", planetNumber);
		request.setAttribute("planetName", planetName);
		request.setAttribute("starId", starId);
		ModelAndView modelAndView = new ModelAndView("planet");
		return modelAndView;
	}

}
