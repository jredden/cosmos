package com.zenred.cosmos.dao.base;

import java.util.List;

import cosmos.hibernate.StarRep;

public interface IStarsFund  {
	List<StarRep> findStars(String key);
	List<StarRep> findStarsInCluster(String clusterId);
}
