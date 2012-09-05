// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasePlanetoid.java

package cosmos.hibernate.base;

import cosmos.hibernate.Planetoid;
import java.io.Serializable;
import java.util.Date;

public abstract class BasePlanetoid
    implements Serializable
{

    public BasePlanetoid()
    {
        hashCode = 0x80000000;
        initialize();
    }

    public BasePlanetoid(String _planetoidId, double _radius, double _distanceToPimary, double _degree, 
            double _temperature, Double _percentWater, Date _datestamp)
    {
        hashCode = 0x80000000;
        setPlanetoidId(_planetoidId);
        setRadius(_radius);
        setDistanceToPimary(_distanceToPimary);
        setDegree(_degree);
        setTemperature(_temperature);
        setPercentWater(_percentWater);
        setDatestamp(_datestamp);
        initialize();
    }

    protected void initialize()
    {
    }

    public String getPlanetoidId()
    {
        return _planetoidId;
    }

    public void setPlanetoidId(String _planetoidId)
    {
        this._planetoidId = _planetoidId;
        hashCode = 0x80000000;
    }

    public double getRadius()
    {
        return _radius;
    }

    public void setRadius(double _radius)
    {
        this._radius = _radius;
        hashCode = 0x80000000;
    }

    public double getDistanceToPimary()
    {
        return _distanceToPimary;
    }

    public void setDistanceToPimary(double _distanceToPimary)
    {
        this._distanceToPimary = _distanceToPimary;
        hashCode = 0x80000000;
    }

    public double getDegree()
    {
        return _degree;
    }

    public void setDegree(double _degree)
    {
        this._degree = _degree;
        hashCode = 0x80000000;
    }

    public double getTemperature()
    {
        return _temperature;
    }

    public void setTemperature(double _temperature)
    {
        this._temperature = _temperature;
        hashCode = 0x80000000;
    }

    public Double getPercentWater()
    {
        return _percentWater;
    }

    public void setPercentWater(Double _percentWater)
    {
        this._percentWater = _percentWater;
        hashCode = 0x80000000;
    }

    public Date getDatestamp()
    {
        return _datestamp;
    }

    public void setDatestamp(Date _datestamp)
    {
        this._datestamp = _datestamp;
        hashCode = 0x80000000;
    }

    public boolean equals(Object obj)
    {
        if(null == obj)
            return false;
        if(!(obj instanceof Planetoid))
            return false;
        Planetoid mObj = (Planetoid)obj;
        if(null != getPlanetoidId() && null != mObj.getPlanetoidId())
        {
            if(!getPlanetoidId().equals(mObj.getPlanetoidId()))
                return false;
        } else
        {
            return false;
        }
        if(getRadius() != mObj.getRadius())
            return false;
        if(getDistanceToPimary() != mObj.getDistanceToPimary())
            return false;
        if(getDegree() != mObj.getDegree())
            return false;
        if(getTemperature() != mObj.getTemperature())
            return false;
        if(null != getPercentWater() && null != mObj.getPercentWater())
        {
            if(!getPercentWater().equals(mObj.getPercentWater()))
                return false;
        } else
        {
            return false;
        }
        if(null != getDatestamp() && null != mObj.getDatestamp())
            return getDatestamp().equals(mObj.getDatestamp());
        else
            return false;
    }

    public int hashCode()
    {
        if(0x80000000 == hashCode)
        {
            StringBuffer sb = new StringBuffer();
            if(null != getPlanetoidId())
            {
                sb.append(getPlanetoidId().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            sb.append((new Double(getRadius())).hashCode());
            sb.append(":");
            sb.append((new Double(getDistanceToPimary())).hashCode());
            sb.append(":");
            sb.append((new Double(getDegree())).hashCode());
            sb.append(":");
            sb.append((new Double(getTemperature())).hashCode());
            sb.append(":");
            if(null != getPercentWater())
            {
                sb.append(getPercentWater().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getDatestamp())
            {
                sb.append(getDatestamp().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            hashCode = sb.toString().hashCode();
        }
        return hashCode;
    }

    public String toString()
    {
        return super.toString();
    }

    private int hashCode;
    private String _planetoidId;
    private double _radius;
    private double _distanceToPimary;
    private double _degree;
    private double _temperature;
    private Double _percentWater;
    private Date _datestamp;
}
