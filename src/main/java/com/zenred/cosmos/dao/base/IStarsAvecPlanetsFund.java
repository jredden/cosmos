package com.zenred.cosmos.dao.base;

import java.util.List;

import cosmos.hibernate.ClusterRep;

public interface IStarsAvecPlanetsFund  {
	List<ClusterRep> findByPlanetsAllowed();
}
