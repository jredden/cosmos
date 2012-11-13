package com.zenred.bizrule;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * tests domain integrity of sequential series.
 */

public class SequentialIntegrityTest extends TestCase{

	public SequentialIntegrityTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( SequentialIntegrityTest.class );
	}
	public void testSequentialIntegrity(){
		SequentialIntegrity _sequentialIntegrity = new SequentialIntegrity();
		String _ts0 = _sequentialIntegrity.nextInSequence("thestr_0");
		String _ts1 = _sequentialIntegrity.nextInSequence("thestr_0");
		System.out.println("SequentialIntegrityTest.results:" + _ts0 + ":" + _ts1 + ":");
		boolean _ans = !_ts0.equals(_ts1);
		assertTrue(_ans);
	}
}
