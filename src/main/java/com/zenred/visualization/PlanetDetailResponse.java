package com.zenred.visualization;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("planetDetail")
public class PlanetDetailResponse {
	
	private double starLuminosity;
	private String starColor;
	private double starSize;
	
	private double radius;
	private double distance;
	private double degree;
	private double temperature;
	
	private List<String> chemName;
	private List<String> percentage;
	
	private List<String> moonDistance;
	private List<String> moonDegree;
	
	public PlanetDetailResponse(){
		chemName = new ArrayList<String>();
		percentage = new ArrayList<String>();
		moonDegree = new ArrayList<String>();
		moonDistance = new ArrayList<String>();
	}
	
	public double getStarLuminosity() {
		return starLuminosity;
	}
	public void setStarLuminosity(double starLuminosity) {
		this.starLuminosity = starLuminosity;
	}
	public String getStarColor() {
		return starColor;
	}
	public void setStarColor(String starColor) {
		this.starColor = starColor;
	}
	public double getStarSize() {
		return starSize;
	}
	public void setStarSize(double starSize) {
		this.starSize = starSize;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getDegree() {
		return degree;
	}
	public void setDegree(double degree) {
		this.degree = degree;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public List<String> getChemName() {
		return chemName;
	}
	public void setChemName(List<String> chemName) {
		this.chemName = chemName;
	}
	public List<String> getPercentage() {
		return percentage;
	}
	public void setPercentage(List<String> percentage) {
		this.percentage = percentage;
	}
	public List<String> getMoonDistance() {
		return moonDistance;
	}
	public void setMoonDistance(List<String> moonDistance) {
		this.moonDistance = moonDistance;
	}
	public List<String> getMoonDegree() {
		return moonDegree;
	}
	public void setMoonDegree(List<String> moonDegree) {
		this.moonDegree = moonDegree;
	}
	
	@Override
	public String toString() {
		return "PlanetDetailResponse [starLuminosity=" + starLuminosity
				+ ", starColor=" + starColor + ", starSize=" + starSize
				+ ", radius=" + radius + ", distance=" + distance + ", degree="
				+ degree + ", temperature=" + temperature + ", chemName="
				+ chemName + ", percentage=" + percentage + ", moonDistance="
				+ moonDistance + ", moonDegree=" + moonDegree + "]";
	}
	
	
}
