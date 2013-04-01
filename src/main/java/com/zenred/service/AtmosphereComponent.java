package com.zenred.service;

public class AtmosphereComponent {
	
	private Double un_normalized_percent;
	private String symbol;
	private Boolean solid = Boolean.FALSE;
	
	public Double getUn_normalized_percent() {
		return un_normalized_percent;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setUn_normalized_percent(Double un_normalized_percent) {
		this.un_normalized_percent = un_normalized_percent;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public Boolean getSolid() {
		return solid;
	}
	public void setSolid(Boolean solid) {
		this.solid = solid;
	}
	@Override
	public String toString() {
		return "AtmosphereComponent [un_normalized_percent="
				+ un_normalized_percent + ", symbol=" + symbol + ", solid="
				+ solid + "]";
	}
	
	
	
	
	
}
