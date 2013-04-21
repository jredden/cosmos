// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenPlanets.java

package com.zenred.cosmos;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenred.data_access.ClusterRepDAO;
import com.zenred.data_access.DBPlanetoidsDAO;
import com.zenred.data_access.MarshallStars;
import com.zenred.data_access.NoClustersNearStarDAO;
import com.zenred.data_access.Planetoid;
import com.zenred.data_access.Planetoid_Atmosphere;
import com.zenred.data_access.Planetoidrep;
import com.zenred.service.AtmosphereComponent;
import com.zenred.service.AtmosphereDTO;
import com.zenred.service.AtmosphereParts;
import com.zenred.service.GenerateAtmosphere;
import com.zenred.util.VectToList;

import cosmos.hibernate.StarRep;

// Referenced classes of package com.zenred.cosmos:
//            StarsAvecPlanetsDAO, NoClustersNearStarDAO, ClusterRepDAO, DrawRolls

public class GenPlanets {
	
	private Logger logger = LoggerFactory.getLogger(GenPlanets.class);
	
	private List<StarRep> starsystems_list;

	private MarshallStars marshall_stars = new MarshallStars();
	private NoClustersNearStarDAO numbercluster = new NoClustersNearStarDAO();
	private ClusterRepDAO clusterrepdao = new ClusterRepDAO();
	double bode1;
	Atmosphere atmosphere;
	private double distance_in_aus;
	private double planet_radius;
	private double degrees;
	private double period;
	private double distance;
	private double temperature;

	/**
	 * ctor
	 */
	public GenPlanets() {
	}

	public double sizeRadius(double temperature) {
		double _sizeradius = 0.0;

		if (temperature > 500.0 || temperature < 100.0) {
			_sizeradius = 1500.0 + (4000.0 * DrawRolls.Instance().draw_rand());
		} else {
			if (temperature < 200.0 && temperature >= 100.0) {
				_sizeradius = 1000.0 + (98000.0 * DrawRolls.Instance()
						.draw_rand());
			} else {
				if (temperature < 100.0 && temperature >= 1.0) {
					_sizeradius = 1500.0 + (16000.0 * DrawRolls.Instance()
							.draw_rand());
				}
			}
		}
		return _sizeradius;
	}
	public double sizeRadius(double temperature, double parentRadius) {
		double _sizeradius = 0.0;

		if (temperature > 500.0 || temperature < 100.0) {
			_sizeradius = 1500.0 + (parentRadius/3.0 * DrawRolls.Instance().draw_rand());
		} else {
			if (temperature < 200.0 && temperature >= 100.0) {
				_sizeradius = 1000.0 + (parentRadius/3.0 * DrawRolls.Instance()
						.draw_rand());
			} else {
				if (temperature < 100.0 && temperature >= 1.0) {
					_sizeradius = 1500.0 + (parentRadius/3.0 * DrawRolls.Instance()
							.draw_rand());
				}
			}
		}
		return _sizeradius;
	}

	/**
	 * returns distance
	 */
	public double genBode(int planetoid_counter) {
		double _bode_ret = 0.0;
		double _bode1 = DrawRolls.Instance().draw_rand();
		double _bode2 = DrawRolls.Instance().draw_rand();
		double _bode3 = ((_bode1 * 54.0) + 6.0) / 100.0;
		double _bode4 = ((_bode2 * 66.0) + 6.0) / 100.0;
		_bode1 = new Double(planetoid_counter).doubleValue();
		_bode1 *= _bode1;
		if(0 == planetoid_counter){
			_bode_ret =_bode4;
		}
		else{
			_bode_ret = _bode4 + _bode3 * _bode1;
		}
		return _bode_ret;
	}

	/**
	 * 1 cluster / 1 sun - 10
	 * 1 cluster / 2 sun - 8
	 * 1 cluster / 3 sun - 6
	 * 2 cluster / 2 sun - 4
	 * 2 cluster / 3 sun - 3
	 * 2 cluster / 4 sun - 2
	 * 
	 */
	public int numberOfPlanets(int num_clusters, int num_stars) {
		int _num_planets = 0;

		if (1 == num_clusters) {
			if (1 == num_stars) {
				_num_planets = DrawRolls.Instance().get_D10();
			} else {
				if (2 == num_stars) {
					_num_planets = DrawRolls.Instance().get_D8();
				} else {
					if (3 == num_stars) {
						_num_planets = DrawRolls.Instance().get_D6();
					}
				}
			}
		} else {
			if (2 == num_clusters) {
				if (2 == num_stars) {
					_num_planets = DrawRolls.Instance().get_D4();
				} else {
					if (3 == num_stars) {
						_num_planets = DrawRolls.Instance().get_D3();
					} else {
						if (4 == num_stars) {
							_num_planets = DrawRolls.Instance().get_D2();
						}

					}
				}
			}
		}
		return _num_planets;
	}

	/**
	 * generate all associated to one planetoid
	 * 
	 * temperature
	 * period
	 * distance astronomical units
	 * starting degree of planetoid
	 * radius of the planetoid
	 */
	private void generatePlanet(int planetoid_counter, StarRep _starrep,
			Object constraint) {
		distance = genBode(planetoid_counter);

		temperature = 295.0 * (Math.pow(_starrep.getLuminosity()
				.doubleValue()
				/ (distance * distance), .25));

		period = Math.pow(distance, 3.0 / Math.pow(_starrep
				.getStarSize().doubleValue(), 0.5));
		if (period > 9999.0) {
			period = 9000.0 + 10.0 * planetoid_counter + 100.0 * bode1;
		}

		distance_in_aus = distance * StarAtributesIF.convau;
		degrees = DrawRolls.Instance().draw_rand() * 360.0;

		planet_radius = sizeRadius(temperature);
	}

	/**
	 * read list of starts - can be limited by constraints, typically
	 * U, V coordinates.
	 * 
	 * determine how many stars in cluster, a second constraint on the
	 * number and positioning of planets
	 * 
	 * determine how many clusters in a system.
	 */
	public void generatePlanets(Object constraints) {
		starsystems_list = marshall_stars.getStarsWithPlanets();
		 List<Planetoid_Atmosphere> _planetoid_atmosphere_list = new ArrayList<Planetoid_Atmosphere>();
		AtmosphereDTO atmosphereDTO;
		GenerateAtmosphere generateAtmosphere = new GenerateAtmosphere();
		 List<Planetoid> _planetoid_list = new ArrayList<Planetoid>();
		 List<Planetoidrep> _planetoid_rep_list = new ArrayList<Planetoidrep>();
		 Planetoidrep _planetoid_rep = null;
		 Planetoid _planetoid = null;
		for (StarRep _starrep : starsystems_list) {
			String _systerm_id = _starrep.getSystemId();
			int _no_stars = marshall_stars.getNumberStars(_systerm_id)
					.intValue();
			logger.debug("sys id:" + _systerm_id + ":" + _no_stars + ":");
			int _num_planets = numberOfPlanets(1, _no_stars);
			for (int _idex = 0; _idex < _num_planets; _idex++) {
				
				String _planetoid_id = _starrep.getClusterId() + "_" + _idex+"_"+_starrep.getStarId();
				_planetoid_rep = new Planetoidrep();
				_planetoid_rep.setClusterId(_starrep.getClusterId());
				_planetoid_rep.setSystemId(_systerm_id);
				_planetoid_rep.setPlanetoidId(_planetoid_id);
				_planetoid_rep.setItsStar(_starrep.getStarId().toString());
				
				generatePlanet(_idex, _starrep, constraints);
				System.out.println("Planet:"+ _starrep.getLuminosity().doubleValue() + ":"
						+ distance_in_aus + ":"
						+ planet_radius  + ":"
						+ _starrep.getStarType()  + ":"
						+ _planetoid_id +":"
						);
				_planetoid = new Planetoid();
				_planetoid.setDegree(degrees);
				_planetoid.setDistance_au(distance_in_aus);
				_planetoid.setDistancetopimary(distance);
				_planetoid.setPeriod(period);
				_planetoid.setPlanetoidId(_planetoid_id);
				_planetoid.setRadius(planet_radius);
				_planetoid.setTemperature(temperature);
				_planetoid.setPercentwater(0.0);
/*				
				atmosphere = Atmosphere.genAtmosphere(_starrep.getLuminosity()
						.doubleValue(), distance_in_aus, planet_radius,
						_starrep.getStarType());
				List<Planetoid_Atmosphere> _list = Atmosphere.genList(atmosphere.getAtmophereProfile(), _planetoid_id);
//				System.out.println("Atmosphere:"+ atmosphere);
				_planetoid_atmosphere_list.addAll(_list);
				
				To Do: refactor so DAO uses DTO object
*/
				atmosphereDTO = generateAtmosphere.genAtmosphereNormalized(_starrep.getLuminosity()
						.doubleValue(), distance_in_aus, planet_radius, _starrep.getStarType());
				_planetoid_list.add(_planetoid);
				List<Planetoid_Atmosphere> _list = new ArrayList<Planetoid_Atmosphere>();
				for(AtmosphereComponent atmosphereComponent : atmosphereDTO.getAtmosphereCompenent()){
					Planetoid_Atmosphere planetoidAtmosphere = new Planetoid_Atmosphere();
					planetoidAtmosphere.setChem_name(atmosphereComponent.getSymbol());
					planetoidAtmosphere.setPercentage(atmosphereComponent.getNormalized_percent());
					planetoidAtmosphere.setPlanetoidId(_planetoid_id);
					if(atmosphereComponent.getSymbol().equals(AtmosphereParts.Water.getSymbol())){
						_planetoid.setPercentwater(atmosphereComponent.getNormalized_percent());
					}
				}
				logger.info("Atmosphere: {}", atmosphere);
				_planetoid_atmosphere_list.addAll(_list);
				_planetoid_rep_list.add(_planetoid_rep);
			}
			// do it
			DBPlanetoidsDAO _dbplanetoids_dao = new DBPlanetoidsDAO();
			_dbplanetoids_dao.genPlanetoids(_planetoid_rep_list, _planetoid_list, _planetoid_atmosphere_list);
			
			_planetoid_atmosphere_list = new ArrayList<Planetoid_Atmosphere>();
			_planetoid_list = new ArrayList<Planetoid>();
			_planetoid_rep_list = new ArrayList<Planetoidrep>();

//			EmitPlanetoidDAO _emit_planetoid_dao = new EmitPlanetoidDAO();
//			_emit_planetoid_dao.genPlanetoids(_planetoid_rep_list, _planetoid_list, _planetoid_atmosphere_list);

		}
	}

	public List<StarRep> getStarSystemsList() {
		return starsystems_list;
	}

	public static void main(String[] args) {
		GenPlanets genPlanets = new GenPlanets();
		genPlanets.generatePlanets(null);
		VectToList.print(genPlanets.starsystems_list);
		System.out.println("number systems:"
				+ genPlanets.starsystems_list.size() + ":");
	}
}
