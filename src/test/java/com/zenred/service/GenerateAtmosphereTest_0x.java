package com.zenred.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import com.zenred.RunTimeLikeSetup;
import com.zenred.cosmos.StarAtributesIF;

public class GenerateAtmosphereTest_0x {

	Logger logger = LoggerFactory.getLogger(GenerateAtmosphereTest_0x.class);
	private GenerateAtmosphere generateAtmosphere;

	@Test
	public void testGenAtmosphere() {
//		RunTimeLikeSetup runTimeLikeSetup = RunTimeLikeSetup.instance();
//		generateAtmosphere = (GenerateAtmosphere) runTimeLikeSetup.getBean("generateAtmosphere");
		generateAtmosphere = new GenerateAtmosphere();
		AtmosphereDTO atmosphereDTO = generateAtmosphere.genAtmosphere(.8, 1.1, 5000.0, StarAtributesIF.DRKBRN_SDW);
		logger.info("DTO: {}", atmosphereDTO);
		assertNotNull(atmosphereDTO);
	}

	
}
