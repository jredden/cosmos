
package com.zenred.util;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 *
 */
public class OrderedArrayListCollectionTest  extends TestCase{
	public OrderedArrayListCollectionTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( OrderedArrayListCollectionTest.class );
	}
	public void testOrderedArrayListCollection(){
		assertTrue( true );
	}

}
