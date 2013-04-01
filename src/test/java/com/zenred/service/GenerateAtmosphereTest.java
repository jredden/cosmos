package com.zenred.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zenred.RunTimeLikeSetup;
import com.zenred.cosmos.StarAtributesIF;

public class GenerateAtmosphereTest {

	private GenerateAtmosphere generateAtmosphere;

	@Test
	public void testGenAtmosphere() {
		RunTimeLikeSetup runTimeLikeSetup = RunTimeLikeSetup.instance();
		generateAtmosphere = (GenerateAtmosphere) runTimeLikeSetup.getBean("generateAtmosphere");
		AtmosphereDTO atmosphereDTO = generateAtmosphere.genAtmosphere(1.2, 1.5, 7000.0, StarAtributesIF.YELO_MAINS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.8, 1.1, 5000.0, StarAtributesIF.YELO_MAINS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(1.2, 1.5, 7000.0, StarAtributesIF.BLUE_MAINS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.8, 1.1, 5000.0, StarAtributesIF.BLUE_MAINS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(1.2, 1.5, 7000.0, StarAtributesIF.WHIT_SUBDW);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.8, 1.1, 5000.0, StarAtributesIF.WHIT_SUBDW);
		assertNotNull(atmosphereDTO);
	}

	
}
