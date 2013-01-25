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
    Neon("neon", "ne", 24.6, 27.1)
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
			
			
		
}
