// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseStarRep.java

package cosmos.hibernate.base;

import cosmos.hibernate.StarRep;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseStarRep
    implements Serializable
{

    public BaseStarRep()
    {
        hashCode = 0x80000000;
        initialize();
    }

    public BaseStarRep(Short _starId, String _systemId, String _clusterId, Double _distanceClustVirtCentre, Double _luminosity, String _noPlanetsAllowed, Double _angleInRadiansS, String _starColor, 
            String _starType, Date _datestamp, Double _starSize)
    {
        hashCode = 0x80000000;
        setSystemId(_systemId);
        setClusterId(_clusterId);
        setDistanceClustVirtCentre(_distanceClustVirtCentre);
        setLuminosity(_luminosity);
        setNoPlanetsAllowed(_noPlanetsAllowed);
        setAngleInRadiansS(_angleInRadiansS);
        setStarColor(_starColor);
        setStarType(_starType);
        setDatestamp(_datestamp);
        setStarSize(_starSize);
        setStarId(_starId);
        initialize();
    }

    protected void initialize()
    {
    }

    public String getSystemId()
    {
        return _systemId;
    }

    public void setStarId(Short _starId)
    {
        this._starId = _starId;
    }
    public Short getStarId()
    {
        return _starId;
    }

    public void setSystemId(String _systemId)
    {
        this._systemId = _systemId;
        hashCode = 0x80000000;
    }

    public String getClusterId()
    {
        return _clusterId;
    }

    public void setClusterId(String _clusterId)
    {
        this._clusterId = _clusterId;
        hashCode = 0x80000000;
    }

    public Double getDistanceClustVirtCentre()
    {
        return _distanceClustVirtCentre;
    }

    public void setDistanceClustVirtCentre(Double _distanceClustVirtCentre)
    {
        this._distanceClustVirtCentre = _distanceClustVirtCentre;
        hashCode = 0x80000000;
    }

    public Double getLuminosity()
    {
        return _luminosity;
    }

    public void setLuminosity(Double _luminosity)
    {
        this._luminosity = _luminosity;
        hashCode = 0x80000000;
    }

    public String getNoPlanetsAllowed()
    {
        return _noPlanetsAllowed;
    }

    public void setNoPlanetsAllowed(String _noPlanetsAllowed)
    {
        this._noPlanetsAllowed = _noPlanetsAllowed;
        hashCode = 0x80000000;
    }

    public Double getAngleInRadiansS()
    {
        return _angleInRadiansS;
    }

    public void setAngleInRadiansS(Double _angleInRadiansS)
    {
        this._angleInRadiansS = _angleInRadiansS;
        hashCode = 0x80000000;
    }

    public String getStarColor()
    {
        return _starColor;
    }

    public void setStarColor(String _starColor)
    {
        this._starColor = _starColor;
        hashCode = 0x80000000;
    }

    public String getStarType()
    {
        return _starType;
    }

    public void setStarType(String _starType)
    {
        this._starType = _starType;
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

    public Double getStarSize()
    {
        return _starSize;
    }

    public void setStarSize(Double _starSize)
    {
        this._starSize = _starSize;
        hashCode = 0x80000000;
    }

    public boolean equals(Object obj)
    {
        if(null == obj)
            return false;
        if(!(obj instanceof StarRep))
            return false;
        StarRep mObj = (StarRep)obj;
        if(null != getSystemId() && null != mObj.getSystemId())
        {
            if(!getSystemId().equals(mObj.getSystemId()))
                return false;
        } else
        {
            return false;
        }
        if(null != getClusterId() && null != mObj.getClusterId())
        {
            if(!getClusterId().equals(mObj.getClusterId()))
                return false;
        } else
        {
            return false;
        }
        if(null != getDistanceClustVirtCentre() && null != mObj.getDistanceClustVirtCentre())
        {
            if(!getDistanceClustVirtCentre().equals(mObj.getDistanceClustVirtCentre()))
                return false;
        } else
        {
            return false;
        }
        if(null != getLuminosity() && null != mObj.getLuminosity())
        {
            if(!getLuminosity().equals(mObj.getLuminosity()))
                return false;
        } else
        {
            return false;
        }
        if(null != getNoPlanetsAllowed() && null != mObj.getNoPlanetsAllowed())
        {
            if(!getNoPlanetsAllowed().equals(mObj.getNoPlanetsAllowed()))
                return false;
        } else
        {
            return false;
        }
        if(null != getAngleInRadiansS() && null != mObj.getAngleInRadiansS())
        {
            if(!getAngleInRadiansS().equals(mObj.getAngleInRadiansS()))
                return false;
        } else
        {
            return false;
        }
        if(null != getStarColor() && null != mObj.getStarColor())
        {
            if(!getStarColor().equals(mObj.getStarColor()))
                return false;
        } else
        {
            return false;
        }
        if(null != getStarType() && null != mObj.getStarType())
        {
            if(!getStarType().equals(mObj.getStarType()))
                return false;
        } else
        {
            return false;
        }
        if(null != getDatestamp() && null != mObj.getDatestamp())
        {
            if(!getDatestamp().equals(mObj.getDatestamp()))
                return false;
        } else
        {
            return false;
        }
        if(null != getStarSize() && null != mObj.getStarSize())
            return getStarSize().equals(mObj.getStarSize());
        else
            return false;
    }

    public int hashCode()
    {
        if(0x80000000 == hashCode)
        {
            StringBuffer sb = new StringBuffer();
            if(null != getStarId())
            {
                sb.append(getStarId().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getSystemId())
            {
                sb.append(getSystemId().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getClusterId())
            {
                sb.append(getClusterId().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getDistanceClustVirtCentre())
            {
                sb.append(getDistanceClustVirtCentre().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getLuminosity())
            {
                sb.append(getLuminosity().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getNoPlanetsAllowed())
            {
                sb.append(getNoPlanetsAllowed().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getAngleInRadiansS())
            {
                sb.append(getAngleInRadiansS().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getStarColor())
            {
                sb.append(getStarColor().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getStarType())
            {
                sb.append(getStarType().hashCode());
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
            if(null != getStarSize())
            {
                sb.append(getStarSize().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            hashCode = sb.toString().hashCode();
        }
        return hashCode;
    }

    @Override
	public String toString() {
		return "BaseStarRep [hashCode=" + hashCode + ", _starId=" + _starId
				+ ", _systemId=" + _systemId + ", _clusterId=" + _clusterId
				+ ", _distanceClustVirtCentre=" + _distanceClustVirtCentre
				+ ", _luminosity=" + _luminosity + ", _noPlanetsAllowed="
				+ _noPlanetsAllowed + ", _angleInRadiansS=" + _angleInRadiansS
				+ ", _starColor=" + _starColor + ", _starType=" + _starType
				+ ", _datestamp=" + _datestamp + ", _starSize=" + _starSize
				+ "]";
	}

    private int hashCode;
    private Short 	_starId;
    private String _systemId;
    private String _clusterId;
    private Double _distanceClustVirtCentre;
    private Double _luminosity;
    private String _noPlanetsAllowed;
    private Double _angleInRadiansS;
    private String _starColor;
    private String _starType;
    private Date _datestamp;
    private Double _starSize;
}
