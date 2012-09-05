// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Planetoid.java

package cosmos.hibernate;

import cosmos.hibernate.base.BasePlanetoid;
import java.util.Date;

public class Planetoid extends BasePlanetoid
{

    public Planetoid()
    {
    }

    public Planetoid(String _planetoidId, double _radius, double _distanceToPimary, double _degree, 
            double _temperature, Double _percentWater, Date _datestamp)
    {
        super(_planetoidId, _radius, _distanceToPimary, _degree, _temperature, _percentWater, _datestamp);
    }

	@Override
	public String toString() {
		return "Planetoid [getPlanetoidId()=" + getPlanetoidId()
				+ ", getRadius()=" + getRadius() + ", getDistanceToPimary()="
				+ getDistanceToPimary() + ", getDegree()=" + getDegree()
				+ ", getTemperature()=" + getTemperature()
				+ ", getPercentWater()=" + getPercentWater()
				+ ", getDatestamp()=" + getDatestamp() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}
}
