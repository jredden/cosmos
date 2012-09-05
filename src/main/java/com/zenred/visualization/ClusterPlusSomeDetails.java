package com.zenred.visualization;

import java.util.List;

import cosmos.hibernate.StarRep;

public class ClusterPlusSomeDetails {

	
	private String systemId;
	private Double distanceSysVirtCentre;
	private String planetsAllowed;
	private String clusterId;
	private Double angleInRadians;
	private String clusterDescription;
	private Short numberStarsInCluster;
	private List<StarRep> starRepList;

	
	/**
	 * @return the systemId
	 */
	public String getSystemId() {
		return systemId;
	}
	/**
	 * @param systemId the systemId to set
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	/**
	 * @return the distanceSysVirtCentre
	 */
	public Double getDistanceSysVirtCentre() {
		return distanceSysVirtCentre;
	}
	/**
	 * @param distanceSysVirtCentre the distanceSysVirtCentre to set
	 */
	public void setDistanceSysVirtCentre(Double distanceSysVirtCentre) {
		this.distanceSysVirtCentre = distanceSysVirtCentre;
	}
	/**
	 * @return the planetsAllowed
	 */
	public String getPlanetsAllowed() {
		return planetsAllowed;
	}
	/**
	 * @param planetsAllowed the planetsAllowed to set
	 */
	public void setPlanetsAllowed(String planetsAllowed) {
		this.planetsAllowed = planetsAllowed;
	}
	/**
	 * @return the clusterId
	 */
	public String getClusterId() {
		return clusterId;
	}
	/**
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}
	/**
	 * @return the angleInRadians
	 */
	public Double getAngleInRadians() {
		return angleInRadians;
	}
	/**
	 * @param angleInRadians the angleInRadians to set
	 */
	public void setAngleInRadians(Double angleInRadians) {
		this.angleInRadians = angleInRadians;
	}
	/**
	 * @return the clusterDescription
	 */
	public String getClusterDescription() {
		return clusterDescription;
	}
	/**
	 * @param clusterDescription the clusterDescription to set
	 */
	public void setClusterDescription(String clusterDescription) {
		this.clusterDescription = clusterDescription;
	}
	/**
	 * @return the numberStarsInCluster
	 */
	public Short getNumberStarsInCluster() {
		return numberStarsInCluster;
	}
	/**
	 * @param numberStarsInCluster the numberStarsInCluster to set
	 */
	public void setNumberStarsInCluster(Short numberStarsInCluster) {
		this.numberStarsInCluster = numberStarsInCluster;
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
