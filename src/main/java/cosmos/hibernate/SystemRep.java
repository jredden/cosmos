// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemRep.java

package cosmos.hibernate;

import cosmos.hibernate.base.BaseSystemRep;
import java.util.Date;

public class SystemRep extends BaseSystemRep
{

    public SystemRep()
    {
    }

    public SystemRep(String _systemId, Double _distanceToGalaxyCentre, Double _ucoordinate, Double _vcoordinate, Date _datestamp)
    {
        super(_systemId, _distanceToGalaxyCentre, _ucoordinate, _vcoordinate, _datestamp);
    }

	@Override
	public String toString() {
		return "SystemRep [getSystemId()=" + getSystemId()
				+ ", getDistanceToGalaxyCentre()="
				+ getDistanceToGalaxyCentre() + ", getUcoordinate()="
				+ getUcoordinate() + ", getVcoordinate()=" + getVcoordinate()
				+ ", getDatestamp()=" + getDatestamp() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}
}
