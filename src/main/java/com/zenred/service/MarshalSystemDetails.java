package com.zenred.service;

import java.util.ArrayList;
import java.util.List;

import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.visualization.SystemPlusSomeDetails;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.StarRep;
import cosmos.hibernate.SystemRep;

public class MarshalSystemDetails {

	public static double AU = 149598000.0;

	public List<SystemPlusSomeDetails> addClustersAndStars(
			List<SystemRep> systemRepList) {

		List<SystemPlusSomeDetails> systemPlusSomeDetailsList = new ArrayList<SystemPlusSomeDetails>();

		for (SystemRep systemRep : systemRepList) {
			MarshallClustersAndStarsAndPlanetsInOneSystem _clust_plan_one_sys = new MarshallClustersAndStarsAndPlanetsInOneSystem();
			List<ClusterRep> clusterRepList = _clust_plan_one_sys
					.getClusters(systemRep.getSystemId());
			List<StarRep> starRepList = _clust_plan_one_sys.getStars(systemRep.getSystemId());
			System.out.println("addClustersAndStars:"+systemRep.getSystemId()+":");
			SystemPlusSomeDetails systemPlusSomeDetails = new SystemPlusSomeDetails();
			Double distanceToGalaxyCentre = systemRep
					.getDistanceToGalaxyCentre();
			systemPlusSomeDetails.set_distanceToGalaxyCentre(""
					+ distanceToGalaxyCentre.doubleValue() / AU);
			systemPlusSomeDetails.set_systemId(systemRep.getSystemId());
			systemPlusSomeDetails.set_ucoordinate(systemRep.getUcoordinate()
					.toString());
			systemPlusSomeDetails.set_vcoordinate(systemRep.getVcoordinate()
					.toString());
			systemPlusSomeDetails.setClusterRepList(clusterRepList);
			systemPlusSomeDetails.setStarRepList(starRepList);
			systemPlusSomeDetailsList.add(systemPlusSomeDetails);
		}
		return systemPlusSomeDetailsList;
	}
}
