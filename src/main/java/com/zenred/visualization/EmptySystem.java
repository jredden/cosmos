package com.zenred.visualization;

import java.util.ArrayList;
import java.util.List;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.StarRep;

public class EmptySystem {
	
	public static List<SystemPlusSomeDetails> one(){
		SystemPlusSomeDetails systemPlusSomeDetails = new SystemPlusSomeDetails();
		systemPlusSomeDetails.setClusterRepList(new ArrayList<ClusterRep>());
		systemPlusSomeDetails.setStarRepList(new ArrayList<StarRep>());
		List<SystemPlusSomeDetails> systemPlusSomeDetailsList = new ArrayList<SystemPlusSomeDetails>();
		systemPlusSomeDetailsList.add(systemPlusSomeDetails);
		return systemPlusSomeDetailsList;
	}

}
