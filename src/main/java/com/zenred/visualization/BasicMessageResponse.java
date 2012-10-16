package com.zenred.visualization;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("basicMesage")
public class BasicMessageResponse {

	private String theMessage;
	private List<Integer> scaledX;
	private List<Integer> scaledY;
	private List<String> starColors;

	public String getTheMessage() {
		return theMessage;
	}

	public void setTheMessage(String theMessage) {
		this.theMessage = theMessage;
	}



	public List<Integer> getScaledX() {
		return scaledX;
	}

	public void setScaledX(List<Integer> scaledX) {
		this.scaledX = scaledX;
	}

	public List<Integer> getScaledY() {
		return scaledY;
	}

	public void setScaledY(List<Integer> scaledY) {
		this.scaledY = scaledY;
	}

	public List<String> getStarColors() {
		return starColors;
	}

	public void setStarColors(List<String> starColors) {
		this.starColors = starColors;
	}

	@Override
	public String toString() {
		return "BasicMessageResponse [theMessage=" + theMessage + ", scaledX="
				+ scaledX + ", scaledY=" + scaledY + ", starColors="
				+ starColors + "]";
	}
	
	
}
