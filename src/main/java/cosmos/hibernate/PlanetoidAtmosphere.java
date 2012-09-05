// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlanetoidAtmosphere.java

package cosmos.hibernate;

import cosmos.hibernate.base.BasePlanetoidAtmosphere;
import java.util.Date;

public class PlanetoidAtmosphere extends BasePlanetoidAtmosphere {

	public PlanetoidAtmosphere() {
	}

	public PlanetoidAtmosphere(String _planetoidId, String _chem_name,
			double _percentage, Date _datestamp) {
		super(_planetoidId, _chem_name, _percentage, _datestamp);
	}
}
