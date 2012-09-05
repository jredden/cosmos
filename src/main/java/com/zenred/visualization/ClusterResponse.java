package com.zenred.visualization;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cluster")
public class ClusterResponse {
	
    private Double distanceSysVirtCentre;
    private String planetsAllowed;
    private String clusterId;
    private Double angleDegrees;
    private String clusterDescription;
    private Short numberStarsInCluster;
    private int scaledX;
    private int scaledY;
    
	public Double getDistanceSysVirtCentre() {
		return distanceSysVirtCentre;
	}
	public void setDistanceSysVirtCentre(Double distanceSysVirtCentre) {
		this.distanceSysVirtCentre = distanceSysVirtCentre;
	}
	public String getPlanetsAllowed() {
		return planetsAllowed;
	}
	public void setPlanetsAllowed(String planetsAllowed) {
		this.planetsAllowed = planetsAllowed;
	}
	public String getClusterId() {
		return clusterId;
	}
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}
	public Double getAngleDegrees() {
		return angleDegrees;
	}
	public void setAngleDegrees(Double angleDegrees) {
		this.angleDegrees = angleDegrees;
	}
	public String getClusterDescription() {
		return clusterDescription;
	}
	public void setClusterDescription(String clusterDescription) {
		this.clusterDescription = clusterDescription;
	}
	public Short getNumberStarsInCluster() {
		return numberStarsInCluster;
	}
	public void setNumberStarsInCluster(Short numberStarsInCluster) {
		this.numberStarsInCluster = numberStarsInCluster;
	}

	public int getScaledX() {
		return scaledX;
	}
	public void setScaledX(int scaledX) {
		this.scaledX = scaledX;
	}
	public int getScaledY() {
		return scaledY;
	}
	public void setScaledY(int scaledY) {
		this.scaledY = scaledY;
	}
	@Override
	public String toString() {
		return "ClusterResponse [distanceSysVirtCentre="
				+ distanceSysVirtCentre + ", planetsAllowed=" + planetsAllowed
				+ ", clusterId=" + clusterId + ", angleDegrees=" + angleDegrees
				+ ", clusterDescription=" + clusterDescription
				+ ", numberStarsInCluster=" + numberStarsInCluster
				+ ", scaledX=" + scaledX + ", scaledY=" + scaledY + "]";
	}

	
}
