package com.zenred.util;

/**
  * Translator Interface
  */
public interface TranslatorStrategy {

	/**
	  * Translate data in the 'extractedObject'
	  * provided by the 'source', and return a
	  * DataObject that contains the translated values.
	  */
    public DataObject translate(Object extractedObject,
      String source);

}
