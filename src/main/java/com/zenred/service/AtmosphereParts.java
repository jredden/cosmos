package com.zenred.service;

public enum AtmosphereParts {
	
	Methane("methane", "ch[4]", 90.6, 109.0),
    SulfuricAcid("sulfuric acid", "h[1]so[4]", 283.15, 613.15)
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
