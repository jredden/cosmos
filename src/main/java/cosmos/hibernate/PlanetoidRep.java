// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlanetoidRep.java

package cosmos.hibernate;

import cosmos.hibernate.base.BasePlanetoidRep;

import java.util.Calendar;
import java.util.Date;


import com.zenred.data_access.PlanetoidRepIF;

public class PlanetoidRep extends BasePlanetoidRep {

	@Override
	public String toString() {
		return "PlanetoidRep [getSystemId()=" + getSystemId()
				+ ", getClusterId()=" + getClusterId() + ", getPlanetoidId()="
				+ getPlanetoidId() + ", getProfile()=" + getProfile()
				+ ", getDatestamp()=" + getDatestamp() + ", getItsStar()="
				+ getItsStar() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}

	public PlanetoidRep() {
	}

	public PlanetoidRep(String _systemId, String _clusterId,
			String _planetoidId, String _profile, Date _datestamp, String _itsStar) {
		super(_systemId, _clusterId, _planetoidId, _profile, _datestamp, _itsStar);
	}

	public PlanetoidRep(PlanetoidRepIF planetoid_rep) {
		super(planetoid_rep.getSystemId(), planetoid_rep.getClusterId(),
				planetoid_rep.getPlanetoidId(), planetoid_rep.getProfile(),
				new Date(Calendar.getInstance().getTimeInMillis()), planetoid_rep.getItsStar());
	}
}
