package com.zenred.cosmos.dao.file;

import java.text.MessageFormat;
import java.util.List;

import com.zenred.cosmos.EmitSqlIF;
import com.zenred.data_access.Planetoid;
import com.zenred.data_access.PlanetoidRepIF;
import com.zenred.data_access.Planetoid_Atmosphere;
import com.zenred.data_access.Planetoidrep;

public class EmitPlanetoidDAO extends AbstractSqlFileDAO implements EmitSqlIF {
	private static String PLANETOID_REP = "PlanetoidRep";
	private static String PLANETOID = "Planetoid";
	private static String PLANETOID_ATMOSPHERE = "PlanetoidAtmosphere";
	private static String SQL = ".sql";


	public void genPlanetoids(List<Planetoidrep> planetoid_rep,
			List<Planetoid> planetoid,
			List<Planetoid_Atmosphere> planetoid_atmosphere) {

		for (PlanetoidRepIF _planetoid_rep : planetoid_rep) {
			nextPlanetoidRep(_planetoid_rep);
		}

		for (Planetoid _planetoid : planetoid) {
			nextPlanetoid(_planetoid);
		}
		for (Planetoid_Atmosphere _planetoid_atmosphere : planetoid_atmosphere) {
			nextPlanetoidAtmosphere(_planetoid_atmosphere);
		}
	}

	private void nextPlanetoid(Planetoid _planetoid) {
		String _insert_planetoid = MessageFormat.format(INSERT_PLANETOID,
				new Object[] { "'" + _planetoid.getPlanetoidId() + "'",
						_planetoid.getRadius().toString(),
						_planetoid.getDistancetopimary().toString(),
						_planetoid.getDegree().toString().toString(),
						_planetoid.getTemperature().toString(),
						_planetoid.getPercentwater().toString(),
						_planetoid.getPeriod().toString(),
						_planetoid.getDistance_au().toString(), });
		super.establishFileWriter( PLANETOID + "_"
				+ _planetoid.getPlanetoidId()
				+ SQL
				);
		super.printWrite(_insert_planetoid);
		super.stopPrintWriter();

	}

	private void nextPlanetoidRep(PlanetoidRepIF planetoid_rep) {
		String _insert_planetoid_rep = MessageFormat.format(
				INSERT_PLANETOID_REP, new Object[] {
						"'" + planetoid_rep.getSystemId() + "'",
						"'" + planetoid_rep.getClusterId() + "'",
						"'" + planetoid_rep.getPlanetoidId() + "'" });
		super.establishFileWriter(PLANETOID_REP + "_"
				+ planetoid_rep.getSystemId() + "_"
				+ planetoid_rep.getClusterId() + "_"
				+ planetoid_rep.getPlanetoidId()
				+ SQL
				);
		super.printWrite(_insert_planetoid_rep);
		super.stopPrintWriter();

	}

	private void nextPlanetoidAtmosphere(
			Planetoid_Atmosphere planetoid_atmosphere) {
		String _insert_planetoid_atmosphere = MessageFormat.format(
				INSERT_PLANETOID_ATMOSPHERE, new Object[] {
						"'" + planetoid_atmosphere.getPlanetoidId() + "'",
						"'" + planetoid_atmosphere.getChem_name() + "'", 
						 planetoid_atmosphere.getPercentage() 
						});
		super.establishFileWriter(PLANETOID_ATMOSPHERE + "_"
				+ planetoid_atmosphere.getPlanetoidId() + "_"
				+ planetoid_atmosphere.getChem_name()
				+ SQL
				);
		super.printWrite(_insert_planetoid_atmosphere);
		super.stopPrintWriter();

	}
}
