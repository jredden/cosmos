package com.zenred.cosmos;

public class DistVect {
    
    /**
     * encapsulate a distance Vector
     */

    Double m_xdim;
    Double m_ydim;
    Double m_zdim;

    /**
     * DistVect no arg constructor
     */

     public DistVect (){
	 this(0.0, 0.0, 0.0);
    }

    /**
     * DistVect  constructor
     * @param double x dimension
     * @param double y dimension
     * @param double z dimension
     */
    public DistVect (double xdim, double ydim, double zdim){
	m_xdim = new Double(xdim);
	m_ydim = new Double(ydim);
	m_zdim = new Double(xdim);
    }
}
	
