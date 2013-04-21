package com.zenred.service;

public class AtmosphereComponent {
	
	private Double un_normalized_percent;
	private Double normalized_percent;
	private String symbol;
	private Boolean solid = Boolean.FALSE;
	private Boolean liquid = Boolean.FALSE;
	private String visulualized_symbol;
	
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
	public Boolean getLiquid() {
		return liquid;
	}
	public void setLiquid(Boolean liquid) {
		this.liquid = liquid;
	}
	public Double getNormalized_percent() {
		return normalized_percent;
	}
	public void setNormalized_percent(Double normalized_percent) {
		this.normalized_percent = normalized_percent;
	}

	public String getVisulualized_symbol() {
		return visulualized_symbol;
	}
	public void setVisulualized_symbol(String visulualized_symbol) {
		this.visulualized_symbol = visulualized_symbol;
	}
	@Override
	public String toString() {
		return "AtmosphereComponent [un_normalized_percent="
				+ un_normalized_percent + ", normalized_percent="
				+ normalized_percent + ", symbol=" + symbol + ", solid="
				+ solid + ", liquid=" + liquid + ", visulualized_symbol="
				+ visulualized_symbol + "]";
	}
	
	
	
	
	
}
