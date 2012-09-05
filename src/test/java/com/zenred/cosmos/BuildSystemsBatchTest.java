package com.zenred.cosmos;

import java.util.List;
import java.util.Hashtable;
import java.util.Iterator;

import com.zenred.cosmos.BuildSystemsBatch;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/*
 * BuildSystemsBatch.java
 *
 * Created on April 6, 2001, 8:59 PM
 */



/**
 *
 * @author  jredden
 * @version 
 */
public class BuildSystemsBatchTest  extends TestCase  {
	public BuildSystemsBatchTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( BuildSystemsBatchTest.class );
	}
	/*
	public void testBuildSystemsBatch(){
		boolean _result = true;
		System.out.println("start RunTest0.sh");
        String cmd = "/home/jredden/workspace/cosmos/target/cosmos-1.0/sh/RunTest0.sh";
        Runtime rt = Runtime.getRuntime();
        try{
        	Process _proc = rt.exec(cmd);
//        	for(int _i = 0; _i < 10000000; _i++){}
        }
        catch (Exception gene){
        	System.err.println(gene.getLocalizedMessage());
            gene.printStackTrace();
            _result = false;
        }
        finally{
        	System.out.println("finish RunTest0.sh");
         }
        
    	try{
    		long __mille_sec = 30000; // 30 seconds
    		Thread.sleep(__mille_sec);
    	}
    	catch(InterruptedException ie){
        	System.err.println(ie.getLocalizedMessage());
            ie.printStackTrace();
            _result = false;
    	}
        finally{
        	System.out.println("finish sleep RunTest0.sh");
         }
       	assertTrue(_result);

 	}
 	*/
	
	public void testGen(){
		BuildSystemsBatch buildSystemsBatch = new BuildSystemsBatch();
		String [] argv = {"5", "5", "100003", "100003",  "31"};
		buildSystemsBatch.setNumberSystemsAndCoordinates(argv);
		buildSystemsBatch.actionPerformed();
        System.out.println("BuildSystemsBatch finished ... ");

	}
}
