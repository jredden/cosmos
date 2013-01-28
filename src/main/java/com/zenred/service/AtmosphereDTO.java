package com.zenred.service;

import java.util.ArrayList;
import java.util.List;

public class AtmosphereDTO {
	
	private float density;
	
	private List<AtmosphereComponent> atmosphereCompenent;
	
	private Double starLuminosity;
	private Double distanceToPrimary;
	private Double planetRadius;
	private String starClass;
	
	public void stage(double starLuminosity, double distanceToPrimary, double planetRadius, String starClass){
		this.starLuminosity = this.starLuminosity;
		this.distanceToPrimary = this.distanceToPrimary;
		this.planetRadius = planetRadius;
		this.starClass = starClass;
		atmosphereCompenent = new ArrayList<AtmosphereComponent>();
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public List<AtmosphereComponent> getAtmosphereCompenent() {
		return atmosphereCompenent;
	}

	public void setAtmosphereCompenent(List<AtmosphereComponent> atmosphereCompenent) {
		this.atmosphereCompenent = atmosphereCompenent;
	}

	public Double getStarLuminosity() {
		return starLuminosity;
	}

	public void setStarLuminosity(Double starLuminosity) {
		this.starLuminosity = starLuminosity;
	}

	public Double getDistanceToPrimary() {
		return distanceToPrimary;
	}

	public void setDistanceToPrimary(Double distanceToPrimary) {
		this.distanceToPrimary = distanceToPrimary;
	}

	public Double getPlanetRadius() {
		return planetRadius;
	}

	public void setPlanetRadius(Double planetRadius) {
		this.planetRadius = planetRadius;
	}

	public String getStarClass() {
		return starClass;
	}

	public void setStarClass(String starClass) {
		this.starClass = starClass;
	}

}
