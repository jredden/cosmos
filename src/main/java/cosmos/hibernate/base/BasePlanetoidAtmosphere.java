// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BasePlanetoidAtmosphere.java

package cosmos.hibernate.base;

import cosmos.hibernate.PlanetoidAtmosphere;
import java.io.Serializable;
import java.util.Date;

public abstract class BasePlanetoidAtmosphere implements Serializable {

	public BasePlanetoidAtmosphere() {
		hashCode = 0x80000000;
		initialize();
	}

	public BasePlanetoidAtmosphere(String _planetoidId, String _chem_name,
			double _percentage, Date _datestamp) {
		hashCode = 0x80000000;
		setPlanetoidId(_planetoidId);
		setChem_name(_chem_name);
		setPercentage(_percentage);
		setDatestamp(_datestamp);
		initialize();
	}

	protected void initialize() {
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof PlanetoidAtmosphere))
			return false;
		PlanetoidAtmosphere mObj = (PlanetoidAtmosphere) obj;
		if (null != getPlanetoidId() && null != mObj.getPlanetoidId()) {
			if (!getPlanetoidId().equals(mObj.getPlanetoidId()))
				return false;
		} else {
			return false;
		}
		if (getChem_name() != mObj.getChem_name())
			return false;
		if (getPercentage() != mObj.getPercentage())
			return false;

		if (null != getDatestamp() && null != mObj.getDatestamp())
			return getDatestamp().equals(mObj.getDatestamp());
		else
			return false;
	}

	public int hashCode() {
		if (0x80000000 == hashCode) {
			StringBuffer sb = new StringBuffer();
			if (null != getPlanetoidId()) {
				sb.append(getPlanetoidId().hashCode());
				sb.append(":");
			} else {
				return super.hashCode();
			}
			sb.append(getChem_name().hashCode());
			sb.append(":");

			sb.append((new Double(getPercentage())).hashCode());
			sb.append(":");

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
		return super.toString();
	}

	private int hashCode;
	private String _planetoidId;
	private String _chem_name;
	private double _percentage;

	private Date _datestamp;

	public String getPlanetoidId() {
		return _planetoidId;
	}

	public void setPlanetoidId(String id) {
		_planetoidId = id;
	}

	public String getChem_name() {
		return _chem_name;
	}

	public void setChem_name(String _chem_name) {
		this._chem_name = _chem_name;
	}

	public double getPercentage() {
		return _percentage;
	}

	public void setPercentage(double _percentage) {
		this._percentage = _percentage;
	}

	public Date getDatestamp() {
		return _datestamp;
	}

	public void setDatestamp(Date _datestamp) {
		this._datestamp = _datestamp;
	}
}
