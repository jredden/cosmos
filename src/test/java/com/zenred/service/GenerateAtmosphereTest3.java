package com.zenred.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenred.RunTimeLikeSetup;
import com.zenred.cosmos.DrawRolls;
import com.zenred.cosmos.StarAtributesIF;
import com.zenred.util.DrawRandomAlphaNumeric;

public class GenerateAtmosphereTest3 {
	
	Logger logger = LoggerFactory.getLogger(GenerateAtmosphereTest3.class);

	private GenerateAtmosphere generateAtmosphere;
	
	static private List<String> listOfAttributes = new ArrayList<String>();
	static{
		listOfAttributes.add(StarAtributesIF.BLUE_DWARF);
		listOfAttributes.add(StarAtributesIF.BLUE_GI_I);
		listOfAttributes.add(StarAtributesIF.BLUE_GI_II);
		listOfAttributes.add(StarAtributesIF.BLUE_MAINS);
		listOfAttributes.add(StarAtributesIF.BLUE_SG_I);
		listOfAttributes.add(StarAtributesIF.BLUE_SG_II);
		listOfAttributes.add(StarAtributesIF.BLUE_SUBDW);
		listOfAttributes.add(StarAtributesIF.BLUE_SUBGI);
		listOfAttributes.add(StarAtributesIF.BROWN_SUBS);
		listOfAttributes.add(StarAtributesIF.DRKBRN_SDW);
		listOfAttributes.add(StarAtributesIF.LTBL_DWARF);
		listOfAttributes.add(StarAtributesIF.LTBL_GI_I);
		listOfAttributes.add(StarAtributesIF.LTBL_GI_II);
		listOfAttributes.add(StarAtributesIF.LTBL_MAINS);
		listOfAttributes.add(StarAtributesIF.LTBL_SG_I);
		listOfAttributes.add(StarAtributesIF.LTBL_SG_II);
		listOfAttributes.add(StarAtributesIF.LTBL_SUBDW);
		listOfAttributes.add(StarAtributesIF.LTBL_SUBGI);
		listOfAttributes.add(StarAtributesIF.ORNG_DWARF);
		listOfAttributes.add(StarAtributesIF.ORNG_GI_I);
		listOfAttributes.add(StarAtributesIF.ORNG_GI_II);
		listOfAttributes.add(StarAtributesIF.ORNG_MAINS);
		listOfAttributes.add(StarAtributesIF.ORNG_SG_I);
		listOfAttributes.add(StarAtributesIF.ORNG_SG_II);
		listOfAttributes.add(StarAtributesIF.ORNG_SUBDW);
		listOfAttributes.add(StarAtributesIF.ORNG_SUBGI);
		listOfAttributes.add(StarAtributesIF.PURPLE_RED);
		listOfAttributes.add(StarAtributesIF.PYEL_DWARF);
		listOfAttributes.add(StarAtributesIF.PYEL_GI_I);
		listOfAttributes.add(StarAtributesIF.PYEL_GI_II);
		listOfAttributes.add(StarAtributesIF.PYEL_MAINS);
		listOfAttributes.add(StarAtributesIF.PYEL_SG_I);
		listOfAttributes.add(StarAtributesIF.PYEL_SG_II);
		listOfAttributes.add(StarAtributesIF.PYEL_SUBDW);
		listOfAttributes.add(StarAtributesIF.PYEL_SUBGI);
		listOfAttributes.add(StarAtributesIF.RED__DWARF);
		listOfAttributes.add(StarAtributesIF.RED__GI_I);
		listOfAttributes.add(StarAtributesIF.RED__GI_II);
		listOfAttributes.add(StarAtributesIF.RED__MAINS);
		listOfAttributes.add(StarAtributesIF.RED__SG_I);
		listOfAttributes.add(StarAtributesIF.RED__SG_II);
		listOfAttributes.add(StarAtributesIF.RED__SUBDW);
		listOfAttributes.add(StarAtributesIF.RED__SUBGI);
		listOfAttributes.add(StarAtributesIF.WHIT_DWARF);
		listOfAttributes.add(StarAtributesIF.WHIT_GI_I);
		listOfAttributes.add(StarAtributesIF.WHIT_GI_II);
		listOfAttributes.add(StarAtributesIF.WHIT_MAINS);
		listOfAttributes.add(StarAtributesIF.WHIT_SG_I);
		listOfAttributes.add(StarAtributesIF.WHIT_SG_II);
		listOfAttributes.add(StarAtributesIF.WHIT_SUBDW);
		listOfAttributes.add(StarAtributesIF.WHIT_SUBGI);
		listOfAttributes.add(StarAtributesIF.YELO_DWARF);
		listOfAttributes.add(StarAtributesIF.YELO_GI_I);
		listOfAttributes.add(StarAtributesIF.YELO_GI_II);
		listOfAttributes.add(StarAtributesIF.YELO_MAINS);
		listOfAttributes.add(StarAtributesIF.YELO_SG_I);
		listOfAttributes.add(StarAtributesIF.YELO_SG_II);
		listOfAttributes.add(StarAtributesIF.YELO_SUBDW);
		listOfAttributes.add(StarAtributesIF.YELO_SUBGI);
	}

	private static int numberTests = 10;
	
	@Test
	public void testGenAtmosphere() {
		RunTimeLikeSetup runTimeLikeSetup = RunTimeLikeSetup.instance();
		generateAtmosphere = (GenerateAtmosphere) runTimeLikeSetup.getBean("generateAtmosphere");
		
		int range = listOfAttributes.size() - 1;
		
		while (numberTests > 0){
			
			int index = DrawRolls.Instance().getDraw(range);
			String color = listOfAttributes.get(index);
			double lumens = DrawRolls.Instance().getDraw(3.0) + 1.0;
			double distanceInAus = 0.3 +  DrawRolls.Instance().getDraw(17.0);
			double size = 1000.0 + DrawRolls.Instance().getDraw(17.0)*1000.0;

			logger.info("INPUT: {} {}", color, "lumens:" + lumens + " distance AUs:" + distanceInAus + " size:" + size );
			AtmosphereDTO atmosphereDTO = generateAtmosphere.genAtmosphere(lumens, distanceInAus, size, color);
			logger.info("DTO: {}", atmosphereDTO);
			assertNotNull(atmosphereDTO);
			--numberTests;
		}
	}

	
}
