package com.zenred.util;


public class DrawRandomAlphaNumeric{

    public static String getRandomAlphaNumic(String prefix){
	return prefix + new Double(Math.random()).toString();
    }
}

