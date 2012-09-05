//Source file: C:/VisualCafe/JAVA/LIB/cosmos/Atmosphere.java

package com.zenred.cosmos;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

import com.zenred.data_access.Planetoid_Atmosphere;

public class AtmosphereTest  extends TestCase 
{
	private static final Logger logger = Logger.getLogger(AtmosphereTest.class);
	private Atmosphere _atmosphere;
	private Atmosphere _atmosphere2;
	private Atmosphere _atmosphere3;
	private Atmosphere _atmosphere4;
	private Atmosphere _atmosphere5;
	private Atmosphere _atmosphere6;
	private Atmosphere _atmosphere7;
	private Atmosphere _atmosphere8;

	public AtmosphereTest(String test_name){
		super( test_name );
	}
	public static Test suite(){
		return new TestSuite( AtmosphereTest.class );
	}
	public void testAtmosphere(){
//		org.apache.log4j.BasicConfigurator.configure();
		
		double star_luminosity = 0.282;
		double distance_primary_au_s = 1.2;
		double planet_radius = 5002.98;
		String star_color_type = "k2";
		_atmosphere = Atmosphere.genAtmosphere(star_luminosity, distance_primary_au_s, planet_radius, star_color_type);
		logger.info(_atmosphere);
		logger.info("\n");
		star_luminosity = 1.2;
		distance_primary_au_s = 0.8;
		planet_radius = 4002.98;
		star_color_type = "m1";	
		_atmosphere2 = Atmosphere.genAtmosphere(star_luminosity, distance_primary_au_s, planet_radius, star_color_type);
		logger.info(_atmosphere2);
		logger.info("\n");
		star_luminosity = 1.1;
		distance_primary_au_s = 1.2;
		planet_radius = 5002.98;
		star_color_type = "g2";	
		_atmosphere3 = Atmosphere.genAtmosphere(star_luminosity, distance_primary_au_s, planet_radius, star_color_type);
		logger.info(_atmosphere3);
		logger.info("\n");
		star_luminosity = .4;
		distance_primary_au_s = 1.8;
		planet_radius = 6002.98;
		star_color_type = "m1";	
		_atmosphere4 = Atmosphere.genAtmosphere(star_luminosity, distance_primary_au_s, planet_radius, star_color_type);
		logger.info(_atmosphere4);
		logger.info("\n");

		star_luminosity = 1.2;
		distance_primary_au_s = 8.8;
		planet_radius = 12002.98;
		star_color_type = "m1";	
		_atmosphere5 = Atmosphere.genAtmosphere(star_luminosity, distance_primary_au_s, planet_radius, star_color_type);
		logger.info(_atmosphere5);
		logger.info("\n");
		star_luminosity = 1.1;
		distance_primary_au_s = 6.2;
		planet_radius = 20002.98;
		star_color_type = "m6";	
		_atmosphere6 = Atmosphere.genAtmosphere(star_luminosity, distance_primary_au_s, planet_radius, star_color_type);
		logger.info(_atmosphere6);
		logger.info("\n");
		star_luminosity = .4;
		distance_primary_au_s = 10.8;
		planet_radius = 30002.98;
		star_color_type = "m1";	
		_atmosphere7 = Atmosphere.genAtmosphere(star_luminosity, distance_primary_au_s, planet_radius, star_color_type);
		logger.info(_atmosphere7);
		logger.info("\n");
		star_luminosity = 1.1;
		distance_primary_au_s = 1.2;
		planet_radius = 6002.98;
		star_color_type = "g5";	
		_atmosphere8 = Atmosphere.genAtmosphere(star_luminosity, distance_primary_au_s, planet_radius, star_color_type);
		logger.info(_atmosphere8);
		logger.info("\n");

		List<Planetoid_Atmosphere> _list = Atmosphere.genList(_atmosphere.getAtmophereProfile(), "test_planetoid");
		List<Planetoid_Atmosphere> _list2 = Atmosphere.genList(_atmosphere2.getAtmophereProfile(), "test_planetoid2");
		List<Planetoid_Atmosphere> _list3 = Atmosphere.genList(_atmosphere3.getAtmophereProfile(), "test_planetoid3");
		List<Planetoid_Atmosphere> _list4 = Atmosphere.genList(_atmosphere4.getAtmophereProfile(), "test_planetoid4");
		assertTrue( _list.size() != 0 && _list2.size() != 0 );
	}
	

}
