package com.zenred.service;

public class AtmosphereGenException extends Exception {

	public AtmosphereGenException(String string, Exception e1) {
		super(string, e1);
	}

	public AtmosphereGenException(String _fail) {
		super(_fail);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 10101010L;

}
