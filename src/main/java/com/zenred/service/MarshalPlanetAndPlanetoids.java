package com.zenred.service;

import java.util.List;

import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.data_access.MarshallStars;
import cosmos.hibernate.Planetoid;
import cosmos.hibernate.PlanetoidAtmosphereRep;
import cosmos.hibernate.StarRep;

public class MarshalPlanetAndPlanetoids {
	
	private MarshallStars marshallStars;
	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;
	
	public void setMarshallClustersAndStarsAndPlanetsInOneSystem(
			MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem) {
		this.marshallClustersAndStarsAndPlanetsInOneSystem = marshallClustersAndStarsAndPlanetsInOneSystem;
	}

	
	public void setMarshallStars(MarshallStars marshallStars) {
		this.marshallStars = marshallStars;
	}


	public StarRep fetchPlanetsStar(String starId){
		return marshallStars.fetchPlanetsStar(starId);
	}
	
	public Planetoid fetchPlanetoidByPlanetNameAndStarId(String planetName, String starId){
		return marshallClustersAndStarsAndPlanetsInOneSystem.getOnePlanetoid(planetName+'_'+starId);
	}
	
	public List<PlanetoidAtmosphereRep> fetchPlanetsAtmosphere(String planetName, String starId){
		return marshallClustersAndStarsAndPlanetsInOneSystem.getAtmosphere(planetName+'_'+starId);
	}
	
	public List<Planetoid> fetchPlanetsMoons(String planetName, String starId){
		return marshallClustersAndStarsAndPlanetsInOneSystem.getPlanetsMoons(planetName+'_'+starId);
	}

}
