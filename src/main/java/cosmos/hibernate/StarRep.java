// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StarRep.java

package cosmos.hibernate;

import cosmos.hibernate.base.BaseStarRep;
import java.util.Date;

public class StarRep extends BaseStarRep {
	
	public String toString() {
		return "StarRep  toString()=" + super.toString() + ", getClass()="
				+ getClass() + "]";
	}

	private static double scalar_value = 2976000000000.0;  // largest distance from cluster centre to star

	public StarRep() {
	}

	public StarRep(Short _starId, String _systemId, String _clusterId,
			Double _distanceClustVirtCentre, Double _luminosity,
			String _noPlanetsAllowed, Double _angleInRadiansS,
			String _starColor, String _starType, Date _datestamp,
			Double _starSize) {
		super(_starId, _systemId, _clusterId, _distanceClustVirtCentre, _luminosity,
				_noPlanetsAllowed, _angleInRadiansS, _starColor, _starType,
				_datestamp, _starSize);
	}

	public String getStarColor() {
		return super.getStarColor().trim();
	}
	
}
