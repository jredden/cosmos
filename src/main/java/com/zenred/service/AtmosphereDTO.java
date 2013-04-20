package com.zenred.service;

import java.util.ArrayList;
import java.util.List;

public class AtmosphereDTO {
	
	
	
	private List<AtmosphereComponent> atmosphereCompenent;
	
	private Double density;
	
	public void stage(double starLuminosity, double distanceToPrimary, double planetRadius, String starClass){
		atmosphereCompenent = new ArrayList<AtmosphereComponent>();
	}
	
	public void stage(){
		atmosphereCompenent = new ArrayList<AtmosphereComponent>();
	}

	public Double getDensity() {
		return density;
	}

	public void setDensity(Double density) {
		this.density = density;
	}

	public List<AtmosphereComponent> getAtmosphereCompenent() {
		return atmosphereCompenent;
	}

	public void setAtmosphereCompenent(List<AtmosphereComponent> atmosphereCompenent) {
		this.atmosphereCompenent = atmosphereCompenent;
	}

	@Override
	public String toString() {
		return "AtmosphereDTO [atmosphereCompenent=" + atmosphereCompenent
				+ ", density=" + density + "]";
	}


}
