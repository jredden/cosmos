package com.zenred.cosmos;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.zenred.util.OrderedArrayListCollection;
/**
 * ClusterRep.java
 *
 *
 * Created: Fri Apr 05 17:08:52 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public class ClusterRepTest  extends TestCase {
	public ClusterRepTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( ClusterRepTest.class );
	}
	public void testClusterRep(){
		assertTrue( true );
	}

}// ClusterRep
