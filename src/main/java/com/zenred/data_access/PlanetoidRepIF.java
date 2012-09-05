package com.zenred.data_access;

import java.util.Calendar;

public interface PlanetoidRepIF {

	public abstract String getClusterId();

	public abstract Calendar getDatestamp();

	public abstract String getPlanetoidId();

	public abstract String getProfile();

	public abstract String getSystemId();
	
	public abstract String getItsStar();

	public abstract void setClusterId(String clusterId);

	public abstract void setDatestamp(Calendar datestamp);

	public abstract void setPlanetoidId(String planetoidId);

	public abstract void setProfile(String profile);

	public abstract void setSystemId(String systemId);
	
	public abstract void setItsStar(String itsStar);

}