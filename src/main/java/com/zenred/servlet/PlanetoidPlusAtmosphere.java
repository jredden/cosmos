package com.zenred.servlet;

import java.util.List;

import cosmos.hibernate.PlanetoidAtmosphereRep;
import cosmos.hibernate.PlanetoidRep;
import cosmos.hibernate.Planetoid;

public class PlanetoidPlusAtmosphere {
	
	private PlanetoidRep  planetoid_rep;
	private List<Planetoid> planetoid;
	private List<PlanetoidAtmosphereRep> planetoid_atmosphere_rep;
	
	public PlanetoidRep getPlanetoid_rep() {
		return planetoid_rep;
	}
	public void setPlanetoid_rep(PlanetoidRep _planetoid_rep) {
		this.planetoid_rep = _planetoid_rep;
	}
	public List<PlanetoidAtmosphereRep> getPlanetoid_atmosphere_rep() {
		return planetoid_atmosphere_rep;
	}
	public void setPlanetoid_atmosphere_rep(
			List<PlanetoidAtmosphereRep> planetoid_atmosphere_rep) {
		this.planetoid_atmosphere_rep = planetoid_atmosphere_rep;
	}
	public List<Planetoid> getPlanetoid() {
		return planetoid;
	}
	public void setPlanetoid(List<Planetoid> planetoid) {
		this.planetoid = planetoid;
	}
	
}
