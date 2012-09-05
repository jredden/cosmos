package com.zenred.visualization;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("stars")
public class StarsResponse {
	
    private Double distanceClustVirtCentre;
    private Double luminosity;
    private String noPlanetsAllowed;
    private Double angleInDegreesS;
    private String starColor;
    private String starType;
    private Double starSize;
    private Short star_id;
    
	public Double getDistanceClustVirtCentre() {
		return distanceClustVirtCentre;
	}
	public void setDistanceClustVirtCentre(Double distanceClustVirtCentre) {
		this.distanceClustVirtCentre = distanceClustVirtCentre;
	}
	public Double getLuminosity() {
		return luminosity;
	}
	public void setLuminosity(Double luminosity) {
		this.luminosity = luminosity;
	}
	public String getNoPlanetsAllowed() {
		return noPlanetsAllowed;
	}
	public void setNoPlanetsAllowed(String noPlanetsAllowed) {
		this.noPlanetsAllowed = noPlanetsAllowed;
	}
	public Double getAngleInDegreesS() {
		return angleInDegreesS;
	}
	public void setAngleInDegreesS(Double angleInDegreesS) {
		this.angleInDegreesS = angleInDegreesS;
	}
	public String getStarColor() {
		return starColor;
	}
	public void setStarColor(String starColor) {
		this.starColor = starColor;
	}
	public String getStarType() {
		return starType;
	}
	public void setStarType(String starType) {
		this.starType = starType;
	}
	public Double getStarSize() {
		return starSize;
	}
	public void setStarSize(Double starSize) {
		this.starSize = starSize;
	}
	public Short getStar_id() {
		return star_id;
	}
	public void setStar_id(Short star_id) {
		this.star_id = star_id;
	}
	
	@Override
	public String toString() {
		return "StarsResponse [distanceClustVirtCentre="
				+ distanceClustVirtCentre + ", luminosity=" + luminosity
				+ ", noPlanetsAllowed=" + noPlanetsAllowed
				+ ", angleInDegreesS=" + angleInDegreesS + ", starColor="
				+ starColor + ", starType=" + starType + ", starSize="
				+ starSize + ", star_id=" + star_id + "]";
	}

}
