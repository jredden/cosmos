package com.zenred.util;

/** Observer interface for the XMLParser */
public interface SAXBuilderEventObserver {

	/**
	  * Called by the parser when a new Builder
	  * instance is required.
	  */
    public void registerNewBuilder(String tagName);

}
