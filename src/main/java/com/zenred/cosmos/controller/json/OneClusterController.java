package com.zenred.cosmos.controller.json;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.data_access.MarshallSystems;
import com.zenred.visualization.SystemPlusSomeDetails;
import com.zenred.visualization.SystemSimpleArray;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.StarRep;
import cosmos.hibernate.SystemRep;

public class OneClusterController implements Controller {
	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;

	public void setMarshallClustersAndStarsAndPlanetsInOneSystem(
			MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem) {
		this.marshallClustersAndStarsAndPlanetsInOneSystem = marshallClustersAndStarsAndPlanetsInOneSystem;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String udim = request.getParameter("udim");
		String vdim = request.getParameter("vdim");

		SystemRep systemsRep = new MarshallSystems()
				.getOneSystemRep(udim, vdim);
		List<ClusterRep> clusterRepList = marshallClustersAndStarsAndPlanetsInOneSystem
				.getClusters(systemsRep.getSystemId());
		List<StarRep> starRepList = marshallClustersAndStarsAndPlanetsInOneSystem
				.getStars(systemsRep.getSystemId());
		SystemPlusSomeDetails systemPlusSomeDetails = new SystemPlusSomeDetails(
				systemsRep, clusterRepList, starRepList);

		ModelAndView modelAndView = new ModelAndView(new SystemDetailView());

		modelAndView.addObject(SystemDetailView.JSON_ROOT,
				SystemSimpleArray.genSimpleArray(systemPlusSomeDetails));
		return modelAndView;
	}

}
