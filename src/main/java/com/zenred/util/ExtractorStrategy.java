package com.zenred.util;

/**
  * Extractor Interface
  */
public interface ExtractorStrategy {

	/** Set the extraction source. */
	public void setSource(Object obj);

	/** Extract data. */
    public Object extract();

    /** Called when extraction is complete. */
    public void finished(Object obj);

}
