// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseClusterRep.java

package cosmos.hibernate.base;

import cosmos.hibernate.ClusterRep;
import java.io.Serializable;
import java.util.Date;

public abstract class BaseClusterRep
    implements Serializable
{

    public BaseClusterRep()
    {
        hashCode = 0x80000000;
        initialize();
    }

    public BaseClusterRep(String _systemId, Double _distanceSysVirtCentre, String _planetsAllowed, String _clusterId, Double _angleInRadians, String _clusterDescription, Short _numberStarsInCluster, 
            Date _datestamp)
    {
        hashCode = 0x80000000;
        setSystemId(_systemId);
        setDistanceSysVirtCentre(_distanceSysVirtCentre);
        setPlanetsAllowed(_planetsAllowed);
        setClusterId(_clusterId);
        setAngleInRadians(_angleInRadians);
        setClusterDescription(_clusterDescription);
        setNumberStarsInCluster(_numberStarsInCluster);
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

    public Double getDistanceSysVirtCentre()
    {
        return _distanceSysVirtCentre;
    }

    public void setDistanceSysVirtCentre(Double _distanceSysVirtCentre)
    {
        this._distanceSysVirtCentre = _distanceSysVirtCentre;
        hashCode = 0x80000000;
    }

    public String getPlanetsAllowed()
    {
        return _planetsAllowed;
    }

    public void setPlanetsAllowed(String _planetsAllowed)
    {
        this._planetsAllowed = _planetsAllowed;
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

    public Double getAngleInRadians()
    {
        return _angleInRadians;
    }

    public void setAngleInRadians(Double _angleInRadians)
    {
        this._angleInRadians = _angleInRadians;
        hashCode = 0x80000000;
    }

    public String getClusterDescription()
    {
        return _clusterDescription;
    }

    public void setClusterDescription(String _clusterDescription)
    {
        this._clusterDescription = _clusterDescription;
        hashCode = 0x80000000;
    }

    public Short getNumberStarsInCluster()
    {
        return _numberStarsInCluster;
    }

    public void setNumberStarsInCluster(Short _numberStarsInCluster)
    {
        this._numberStarsInCluster = _numberStarsInCluster;
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
        if(!(obj instanceof ClusterRep))
            return false;
        ClusterRep mObj = (ClusterRep)obj;
        if(null != getSystemId() && null != mObj.getSystemId())
        {
            if(!getSystemId().equals(mObj.getSystemId()))
                return false;
        } else
        {
            return false;
        }
        if(null != getDistanceSysVirtCentre() && null != mObj.getDistanceSysVirtCentre())
        {
            if(!getDistanceSysVirtCentre().equals(mObj.getDistanceSysVirtCentre()))
                return false;
        } else
        {
            return false;
        }
        if(null != getPlanetsAllowed() && null != mObj.getPlanetsAllowed())
        {
            if(!getPlanetsAllowed().equals(mObj.getPlanetsAllowed()))
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
        if(null != getAngleInRadians() && null != mObj.getAngleInRadians())
        {
            if(!getAngleInRadians().equals(mObj.getAngleInRadians()))
                return false;
        } else
        {
            return false;
        }
        if(null != getClusterDescription() && null != mObj.getClusterDescription())
        {
            if(!getClusterDescription().equals(mObj.getClusterDescription()))
                return false;
        } else
        {
            return false;
        }
        if(null != getNumberStarsInCluster() && null != mObj.getNumberStarsInCluster())
        {
            if(!getNumberStarsInCluster().equals(mObj.getNumberStarsInCluster()))
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
            if(null != getDistanceSysVirtCentre())
            {
                sb.append(getDistanceSysVirtCentre().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getPlanetsAllowed())
            {
                sb.append(getPlanetsAllowed().hashCode());
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
            if(null != getAngleInRadians())
            {
                sb.append(getAngleInRadians().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getClusterDescription())
            {
                sb.append(getClusterDescription().hashCode());
                sb.append(":");
            } else
            {
                return super.hashCode();
            }
            if(null != getNumberStarsInCluster())
            {
                sb.append(getNumberStarsInCluster().hashCode());
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

 
    public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public String get_systemId() {
		return _systemId;
	}

	public void set_systemId(String _systemId) {
		this._systemId = _systemId;
	}

	public Double get_distanceSysVirtCentre() {
		return _distanceSysVirtCentre;
	}

	public void set_distanceSysVirtCentre(Double _distanceSysVirtCentre) {
		this._distanceSysVirtCentre = _distanceSysVirtCentre;
	}

	public String get_planetsAllowed() {
		return _planetsAllowed;
	}

	public void set_planetsAllowed(String _planetsAllowed) {
		this._planetsAllowed = _planetsAllowed;
	}

	public String get_clusterId() {
		return _clusterId;
	}

	public void set_clusterId(String _clusterId) {
		this._clusterId = _clusterId;
	}

	public Double get_angleInRadians() {
		return _angleInRadians;
	}

	public void set_angleInRadians(Double _angleInRadians) {
		this._angleInRadians = _angleInRadians;
	}

	public String get_clusterDescription() {
		return _clusterDescription;
	}

	public void set_clusterDescription(String _clusterDescription) {
		this._clusterDescription = _clusterDescription;
	}

	public Short get_numberStarsInCluster() {
		return _numberStarsInCluster;
	}

	public void set_numberStarsInCluster(Short _numberStarsInCluster) {
		this._numberStarsInCluster = _numberStarsInCluster;
	}

	public Date get_datestamp() {
		return _datestamp;
	}

	public void set_datestamp(Date _datestamp) {
		this._datestamp = _datestamp;
	}


	private int hashCode;
    private String _systemId;
    private Double _distanceSysVirtCentre;
    private String _planetsAllowed;
    private String _clusterId;
    private Double _angleInRadians;
    private String _clusterDescription;
    private Short _numberStarsInCluster;
    private Date _datestamp;
    
	@Override
	public String toString() {
		return "BaseClusterRep [hashCode=" + hashCode + ", _systemId="
				+ _systemId + ", _distanceSysVirtCentre="
				+ _distanceSysVirtCentre + ", _planetsAllowed="
				+ _planetsAllowed + ", _clusterId=" + _clusterId
				+ ", _angleInRadians=" + _angleInRadians
				+ ", _clusterDescription=" + _clusterDescription
				+ ", _numberStarsInCluster=" + _numberStarsInCluster
				+ ", _datestamp=" + _datestamp + "]";
	}
}
