// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClusterRep.java

package cosmos.hibernate;

import cosmos.hibernate.base.BaseClusterRep;
import java.util.Date;

public class ClusterRep extends BaseClusterRep {
	private static double scalar_value = 20108108108.0; // 148 div
														// max(distanceSysVirtCentre)
	
	private static double scalar_value_large = 7534177215.1493670886; // scale 395

	public ClusterRep() {
	}

	public ClusterRep(String _systemId, Double _distanceSysVirtCentre,
			String _planetsAllowed, String _clusterId, Double _angleInRadians,
			String _clusterDescription, Short _numberStarsInCluster,
			Date _datestamp) {
		super(_systemId, _distanceSysVirtCentre, _planetsAllowed, _clusterId,
				_angleInRadians, _clusterDescription, _numberStarsInCluster,
				_datestamp);
	}
	
	private int scaleForView(double dim){
/*		
		if(dim < 5 && dim > 0){
			dim *=12;
		}
		else{
			if(dim < 12 && dim >= 6){
				dim *=3;
			}
		}
*/
		if(dim < -74){dim = -60;} // 74 about half for cluster pos visualization
		if(dim > 74){dim = 60;}
		return (int)dim;
	}
	
	
	public int scaledX(){
		double scaledDelta = super.getDistanceSysVirtCentre().doubleValue()/scalar_value;
		double d_x = scaledDelta * Math.cos(super.getAngleInRadians().doubleValue());
		return scaleForView(d_x);
	}
	
	public int scaledY(){
		double scaledDelta = super.getDistanceSysVirtCentre().doubleValue()/scalar_value;
		double d_y = scaledDelta * Math.sin(super.getAngleInRadians().doubleValue());
		return scaleForView(d_y);
	}
	public int scaledX2(){
		double scaledDelta = super.getDistanceSysVirtCentre().doubleValue()/scalar_value_large;
		double d_x = scaledDelta * Math.cos(super.getAngleInRadians().doubleValue());
		return (int)d_x;
	}
	
	public int scaledY2(){
		double scaledDelta = super.getDistanceSysVirtCentre().doubleValue()/scalar_value_large;
		double d_y = scaledDelta * Math.sin(super.getAngleInRadians().doubleValue());
		return (int)d_y;
	}

	public int getScaledX(){
		return scaledX();
	}
	
	public int getScaledY(){
		return scaledY();
	}
	
	@Override
	public String toString() {
		return "ClusterRep  toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}


	
}
