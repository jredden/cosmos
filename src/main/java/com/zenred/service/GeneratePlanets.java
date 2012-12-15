package com.zenred.service;

import java.util.Date;
import java.util.List;

import com.zenred.cosmos.Atmosphere;
import com.zenred.cosmos.DrawRolls;
import com.zenred.cosmos.GenPlanets;
import com.zenred.cosmos.StarAtributesIF;
import com.zenred.cosmos.StarCalendar;
import com.zenred.data_access.DBPlanetoidsDAO;
import com.zenred.data_access.MarshallClustersAndStarsAndPlanetsInOneSystem;
import com.zenred.data_access.MarshallStars;
import com.zenred.data_access.Planetoid;
import com.zenred.data_access.PlanetoidRepIF;
import com.zenred.data_access.Planetoid_Atmosphere;
import com.zenred.data_access.Planetoidrep;

import cosmos.hibernate.PlanetoidRep;
import cosmos.hibernate.StarRep;

public class GeneratePlanets {
	
	private GenPlanets genPlanets;
	private MarshallStars marshallStars;
	private MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem;
	private DBPlanetoidsDAO dbPlanetoidsDAO;

	public void setGenPlanets(GenPlanets genPlanets) {
		this.genPlanets = genPlanets;
	}
	
	public void setMarshallStars(MarshallStars marshallStars) {
		this.marshallStars = marshallStars;
	}
	
	public void setMarshallClustersAndStarsAndPlanetsInOneSystem(
			MarshallClustersAndStarsAndPlanetsInOneSystem marshallClustersAndStarsAndPlanetsInOneSystem) {
		this.marshallClustersAndStarsAndPlanetsInOneSystem = marshallClustersAndStarsAndPlanetsInOneSystem;
	}

	public GeneratePlanets(){
		dbPlanetoidsDAO = new DBPlanetoidsDAO();
	}

	private StarRep compositeStarRep(List<StarRep> starRepList, int starCount) {
		if (null !=starRepList.get(starCount)) {
			return starRepList.get(starCount);
		}
		else{
			System.err.println("NEED Composite:"+ starRepList+":"+starCount);
		}
		/*
		StarRep starRep = new StarRep();
		starRep.setClusterId(starRepList.get(0).getClusterId());
		starRep.setSystemId(starRepList.get(0).getSystemId());
		float rsize = new Float(starRepList.size()).floatValue();
		starRep.setAngleInRadiansS(0.0); // not really, now degrees
		starRep.setDistanceClustVirtCentre(0.0);
		starRep.setLuminosity(0.0);
		for (StarRep _starRep : starRepList) {
			starRep.setAngleInRadiansS((starRep.getAngleInRadiansS() + _starRep
					.getAngleInRadiansS()) / 360.0);
			starRep.setDistanceClustVirtCentre(starRep
					.getDistanceClustVirtCentre()
					+ (_starRep.getDistanceClustVirtCentre() / rsize));
		}
		
		return starRep;
		*/
		return starRepList.get(0);
	}
	
	protected Planetoid generatePlanetoid(int count, StarRep starRep,
			Constraint constraints, String suffix) {
		String clusterId = starRep.getClusterId();
		return generatePlanetoid(count, starRep, constraints, clusterId, suffix);
	}
	
	protected Planetoid generatePlanetoid(int count, StarRep starRep,
			Constraint constraints, String clusterId, String suffix) {
		Planetoid planetoid_l = new Planetoid();
		double distance = genPlanets.genBode(count);
		planetoid_l.setTemperature(295.0 * (Math.pow(
				starRep.getLuminosity().doubleValue()
						/ (distance * distance), .25)));
		double period = Math.pow(distance,
				3.0 / Math.pow(starRep.getStarSize().doubleValue(), 0.5));
		if (period > 9999.0) {
			period = 9000.0 + 10.0 * count + 100.0 * DrawRolls.Instance().draw_rand();
		}
		planetoid_l.setPeriod(period);
		planetoid_l.setDistance_au(distance * StarAtributesIF.convau);
		planetoid_l.setDegree(DrawRolls.Instance().draw_rand() * 360.0);
		double temperature = planetoid_l.getTemperature();
		if(constraints.getType().equals("Double")){
			double parentRadius = constraints.getNumberConstaint();
			planetoid_l.setRadius(genPlanets.sizeRadius(temperature, parentRadius));
		}
		else{
			planetoid_l.setRadius(genPlanets.sizeRadius(temperature));
		}
		planetoid_l.setPlanetoidId(clusterId + '_' + count+suffix);
		planetoid_l.setPercentwater(0.0);
		planetoid_l.setDistancetopimary(distance);
		System.out.println(":::Planetoid:::"+planetoid_l);
		return planetoid_l;
	}
	
	private void convertWritePlanetoidRep(Planetoidrep planetoidrep){
		PlanetoidRepIF planetoidRepIF = planetoidrep;
		PlanetoidRep planetoidRep = new PlanetoidRep(planetoidRepIF);
		dbPlanetoidsDAO.writePlanetoidRep(planetoidRep);
	}
	
	private void convertWritePlanetoid(Planetoid planetoid) {
		StarCalendar _star_calendar = new StarCalendar();
		Date date = new Date(_star_calendar.getStarTimeInMillis());

		cosmos.hibernate.Planetoid planetoid_h = new cosmos.hibernate.Planetoid(
				planetoid.getPlanetoidId(),
				planetoid.getRadius().doubleValue(), planetoid
						.getDistancetopimary().doubleValue(), planetoid
						.getDegree().doubleValue(), planetoid.getTemperature()
						.doubleValue(), planetoid.getPercentwater(), date);
		dbPlanetoidsDAO.writePlanetoid(planetoid_h);
	}
	
	private void convertWriteAtmosphere(List<Planetoid_Atmosphere> planetoidAtmosphereList){
		StarCalendar _star_calendar = new StarCalendar();
		for (com.zenred.data_access.Planetoid_Atmosphere planetoidAtmosphere : planetoidAtmosphereList) {
			Date date = new Date(_star_calendar.getStarTimeInMillis());
			cosmos.hibernate.PlanetoidAtmosphereRep planetoidAtmosphereRep_h = new cosmos.hibernate.PlanetoidAtmosphereRep(
					planetoidAtmosphere.getPlanetoidId(),
					planetoidAtmosphere.getChem_name(),
					planetoidAtmosphere.getPercentage(),
					date
					);
			dbPlanetoidsDAO.writeAtmosphereRep(planetoidAtmosphereRep_h);
		}
	}
	
	private int numberOfMoons(Planetoid planetoid) {
		int moonNum = 0; /* in the beginning there were no moons */
		int sizeIndex = planetoid.getRadius().intValue() / 1000;
		double aRandomNumber = DrawRolls.Instance().draw_rand();
		if (sizeIndex <= 3 && aRandomNumber <= 0.25) {
			moonNum = DrawRolls.Instance().get_D3();
		} else if ((sizeIndex > 3 && sizeIndex <= 6) && aRandomNumber <= 0.40) {
			moonNum = DrawRolls.Instance().get_D4();
		} else if ((sizeIndex > 6 && sizeIndex <= 16) && aRandomNumber <= 0.40) {
			moonNum = DrawRolls.Instance().get_D6();
		} else if (sizeIndex > 16 && aRandomNumber <= 0.95) {
			moonNum = DrawRolls.Instance().get_D18();
		}
		return moonNum;
	}

	
	public void generateSomePlanets(Constraint constraints, StarRep starRep,
			int starCount, int clusterCount) {
		System.out.println("getSystemId:"+starRep);
		int numberStarsInSystem = marshallStars.getNumberStars(starRep
				.getSystemId());
		int numberPlanets = genPlanets.numberOfPlanets(clusterCount,
				numberStarsInSystem);

		for (int _idex = 0; _idex < numberPlanets; _idex++) {
			StarRep starRep2 = compositeStarRep(marshallClustersAndStarsAndPlanetsInOneSystem.getStarsByClusterId(starRep.getClusterId()), starCount);
			String itsStar = starRep2.getStarId().toString();
			String planetoidId = starRep.getClusterId() + '_' + _idex+"_"+itsStar;
			Planetoidrep planetoidrep = new Planetoidrep();
			planetoidrep.setClusterId(starRep.getClusterId());
			planetoidrep.setSystemId(starRep.getSystemId());
			planetoidrep.setPlanetoidId(planetoidId);
			System.out.println("itsStar::::"+itsStar);
			planetoidrep.setItsStar(itsStar);
			convertWritePlanetoidRep(planetoidrep);
			Planetoid planetoid = generatePlanetoid(_idex, starRep, constraints, "_"+itsStar);
			convertWritePlanetoid(planetoid);
			Atmosphere atmosphere = Atmosphere.genAtmosphere(starRep.getLuminosity()
					.doubleValue(), planetoid.getDistance_au(), planetoid.getRadius(),
					starRep.getStarType());
			List<Planetoid_Atmosphere> planetoidAtmosphereList = Atmosphere.genList(atmosphere.getAtmophereProfile(), planetoidId);
			convertWriteAtmosphere(planetoidAtmosphereList);
			int numberMoons = numberOfMoons(planetoid);
			for(int _moonidex = 0; _moonidex < numberMoons; _moonidex++){
				String planetoidletId = planetoidrep.getPlanetoidId() + '_' + _moonidex+"__PLANETOIDLET";
				Planetoidrep planetoidreplet = new Planetoidrep();
				planetoidreplet.setClusterId(starRep.getClusterId());
				planetoidreplet.setSystemId(starRep.getSystemId());
				planetoidreplet.setPlanetoidId(planetoidletId);
				planetoidreplet.setProfile(planetoidId);
				StarRep starRep3 = compositeStarRep(marshallClustersAndStarsAndPlanetsInOneSystem.getStarsByClusterId(starRep.getClusterId()), starCount);
				itsStar = starRep3.getStarId().toString();
				System.out.println("itsStar::::2:"+itsStar);
				planetoidreplet.setItsStar(itsStar);
				convertWritePlanetoidRep(planetoidreplet);
				Constraint constraint = new Constraint();
				constraint.setType("Double");
				constraint.setNumberConstaint(planetoid.getRadius());
				Planetoid planetoidlet = generatePlanetoid(_moonidex, starRep, constraint, planetoidrep.getPlanetoidId(), "__PLANETOIDLET");
				convertWritePlanetoid(planetoidlet);
				atmosphere = Atmosphere.genAtmosphere(starRep.getLuminosity()
						.doubleValue(), planetoidlet.getDistance_au(), planetoidlet.getRadius(),
						starRep.getStarType());
				List<Planetoid_Atmosphere> planetoidletAtmosphereList = Atmosphere.genList(atmosphere.getAtmophereProfile(), planetoidletId);
				convertWriteAtmosphere(planetoidletAtmosphereList);
			}
		}
	}
}
