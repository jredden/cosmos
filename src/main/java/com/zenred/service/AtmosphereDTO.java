package com.zenred.service;

import java.util.ArrayList;
import java.util.List;

public class AtmosphereDTO {
	
	
	
	private List<AtmosphereComponent> atmosphereCompenent;
	
	private float density;
	
	public void stage(double starLuminosity, double distanceToPrimary, double planetRadius, String starClass){
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


}
