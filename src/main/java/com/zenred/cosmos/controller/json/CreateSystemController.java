package com.zenred.cosmos.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.service.GenerateOneSystem;
import com.zenred.visualization.BasicMessageResponse;
import com.zenred.visualization.ClusterResponse;
import com.zenred.visualization.StarsResponse;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.StarRep;

public class CreateSystemController implements Controller {
	
	private GenerateOneSystem generateOneSystem;
	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;
	private List<Integer> scaledX;
	private List<Integer> scaledY;
	private List<String> starColors;


	public void setGenerateOneSystem(GenerateOneSystem generateOneSystem) {
		this.generateOneSystem = generateOneSystem;
	}

	public void setMarshallClustersAndStarsAndPlanetsInOneSystem(
			MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem) {
		this.marshallClustersAndStarsAndPlanetsInOneSystem = marshallClustersAndStarsAndPlanetsInOneSystem;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String udim = request.getParameter("udim");
		String vdim = request.getParameter("vdim");
		boolean result = generateOneSystem.doesSystemAllReadyExist(udim, vdim);
		BasicMessageResponse basicMessageResponse = new BasicMessageResponse();
		List<StarsResponse> listStarResponse = new ArrayList<StarsResponse>();
		List<ClusterResponse> listClusterResponse = new ArrayList<ClusterResponse>();
		if(result){
			basicMessageResponse.setTheMessage(udim+":"+vdim+" already exists");
		}
		else{
			String systemId = request.getParameter("systemId");
			scaledX = new ArrayList<Integer>();
			scaledY = new ArrayList<Integer>();
			starColors = new ArrayList<String>();
			generateOneSystem.generateSystem(udim, vdim);
			basicMessageResponse.setTheMessage(udim+":"+vdim+" generated");
			List <ClusterRep> listClusterRep = marshallClustersAndStarsAndPlanetsInOneSystem.getClusters(systemId);
			for(ClusterRep clusterRep : listClusterRep){
				scaledX.add(clusterRep.scaledX());
				scaledY.add(clusterRep.scaledY());
				System.out.println("CreateSystem.listClusterRepX:"+clusterRep.scaledX());
				System.out.println("CreateSystem.listClusterRepY:"+clusterRep.scaledY());
			}
			List<StarRep> starRepList = marshallClustersAndStarsAndPlanetsInOneSystem.getStars(systemId);
			for(StarRep starRep : starRepList){
				starColors.add(starRep.getStarColor());
				System.out.println("CreateSystem.starClusterRepColor:"+starRep.getStarColor());
			}
			basicMessageResponse.setScaledX(scaledX);
			basicMessageResponse.setScaledY(scaledY);
			basicMessageResponse.setStarColors(starColors);
		}
		System.out.println("basicMessageResponse:"+basicMessageResponse);
		ModelAndView modelAndView = new ModelAndView(new BasicMessageView());
		modelAndView.addObject(BasicMessageView.JSON_ROOT, basicMessageResponse);
		return modelAndView;
	}

}
