package com.zenred.visualization;


public class PlanetoidResponse {
	
	private String planetoidId;
	private Double distanceToPimary;
	private Double degree;
	private Double temperature;
	private Double planetPosX;
	private Double planetPosY;
	
	public String getPlanetoidId() {
		return planetoidId;
	}
	public void setPlanetoidId(String planetoidId) {
		this.planetoidId = planetoidId;
	}
	public Double getDistanceToPimary() {
		return distanceToPimary;
	}
	public void setDistanceToPimary(Double distanceToPimary) {
		this.distanceToPimary = distanceToPimary;
	}
	public Double getDegree() {
		return degree;
	}
	public void setDegree(Double degree) {
		this.degree = degree;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	
	public Double getPlanetPosX() {
		return planetPosX;
	}
	public void setPlanetPosX(Double planetPosX) {
		this.planetPosX = planetPosX;
	}
	public Double getPlanetPosY() {
		return planetPosY;
	}
	public void setPlanetPosY(Double planetPosY) {
		this.planetPosY = planetPosY;
	}
	@Override
	public String toString() {
		return "PlanetoidResponse [planetoidId=" + planetoidId
				+ ", distanceToPimary=" + distanceToPimary + ", degree="
				+ degree + ", temperature=" + temperature + ", planetPosX="
				+ planetPosX + ", planetPosY=" + planetPosY + "]";
	}
	
	

}
