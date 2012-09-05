package com.zenred.service;

public class Constraint {
	
	private String type;
	private Double numberConstaint;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getNumberConstaint() {
		return numberConstaint;
	}
	public void setNumberConstaint(Double numberConstaint) {
		this.numberConstaint = numberConstaint;
	}
	@Override
	public String toString() {
		return "Constraint [type=" + type + ", numberConstaint="
				+ numberConstaint + "]";
	}
	
	

}
