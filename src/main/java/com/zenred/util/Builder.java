package com.zenred.util;
import org.xml.sax.Attributes;

/**
  * Builder interface that represents a
  * complex object.
  */
public interface Builder {

	 /**
	  * Called when a component definition is
	  * parsed with the Attributes, tag name
	  * and value
	  */
    public void setComponent(Attributes attr,
      String tag, String value);

    /*
     * Called by clients to build the components.
     */
    public boolean buildComponents();

}