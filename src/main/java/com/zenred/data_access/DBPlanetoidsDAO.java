package com.zenred.data_access;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import com.zenred.cosmos.StarCalendar;



import cosmos.hibernate.PlanetoidAtmosphereRep;
import cosmos.hibernate.PlanetoidRep;
import cosmos.hibernate.Planetoid;
import cosmos.hibernate.PlanetoidAtmosphere;

public class DBPlanetoidsDAO extends AbstractDataAccess {
	
	protected static final Logger logger = Logger
	.getLogger(DBPlanetoidsDAO.class);

	
	public DBPlanetoidsDAO() {
		init();
//		org.apache.log4j.BasicConfigurator.configure();
	}

	public void genPlanetoids(List<Planetoidrep> planetoid_rep,
			List<com.zenred.data_access.Planetoid> planetoid,
			List<com.zenred.data_access.Planetoid_Atmosphere> planetoid_atmosphere) {
		PlanetoidRepDAO  _planetoid_rep_dao = (PlanetoidRepDAO ) ctx
		.getBean("planetoidRepDAO");

		int _count = 0;
		for (PlanetoidRepIF _planetoid_rep : planetoid_rep) {
			PlanetoidRep _poid = new PlanetoidRep(_planetoid_rep);
			logger.info(this.getClass().getName() + ":planetoid_rep_id:" + _planetoid_rep.getPlanetoidId());
			_planetoid_rep_dao.savePlanetoidRep(_poid);
			++_count;
			System.gc();			
		}
		PlanetoidDAO  _planetoid_dao = (PlanetoidDAO ) ctx
		.getBean("planetoidDAO");

		for (com.zenred.data_access.Planetoid _planetoid : planetoid) {
			StarCalendar _star_calendar = new StarCalendar();
			Date _date = new Date(_star_calendar.getStarTimeInMillis());

			cosmos.hibernate.Planetoid _planetoid_h = new cosmos.hibernate.Planetoid(_planetoid.getPlanetoidId(),
					_planetoid.getRadius().doubleValue(),
					_planetoid.getDistancetopimary().doubleValue(),
					_planetoid.getDegree().doubleValue(),
					_planetoid.getTemperature().doubleValue(),
					_planetoid.getPercentwater(),
					_date
					);
			logger.info(this.getClass().getName() + ":planetoid_id:" + _planetoid.getPlanetoidId());
			_planetoid_dao.savePlanetoid(_planetoid_h);
			++_count;
			System.gc();

//			nextPlanetoid(_planetoid);
		}
		
		PlanetoidAtmosphereRepDAO  _planetoid_atmos_rep_dao = (PlanetoidAtmosphereRepDAO ) ctx
		.getBean("planetoidAtmosphereRepDAO");
		for (com.zenred.data_access.Planetoid_Atmosphere _planetoid_atmosphere : planetoid_atmosphere) {
			StarCalendar _star_calendar = new StarCalendar();
			Date _date = new Date(_star_calendar.getStarTimeInMillis());
			cosmos.hibernate.PlanetoidAtmosphereRep _planetoid_atmosphere_rep_h = new cosmos.hibernate.PlanetoidAtmosphereRep(
					_planetoid_atmosphere.getPlanetoidId(),
					_planetoid_atmosphere.getChem_name(),
					_planetoid_atmosphere.getPercentage(),
					_date
					);
			logger.info(this.getClass().getName() + ":planetoid_atmosphere_id:" + _planetoid_atmosphere.getPlanetoidId());

			_planetoid_atmos_rep_dao.savePlanetoidAtmosphereRep(_planetoid_atmosphere_rep_h);
			++_count;
			System.gc();


//			nextPlanetoidAtmosphere(_planetoid_atmosphere);
		}
	}
	
	public void writePlanetoidRep(PlanetoidRep planetoidRep){
		PlanetoidRepDAO  planetoidRepDAO = (PlanetoidRepDAO ) ctx
		.getBean("planetoidRepDAO");
		planetoidRepDAO.savePlanetoidRep(planetoidRep);

	}
	
	public void writePlanetoid(Planetoid planetoid){
		PlanetoidDAO  planetoidDAO = (PlanetoidDAO ) ctx
		.getBean("planetoidDAO");
		planetoidDAO.savePlanetoid(planetoid);
	}
	
	public void writeAtmosphereRep(PlanetoidAtmosphereRep planetoidAtmosphereRep){
		PlanetoidAtmosphereRepDAO  planetoidAtmosRepDao = (PlanetoidAtmosphereRepDAO ) ctx
		.getBean("planetoidAtmosphereRepDAO");
		planetoidAtmosRepDao.savePlanetoidAtmosphereRep(planetoidAtmosphereRep);
	}
}
