package com.zenred.cosmos.dao.base;

import java.util.List;

import cosmos.hibernate.ClusterRep;

public interface IStarsAvecPlanets extends GenericDao {
	List<ClusterRep> findAll();
	List<ClusterRep> findByPlanetsAllowed();
}
