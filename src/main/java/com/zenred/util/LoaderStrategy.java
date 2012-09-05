package com.zenred.util;

/**
  * Loader Interface
  */
public interface LoaderStrategy {

	/** Load the data in the DataObject */
    public void load(DataObject objectToLoad);

}
