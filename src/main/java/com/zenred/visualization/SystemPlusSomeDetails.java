package com.zenred.visualization;

import java.util.List;

import cosmos.hibernate.ClusterRep;
import cosmos.hibernate.StarRep;

public class SystemPlusSomeDetails {
	
	private String _systemId;
	private String _distanceToGalaxyCentre;
	private String _ucoordinate;
	private String _vcoordinate;
	private List<ClusterRep> clusterRepList;
	private List<StarRep> starRepList;
	
	/**
	 * @return the _systemId
	 */
	public String get_systemId() {
		return _systemId;
	}
	/**
	 * @param systemId the _systemId to set
	 */
	public void set_systemId(String systemId) {
		_systemId = systemId;
	}
	/**
	 * @return the _distanceToGalaxyCentre
	 */
	public String get_distanceToGalaxyCentre() {
		return _distanceToGalaxyCentre;
	}
	/**
	 * @param distanceToGalaxyCentre the _distanceToGalaxyCentre to set
	 */
	public void set_distanceToGalaxyCentre(String distanceToGalaxyCentre) {
		_distanceToGalaxyCentre = distanceToGalaxyCentre;
	}
	/**
	 * @return the _ucoordinate
	 */
	public String get_ucoordinate() {
		return _ucoordinate;
	}
	/**
	 * @param ucoordinate the _ucoordinate to set
	 */
	public void set_ucoordinate(String ucoordinate) {
		_ucoordinate = ucoordinate;
	}
	/**
	 * @return the _vcoordinate
	 */
	public String get_vcoordinate() {
		return _vcoordinate;
	}
	/**
	 * @param vcoordinate the _vcoordinate to set
	 */
	public void set_vcoordinate(String vcoordinate) {
		_vcoordinate = vcoordinate;
	}
	/**
	 * @return the clusterRepList
	 */
	public List<ClusterRep> getClusterRepList() {
		return clusterRepList;
	}
	/**
	 * @param clusterRepList the clusterRepList to set
	 */
	public void setClusterRepList(List<ClusterRep> clusterRepList) {
		this.clusterRepList = clusterRepList;
	}
	/**
	 * @return the starRepList
	 */
	public List<StarRep> getStarRepList() {
		return starRepList;
	}
	/**
	 * @param starRepList the starRepList to set
	 */
	public void setStarRepList(List<StarRep> starRepList) {
		this.starRepList = starRepList;
	}
}