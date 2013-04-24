package com.zenred.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import com.zenred.RunTimeLikeSetup;
import com.zenred.cosmos.StarAtributesIF;

public class GenerateAtmosphereTest2 {
	
	Logger logger = LoggerFactory.getLogger(GenerateAtmosphereTest2.class);

	private GenerateAtmosphere generateAtmosphere;

	@Test
	public void testGenAtmosphere() {
		// RunTimeLikeSetup runTimeLikeSetup = RunTimeLikeSetup.instance();
		// generateAtmosphere = (GenerateAtmosphere) runTimeLikeSetup.getBean("generateAtmosphere");
		generateAtmosphere = new GenerateAtmosphere();
		logger.info("starting {}", StarAtributesIF.RED__DWARF);
		AtmosphereDTO atmosphereDTO = generateAtmosphere.genAtmosphere(1.2, 1.5, 7000.0, StarAtributesIF.RED__DWARF);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__SUBDW);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.8, 1.1, 5000.0, StarAtributesIF.RED__SUBDW);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__SUBDW);
		atmosphereDTO = generateAtmosphere.genAtmosphere(1.2, 1.5, 7000.0, StarAtributesIF.RED__SUBDW);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__SG_II);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.8, 1.1, 5000.0, StarAtributesIF.RED__SG_II);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.BROWN_SUBS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(1.2, 1.5, 7000.0, StarAtributesIF.BROWN_SUBS);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.PURPLE_RED);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.8, 1.1, 5000.0, StarAtributesIF.PURPLE_RED);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__MAINS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(1.2, 1.5, 17000.0, StarAtributesIF.RED__MAINS);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__MAINS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.8, 1.1, 5000.0, StarAtributesIF.RED__MAINS);

		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__SUBDW);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.5, 1.1, 5000.0, StarAtributesIF.RED__SUBDW);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__SUBDW);
		atmosphereDTO = generateAtmosphere.genAtmosphere(3, 1.5, 7000.0, StarAtributesIF.RED__SUBDW);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__SG_II);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.7, 1.1, 5000.0, StarAtributesIF.RED__SG_II);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.BROWN_SUBS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(1.9, 1.5, 7000.0, StarAtributesIF.BROWN_SUBS);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.PURPLE_RED);
		atmosphereDTO = generateAtmosphere.genAtmosphere(.2, 1.1, 5000.0, StarAtributesIF.PURPLE_RED);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__MAINS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(6.7, 1.5, 17000.0, StarAtributesIF.RED__MAINS);
		logger.info("DTO: {}", atmosphereDTO);
		logger.info("starting {}", StarAtributesIF.RED__MAINS);
		atmosphereDTO = generateAtmosphere.genAtmosphere(9.9, 1.1, 5000.0, StarAtributesIF.RED__MAINS);

		assertNotNull(atmosphereDTO);
	}

	
}
