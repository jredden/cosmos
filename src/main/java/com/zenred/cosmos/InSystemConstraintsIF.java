
package com.zenred.cosmos;

public interface InSystemConstraintsIF {

    static public final String SINGLESTAR = "SINGLESTAR";
    static public final int ISTART = 0;
    static public final int ISINGLESTAR = ISTART;

    static public final String DOUBLESTAR_BINARY = "DOUBLESTAR_BINARY";
    static public final int IDOUBLESTAR_BINARY = ISINGLESTAR + 1;

    static public final String DOUBLESTAR_SPREAD = "DOUBLESTAR_SPREAD";
    static public final int IDOUBLESTAR_SPREAD = IDOUBLESTAR_BINARY + 1;

    static public final String THREESTAR_TRINARY = "THREESTAR_TRINARY";
    static public final int ITHREESTAR_TRINARY = IDOUBLESTAR_SPREAD + 1;
    
    static public final String THREESTAR_BINARYPLUSONE = "THREESTAR_BINARYPLUSONE";
    static public final int ITHREESTAR_BINARYPLUSONE = ITHREESTAR_TRINARY + 1;

    static public final String THREESTAR_SPREAD = "THREESTAR_SPREAD";
    static public final int ITHREESTAR_SPREAD = ITHREESTAR_BINARYPLUSONE + 1;
    
    static public final String FOURSTAR_TRINARYPLUSONE = "FOURSTAR_TRINARYPLUSONE";
    static public final int IFOURSTAR_TRINARYPLUSONE = ITHREESTAR_SPREAD + 1;
    
    static public final String FOURSTAR_2BINARIES = "FOURSTAR_2BINARIES";
    static public final int IFOURSTAR_2BINARIES = IFOURSTAR_TRINARYPLUSONE + 1;
    
    static public final String FOURSTAR_SPREAD = "FOURSTAR_SPREAD";
    static public final int IFOURSTAR_SPREAD = IFOURSTAR_2BINARIES + 1;
    
    static public final String FIVESTAR_FOURSTARSPREADPLUSONE = "FIVESTAR_FOURSTARSPREADPLUSONE";
    static public final int IFIVESTAR_FOURSTARSPREADPLUSONE = IFOURSTAR_SPREAD + 1;
    
    static public final String FIVESTAR_SPREAD = "FIVESTAR_SPREAD";
    static public final int IFIVESTAR_SPREAD = IFIVESTAR_FOURSTARSPREADPLUSONE + 1;
    
    static public final String [] STARCONFIGS = new String [] {
	SINGLESTAR,				// 0
	DOUBLESTAR_BINARY,			//1 
	DOUBLESTAR_SPREAD,			//2
	THREESTAR_TRINARY,			//3 
	THREESTAR_BINARYPLUSONE,		//4 
	THREESTAR_SPREAD,			//5 
	FOURSTAR_TRINARYPLUSONE,		//6 
	FOURSTAR_2BINARIES,			//7 
	FOURSTAR_SPREAD,			//8 
	FIVESTAR_FOURSTARSPREADPLUSONE,		//9 
	FIVESTAR_SPREAD};			//10

    static public final int [] STARNUM = new int [] {
	1,					//0 
	2,					//1 
	2,					//2 
	3,					//3 
	3,					//4 
	3,					//5
	4,					//6
	4,					//7 
	4,					//8
	5,					//9
	5};					//10

    static public final int [] STARMIN = new int [] {
	0,					//0 
	500,					//1 
	700,					//2 
	750,					//3 
	800,					//4
	860,					//5
	900,					//6
	910,					//7
	930,					//8
	980,					//9
	986};					//10
    static public final int [] STARMAX = new int [] {
	499,					//0 
	699,					//1
	749,					//2
	799,					//3
	859,					//4
	899,					//5
	909,					//6
	929,					//7
	979,					//8
	985,					//9
	999};					//10

    // 1/2 a parsec
    static public final double HALFPSEC = 5.952e12;
    static public final double PARSEC = HALFPSEC + HALFPSEC;

    
}
