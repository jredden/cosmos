
package com.zenred.util;
import java.util.LinkedList;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 *
 */
public class OrderedLinkedListCollectionTest  extends TestCase{
	public OrderedLinkedListCollectionTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( OrderedLinkedListCollectionTest.class );
	}
	public void testOrderedLinkedListCollection(){
		assertTrue( true );
	}

}
