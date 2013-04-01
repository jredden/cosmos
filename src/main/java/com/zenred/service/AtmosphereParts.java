package com.zenred.service;

public enum AtmosphereParts {
	
	Methane("methane", "ch[4]", 90.6, 109.0),
    SulfuricAcid("sulfuric acid", "h[1]so[4]", 283.15, 613.15),
    CarbonDioxide("carbon dioxide", "co[1]", 194.5, 216.5),
    Nitrogen("nitrogen", "n[1]", 64.0, 78.0),
    Ammonia("ammonia", "nh[3]", 195.3, 239.6),
    NitrogenDioxide("nitrogen dioxide", "no[1]", 261.8, 294.2),
    Oxygen("oxygen", "o[1]", 54.6, 90.0),
    Water("water", "h[2]o", 273.0, 373.0),
    Neon("neon", "ne[1]", 24.6, 27.1),
    HydrogenSulfide("hydrogen sulfide", "h[1]s", 187.5, 212.3),
    SulferDioxide("sulfer dioxide", "so[1]", 200.8, 263.0),
    Chlorine("chlorine", "cl[1]", 171.9, 238.4),
    Argon("argon", "ar[1]", 83.8, 87.4),
    Helium("helium", "he[1]", 1.0, 1.15),
    Hydrogen("hydrogen", "h[1]", 14.01, 33.0),
    HydrocloricAcid("hydrocloric acid", "h[1]cl[1]", 247.0, 523.15),
    CarbonMonoxide("carbon monoxide", "co[1]", 67.9, 83.0),
    Sodium("sodium", "na[1]", 370.87, 1156.0),
    Potassium("potassium", "k[1]", 336.53, 1032.0),
    Sulfur("sulfer", "s[1]", 388.36, 717.8),
    Carbon("carbon", "c[1]", 3900.0, 5000.0),
    Ozone("ozone", "o[3]", 81.0, 161.0),
    Silicon("silicon", "si[1]", 1673.15, 3073.15),
    Ethane("ethane", "c[2]h[6]", 90.4, 184.6),
    Acetylene("acetylene", "c[2]h[2]", 189.0, 192.0),
    Dicetylene("dicetylene", "c[4]h[2]", 263.0, 283.0),
    Methlacetylene("methacetylene", "ch[3]c=ch", 170.0, 250.0),
    Cyanoacetylene("cyanoacetylene", "c[3]h[1]n[1]", 278.0, 316.0),
    HydrogenCyanide("hudrogen cyanide", "h[1]c[1]n[1]", 260.0, 269.0)
    ;
	
    private AtmosphereParts(final String text, final String symbol, final Double solid, final Double gas) {
        this.text = text;
        this.symbol = symbol;
        this.solid = solid;
        this.gas = gas;
    }

    private final String text;
    private final String symbol;
	private final Double solid;
	private final Double gas;
	
	public String getText() {
		return text;
	}
	public String getSymbol() {
		return symbol;
	}
	public Double getSolid() {
		return solid;
	}
	public Double getGas() {
		return gas;
	}
			
			
		
}
