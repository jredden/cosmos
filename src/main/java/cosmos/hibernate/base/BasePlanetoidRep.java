// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasePlanetoidRep.java

package cosmos.hibernate.base;

import cosmos.hibernate.PlanetoidRep;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class BasePlanetoidRep implements Serializable {

	public BasePlanetoidRep() {
		hashCode = 0x80000000;
		initialize();
	}

	public BasePlanetoidRep(String _systemId, String _clusterId,
			String _planetoidId, String _profile, Date _datestamp, String _itsStar) {
		hashCode = 0x80000000;
		setSystemId(_systemId);
		setClusterId(_clusterId);
		setPlanetoidId(_planetoidId);
		setProfile(_profile);
		setDatestamp(_datestamp);
		setItsStar(_itsStar);
		initialize();
	}

	protected void initialize() {
	}

	public String getSystemId() {
		return _systemId;
	}

	public void setSystemId(String _systemId) {
		this._systemId = _systemId;
		hashCode = 0x80000000;
	}

	public String getClusterId() {
		return _clusterId;
	}

	public void setClusterId(String _clusterId) {
		this._clusterId = _clusterId;
		hashCode = 0x80000000;
	}

	public String getPlanetoidId() {
		return _planetoidId;
	}

	public void setPlanetoidId(String _planetoidId) {
		this._planetoidId = _planetoidId;
		hashCode = 0x80000000;
	}

	public String getProfile() {
		return _profile;
	}

	public void setProfile(String _profile) {
		this._profile = _profile;
		hashCode = 0x80000000;
	}

	public Date getDatestamp() {
		return _datestamp;
	}

	public void setDatestamp(Date _datestamp) {
		this._datestamp = _datestamp;
		hashCode = 0x80000000;
	}

	public String getItsStar() {
		return _itsStar;
	}

	public void setItsStar(String _itsStar) {
		this._itsStar = _itsStar;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof PlanetoidRep))
			return false;
		PlanetoidRep mObj = (PlanetoidRep) obj;
		if (null != getSystemId() && null != mObj.getSystemId()) {
			if (!getSystemId().equals(mObj.getSystemId()))
				return false;
		} else {
			return false;
		}
		if (null != getClusterId() && null != mObj.getClusterId()) {
			if (!getClusterId().equals(mObj.getClusterId()))
				return false;
		} else {
			return false;
		}
		if (null != getPlanetoidId() && null != mObj.getPlanetoidId()) {
			if (!getPlanetoidId().equals(mObj.getPlanetoidId()))
				return false;
		} else {
			return false;
		}
		if (null != getProfile() && null != mObj.getProfile()) {
			if (!getProfile().equals(mObj.getProfile()))
				return false;
		} else {
			return false;
		}
		if (null != getItsStar() && null != mObj.getItsStar()) {
			if (!getItsStar().equals(mObj.getItsStar()))
				return false;
		} else {
			return false;
		}
		if (null != getDatestamp() && null != mObj.getDatestamp())
			return getDatestamp().equals(mObj.getDatestamp());
		else
			return false;
	}

	public int hashCode() {
		if (0x80000000 == hashCode) {
			StringBuffer sb = new StringBuffer();
			if (null != getSystemId()) {
				sb.append(getSystemId().hashCode());
				sb.append(":");
			} else {
				return super.hashCode();
			}
			if (null != getClusterId()) {
				sb.append(getClusterId().hashCode());
				sb.append(":");
			} else {
				return super.hashCode();
			}
			if (null != getPlanetoidId()) {
				sb.append(getPlanetoidId().hashCode());
				sb.append(":");
			} else {
				return super.hashCode();
			}
			if (null != getProfile()) {
				sb.append(getProfile().hashCode());
				sb.append(":");
			} else {
				return super.hashCode();
			}
			if (null != getItsStar()) {
				sb.append(getItsStar().hashCode());
				sb.append(":");
			} else {
				return super.hashCode();
			}
			if (null != getDatestamp()) {
				sb.append(getDatestamp().hashCode());
				sb.append(":");
			} else {
				return super.hashCode();
			}
			hashCode = sb.toString().hashCode();
		}
		return hashCode;
	}

	public String toString() {
		StringBuffer _strbuf = new StringBuffer()
			.append("systemId:")
			.append(_systemId)
			.append(":clusterId:")
			.append(_clusterId)
			.append(":planetoidId:")
			.append(_planetoidId)
			.append(":_itsStar:")
			.append(_itsStar)
			.append(':')
			;
		return _strbuf.toString();
	}

	private int hashCode;
	private String _systemId;
	private String _clusterId;
	private String _planetoidId;
	private String _profile;
	private Date _datestamp;
	private String _itsStar;
}
