package com.zenred.service;

import java.util.ArrayList;
import java.util.List;


import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.data_access.MarshallStars;
import com.zenred.visualization.ClusterPlusSomeDetails;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.Planetoid;
import cosmos.hibernate.StarRep;

public class MarshalClusterDetails {
	

	private MarshallStars marshallStars;
	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;

	public void setMarshallStars(MarshallStars marshallStars) {
		this.marshallStars = marshallStars;
	}
	public void setMarshallClustersAndStarsAndPlanetsInOneSystem(
			MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem) {
		this.marshallClustersAndStarsAndPlanetsInOneSystem = marshallClustersAndStarsAndPlanetsInOneSystem;
	}
	
	
	public List<ClusterPlusSomeDetails> addStars(List<ClusterRep> clusterRepList){
		List<ClusterPlusSomeDetails> clusterPlustSomeDetailList = new ArrayList<ClusterPlusSomeDetails>();
		for(ClusterRep clusterRep : clusterRepList){
			clusterPlustSomeDetailList.add(addOneClustersStars(clusterRep));
		}		
		return clusterPlustSomeDetailList;
	}
	
	public ClusterPlusSomeDetails addOneClustersStars(ClusterRep clusterRep){
		ClusterPlusSomeDetails clusterPlustSomeDetails = new ClusterPlusSomeDetails();
		MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem = new MarshallClustersAndStarsAndPlanetsInOneSystem();
		List<StarRep> starRepList = marshallClustersAndStarsAndPlanetsInOneSystem.getClusterStars(clusterRep.getClusterId());
		clusterPlustSomeDetails.setAngleInRadians(clusterRep.getAngleInRadians());
		clusterPlustSomeDetails.setClusterDescription(clusterRep.getClusterDescription());
		clusterPlustSomeDetails.setClusterId(clusterRep.getClusterId());
		clusterPlustSomeDetails.setDistanceSysVirtCentre(clusterRep.getDistanceSysVirtCentre());
		clusterPlustSomeDetails.setNumberStarsInCluster(clusterRep.getNumberStarsInCluster());
		clusterPlustSomeDetails.setPlanetsAllowed(clusterRep.getPlanetsAllowed());
		clusterPlustSomeDetails.setSystemId(clusterRep.getSystemId());
		clusterPlustSomeDetails.setStarRepList(starRepList);
		
		return clusterPlustSomeDetails;
	}
	
	public List<StarRep> starsInCluster(String clusterId){
		return marshallStars.starsInCluster(clusterId);
	}

	public List<Planetoid> planetsWithStar(String cluster_id, String s_starId){
		Short starId = new Short(s_starId);
		return marshallClustersAndStarsAndPlanetsInOneSystem.getPlanetsByClusterAndStar(cluster_id, starId);
	}


}
