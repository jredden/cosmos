package com.zenred.cosmos;

/**
*
*/
public class HyperspaceCoord{

	private Double m_dim_u;
	private Double m_dim_v;

    /**
     * HypersoaceCoord Constructor - no arg
     */
    public HyperspaceCoord(){
	this(0.0, 0.0);
    }

    /**
     * HypersoaceCoord Constructor 
     * @param - double u dimension
     * @param - double v dimension
     */
    public HyperspaceCoord(double udim, double vdim){
	m_dim_u = new Double(udim);
	m_dim_v = new Double(vdim);
    }

    /**
     * getUDim - 
     * @return u dimension of hyperspace
     */
    double getUDim(){
	return m_dim_u.doubleValue();
    }

    /**
     * getVDim - 
     * @return v dimension of hyperspace
     */
    double getVDim(){
	return m_dim_v.doubleValue();
    }

}
