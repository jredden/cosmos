// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseSystemRep.java

package cosmos.hibernate.base;

import cosmos.hibernate.SystemRep;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseSystemRep
    implements Serializable
{

    public BaseSystemRep()
    {
        hashCode = 0x80000000;
        initialize();
    }

    public BaseSystemRep(String _systemId, Double _distanceToGalaxyCentre, Double _ucoordinate, Double _vcoordinate, Date _datestamp)
    {
        hashCode = 0x80000000;
        setSystemId(_systemId);
        setDistanceToGalaxyCentre(_distanceToGalaxyCentre);
        setUcoordinate(_ucoordinate);
        setVcoordinate(_vcoordinate);
        setDatestamp(_datestamp);
        initialize();
    }

    protected void initialize()
    {
    }

    public String getSystemId()
    {
        return _systemId;
    }

    public void setSystemId(String _systemId)
    {
        this._systemId = _systemId;
        hashCode = 0x80000000;
    }

    public Double getDistanceToGalaxyCentre()
    {
        return _distanceToGalaxyCentre;
    }

    public void setDistanceToGalaxyCentre(Double _distanceToGalaxyCentre)
    {
        this._distanceToGalaxyCentre = _distanceToGalaxyCentre;
        hashCode = 0x80000000;
    }

    public Double getUcoordinate()
    {
        return _ucoordinate;
    }

    public void setUcoordinate(Double _ucoordinate)
    {
        this._ucoordinate = _ucoordinate;
        hashCode = 0x80000000;
    }

    public Double getVcoordinate()
    {
        return _vcoordinate;
    }

    public void setVcoordinate(Double _vcoordinate)
    {
        this._vcoordinate = _vcoordinate;
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
        if(!(obj instanceof SystemRep))
            return false;
        SystemRep mObj = (SystemRep)obj;
        if(null != getSystemId() && null != mObj.getSystemId())
        {
            if(!getSystemId().equals(mObj.getSystemId()))
                return false;
        } else
        {
            return false;
        }
        if(null != getDistanceToGalaxyCentre() && null != mObj.getDistanceToGalaxyCentre())
        {
            if(!getDistanceToGalaxyCentre().equals(mObj.getDistanceToGalaxyCentre()))
                return false;
        } else
        {
            return false;
        }
        if(null != getUcoordinate() && null != mObj.getUcoordinate())
        {
            if(!getUcoordinate().equals(mObj.getUcoordinate()))
                return false;
        } else
        {
            return false;
        }
        if(null != getVcoordinate() && null != mObj.getVcoordinate())
        {
            if(!getVcoordinate().equals(mObj.getVcoordinate()))
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
            if(null != getSystemId())
            {
                sb.append(getSystemId().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getDistanceToGalaxyCentre())
            {
                sb.append(getDistanceToGalaxyCentre().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getUcoordinate())
            {
                sb.append(getUcoordinate().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getVcoordinate())
            {
                sb.append(getVcoordinate().hashCode());
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
    private String _systemId;
    private Double _distanceToGalaxyCentre;
    private Double _ucoordinate;
    private Double _vcoordinate;
    private Date _datestamp;
}
