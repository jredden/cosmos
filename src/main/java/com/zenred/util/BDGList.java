/*
 * BDGList.java
 *
 * Created on May 17, 2002 by Barry Gold
 */

package com.zenred.util;

import java.util.ArrayList;

/**
 *
 * @author  barry gold
 * Extends ArrayList to allow construction with or adding an array.
 */

public class BDGList extends ArrayList
{
    public BDGList(Object [] _array) {
	super();
	add(_array);
    }

    public void add(Object [] _array) {
	int i;
	for (i = 0;  i < _array.length;  ++i)
	    add(_array[i]);
    }
}
