package com.zenred.cosmos.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.data_access.MarshallSystems;
import com.zenred.service.GenerateOneSystem;
import com.zenred.service.MarshalSystemDetails;
import com.zenred.visualization.EmptySystem;
import com.zenred.visualization.SystemPlusSomeDetails;
import com.zenred.visualization.SystemSimpleArray;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.StarRep;
import cosmos.hibernate.SystemRep;

public class PageSystemController implements Controller {

	static private Logger logger = LoggerFactory
			.getLogger(PageSystemController.class);

	/**
	 * pages in one system on client event if it exists.
	 */

	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;

	public void setMarshallClustersAndStarsAndPlanetsInOneSystem(
			MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem) {
		this.marshallClustersAndStarsAndPlanetsInOneSystem = marshallClustersAndStarsAndPlanetsInOneSystem;
	}

	private GenerateOneSystem generateOneSystem;

	public void setGenerateOneSystem(GenerateOneSystem generateOneSystem) {
		this.generateOneSystem = generateOneSystem;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String udim = request.getParameter("udim");
		String vdim = request.getParameter("vdim");
		boolean result = generateOneSystem.doesSystemAllReadyExist(udim, vdim);
		List<SystemPlusSomeDetails> systemPlusSomeDetailsList = null;
		SystemPlusSomeDetails systemPlusSomeDetails = null;

		if (result) {
			SystemRep systemsRep = new MarshallSystems().getOneSystemRep(udim,
					vdim);
			List<ClusterRep> clusterRepList = marshallClustersAndStarsAndPlanetsInOneSystem
					.getClusters(systemsRep.getSystemId());
			List<StarRep> starRepList = marshallClustersAndStarsAndPlanetsInOneSystem
					.getStars(systemsRep.getSystemId());
			systemPlusSomeDetails = new SystemPlusSomeDetails(systemsRep,
					clusterRepList, starRepList);
			logger.info("systemPlusSomeDetails: " + systemPlusSomeDetails
					+ ":::");
		} else {
			systemPlusSomeDetailsList = EmptySystem.one();
			systemPlusSomeDetails = systemPlusSomeDetailsList.get(0);
			systemPlusSomeDetails.setTheMessage(udim + ":" + vdim
					+ " DOES NOT exist");
		}
		ModelAndView modelAndView = new ModelAndView(new SystemDetailView());

		modelAndView.addObject(SystemDetailView.JSON_ROOT,
				SystemSimpleArray.genSimpleArray(systemPlusSomeDetails));
		return modelAndView;
	}

}
