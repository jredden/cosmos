package com.zenred.cosmos.dao.file;


import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.zenred.data_access.Planetoid;
import com.zenred.data_access.Planetoid_Atmosphere;
import com.zenred.data_access.Planetoidrep;

public class EmitPlanetoidDAOTest extends TestCase{
	
	private Planetoidrep planetoid_rep_0;
	private Planetoidrep planetoid_rep_1;
	private Planetoidrep planetoid_rep_2;
	private Planetoidrep planetoid_rep_3;

	private Planetoid planetoid_0;
	private Planetoid planetoid_1;
	private Planetoid planetoid_2;
	private Planetoid planetoid_3;
	private Planetoid_Atmosphere planetoid_0_atmosphere_0;
	private Planetoid_Atmosphere planetoid_0_atmosphere_1;
	private Planetoid_Atmosphere planetoid_0_atmosphere_2;
	private Planetoid_Atmosphere planetoid_1_atmosphere_0;
	private Planetoid_Atmosphere planetoid_1_atmosphere_1;
	private Planetoid_Atmosphere planetoid_1_atmosphere_2;
	private Planetoid_Atmosphere planetoid_1_atmosphere_3;
	private Planetoid_Atmosphere planetoid_1_atmosphere_4;
	private Planetoid_Atmosphere planetoid_2_atmosphere_0;
	private Planetoid_Atmosphere planetoid_2_atmosphere_1;
	private Planetoid_Atmosphere planetoid_2_atmosphere_2;
	private Planetoid_Atmosphere planetoid_2_atmosphere_3;
	private Planetoid_Atmosphere planetoid_2_atmosphere_4;
	private Planetoid_Atmosphere planetoid_2_atmosphere_5;
	private Planetoid_Atmosphere planetoid_3_atmosphere_0;
	private Planetoid_Atmosphere planetoid_3_atmosphere_1;
	private Planetoid_Atmosphere planetoid_3_atmosphere_2;

	private List<Planetoid_Atmosphere> planetoid_atmosphere_list;
	private List<Planetoid> planetoid_list;
	private List<Planetoidrep> planetoid_rep_list;
	

	@Before
	public void setUp() throws Exception {
		planetoid_0_atmosphere_0 = new Planetoid_Atmosphere();
		planetoid_0_atmosphere_0.setPlanetoidId("planetoid_0");
		planetoid_0_atmosphere_0.setChem_name("carbon dioxide");
		planetoid_0_atmosphere_0.setPercentage(88.23977);
		planetoid_0_atmosphere_1 = new Planetoid_Atmosphere();
		planetoid_0_atmosphere_1.setPlanetoidId("planetoid_0");
		planetoid_0_atmosphere_1.setChem_name("sulfuric acid");
		planetoid_0_atmosphere_1.setPercentage(10.798);
		planetoid_0_atmosphere_2 = new Planetoid_Atmosphere();
		planetoid_0_atmosphere_2.setPlanetoidId("planetoid_0");
		planetoid_0_atmosphere_2.setChem_name("nitrogen");
		planetoid_0_atmosphere_2.setPercentage(1.0923989);
		planetoid_0 = new Planetoid();
		planetoid_0.setDegree(78.9);
		planetoid_0.setDistance_au(1.3);
		planetoid_0.setPercentwater(0.0);
		planetoid_0.setPeriod(156.23);
		planetoid_0.setPlanetoidId("planetoid_0");
		planetoid_0.setRadius(6721.89);
		planetoid_0.setTemperature(234.819);
		planetoid_0.setDistancetopimary(13495700.34);
		planetoid_rep_0 = new Planetoidrep();
		planetoid_rep_0.setClusterId("cluster_0");
		planetoid_rep_0.setPlanetoidId("planetoid_0");
		planetoid_rep_0.setSystemId("system_0");

		planetoid_1_atmosphere_0 = new Planetoid_Atmosphere();
		planetoid_1_atmosphere_0.setPlanetoidId("planetoid_1");
		planetoid_1_atmosphere_0.setChem_name("carbon dioxide");
		planetoid_1_atmosphere_0.setPercentage(2.4589);
		planetoid_1_atmosphere_1 = new Planetoid_Atmosphere();
		planetoid_1_atmosphere_1.setPlanetoidId("planetoid_1");
		planetoid_1_atmosphere_1.setChem_name("oxygen");
		planetoid_1_atmosphere_1.setPercentage(18.50129);
		planetoid_1_atmosphere_2 = new Planetoid_Atmosphere();
		planetoid_1_atmosphere_2.setPlanetoidId("planetoid_1");
		planetoid_1_atmosphere_2.setChem_name("nitrogen");
		planetoid_1_atmosphere_2.setPercentage(59.9987);
		planetoid_1_atmosphere_3 = new Planetoid_Atmosphere();
		planetoid_1_atmosphere_3.setPlanetoidId("planetoid_1");
		planetoid_1_atmosphere_3.setChem_name("argon");
		planetoid_1_atmosphere_3.setPercentage(0.0899);
		planetoid_1_atmosphere_4 = new Planetoid_Atmosphere();
		planetoid_1_atmosphere_4.setPlanetoidId("planetoid_1");
		planetoid_1_atmosphere_4.setChem_name("ammonia");
		planetoid_1_atmosphere_4.setPercentage(0.004588);
		planetoid_1 = new Planetoid();
		planetoid_1.setDegree(267.34);
		planetoid_1.setDistance_au(1.5);
		planetoid_1.setPercentwater(0.0);
		planetoid_1.setPeriod(378.23);
		planetoid_1.setPlanetoidId("planetoid_1");
		planetoid_1.setRadius(7345.3451);
		planetoid_1.setTemperature(202.935);
		planetoid_1.setDistancetopimary(13495700.34*1.455);
		planetoid_rep_1 = new Planetoidrep();
		planetoid_rep_1.setClusterId("cluster_0");
		planetoid_rep_1.setPlanetoidId("planetoid_1");
		planetoid_rep_1.setSystemId("system_0");

		planetoid_2_atmosphere_0 = new Planetoid_Atmosphere();
		planetoid_2_atmosphere_0.setPlanetoidId("planetoid_2");
		planetoid_2_atmosphere_0.setChem_name("carbon dioxide");
		planetoid_2_atmosphere_0.setPercentage(0.672);
		planetoid_2_atmosphere_1 = new Planetoid_Atmosphere();
		planetoid_2_atmosphere_1.setPlanetoidId("planetoid_2");
		planetoid_2_atmosphere_1.setChem_name("oxygen");
		planetoid_2_atmosphere_1.setPercentage(2.0966);
		planetoid_2_atmosphere_2 = new Planetoid_Atmosphere();
		planetoid_2_atmosphere_2.setPlanetoidId("planetoid_2");
		planetoid_2_atmosphere_2.setChem_name("nitrogen");
		planetoid_2_atmosphere_2.setPercentage(96.592378);
		planetoid_2_atmosphere_3 = new Planetoid_Atmosphere();
		planetoid_2_atmosphere_3.setPlanetoidId("planetoid_2");
		planetoid_2_atmosphere_3.setChem_name("argon");
		planetoid_2_atmosphere_3.setPercentage(1.1989);
		planetoid_2_atmosphere_4 = new Planetoid_Atmosphere();
		planetoid_2_atmosphere_4.setPlanetoidId("planetoid_2");
		planetoid_2_atmosphere_4.setChem_name("ammonia");
		planetoid_2_atmosphere_4.setPercentage(0.97886);
		planetoid_2_atmosphere_5 = new Planetoid_Atmosphere();
		planetoid_2_atmosphere_5.setPlanetoidId("planetoid_2");
		planetoid_2_atmosphere_5.setChem_name("upsidasium");
		planetoid_2_atmosphere_5.setPercentage(0.000036);
		planetoid_2 = new Planetoid();
		planetoid_2.setDegree(19.593);
		planetoid_2.setDistance_au(2.4);
		planetoid_2.setPercentwater(0.0);
		planetoid_2.setPeriod(534.23);
		planetoid_2.setPlanetoidId("planetoid_2");
		planetoid_2.setRadius(4989.3451);
		planetoid_2.setTemperature(167.348);
		planetoid_2.setDistancetopimary(13495700.34*2.2);
		planetoid_rep_2 = new Planetoidrep();
		planetoid_rep_2.setClusterId("cluster_0");
		planetoid_rep_2.setPlanetoidId("planetoid_2");
		planetoid_rep_2.setSystemId("system_0");
		
		planetoid_3_atmosphere_0 = new Planetoid_Atmosphere();
		planetoid_3_atmosphere_0.setPlanetoidId("planetoid_3");
		planetoid_3_atmosphere_0.setChem_name("ammonia");
		planetoid_3_atmosphere_0.setPercentage(91.20907);
		planetoid_3_atmosphere_1 = new Planetoid_Atmosphere();
		planetoid_3_atmosphere_1.setPlanetoidId("planetoid_3");
		planetoid_3_atmosphere_1.setChem_name("helium");
		planetoid_3_atmosphere_1.setPercentage(9.84058);
		planetoid_3_atmosphere_2 = new Planetoid_Atmosphere();
		planetoid_3_atmosphere_2.setPlanetoidId("planetoid_3");
		planetoid_3_atmosphere_2.setChem_name("chlorine");
		planetoid_3_atmosphere_2.setPercentage(.0924542485);
		planetoid_3 = new Planetoid();
		planetoid_3.setDegree(176.33438);
		planetoid_3.setDistance_au(4.45);
		planetoid_3.setPercentwater(0.0);
		planetoid_3.setPeriod(876.23);
		planetoid_3.setPlanetoidId("planetoid_3");
		planetoid_3.setRadius(10676.89);
		planetoid_3.setTemperature(167.597);
		planetoid_3.setDistancetopimary(13495700.34*4.45);
		planetoid_rep_3 = new Planetoidrep();
		planetoid_rep_3.setClusterId("cluster_0");
		planetoid_rep_3.setPlanetoidId("planetoid_3");
		planetoid_rep_3.setSystemId("system_0");

		planetoid_atmosphere_list = new ArrayList<Planetoid_Atmosphere>();
		
		planetoid_atmosphere_list.add(planetoid_0_atmosphere_0);
		planetoid_atmosphere_list.add(planetoid_0_atmosphere_1);
		planetoid_atmosphere_list.add(planetoid_0_atmosphere_2);		

		planetoid_atmosphere_list.add(planetoid_1_atmosphere_0);
		planetoid_atmosphere_list.add(planetoid_1_atmosphere_1);
		planetoid_atmosphere_list.add(planetoid_1_atmosphere_2);		
		planetoid_atmosphere_list.add(planetoid_1_atmosphere_3);
		planetoid_atmosphere_list.add(planetoid_1_atmosphere_4);

		planetoid_atmosphere_list.add(planetoid_2_atmosphere_0);
		planetoid_atmosphere_list.add(planetoid_2_atmosphere_1);
		planetoid_atmosphere_list.add(planetoid_2_atmosphere_2);
		planetoid_atmosphere_list.add(planetoid_2_atmosphere_3);
		planetoid_atmosphere_list.add(planetoid_2_atmosphere_4);
		planetoid_atmosphere_list.add(planetoid_2_atmosphere_5);

		planetoid_atmosphere_list.add(planetoid_3_atmosphere_0);
		planetoid_atmosphere_list.add(planetoid_3_atmosphere_1);
		planetoid_atmosphere_list.add(planetoid_3_atmosphere_2);	
		
		planetoid_list = new ArrayList<Planetoid>();
		planetoid_list.add(planetoid_0);
		planetoid_list.add(planetoid_1);
		planetoid_list.add(planetoid_2);
		planetoid_list.add(planetoid_3);
		
		planetoid_rep_list = new ArrayList<Planetoidrep>();
		planetoid_rep_list.add(planetoid_rep_0);
		planetoid_rep_list.add(planetoid_rep_1);
		planetoid_rep_list.add(planetoid_rep_2);
		planetoid_rep_list.add(planetoid_rep_3);

	}
	
	@Test
	public void testEmitPlanetoidDAO(){
		EmitPlanetoidDAO _emit_planetoid_dao = new EmitPlanetoidDAO();
		_emit_planetoid_dao.genPlanetoids(planetoid_rep_list, planetoid_list, planetoid_atmosphere_list);
	}

}
