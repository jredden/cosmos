package com.zenred.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.event.DebugAgendaEventListener;
import org.drools.event.DebugWorkingMemoryEventListener;
import org.drools.rule.Package;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenred.cosmos.DrawRolls;
import com.zenred.cosmos.StarAtributesIF;

public class GenerateAtmosphere implements StarAtributesIF {

	Logger logger = LoggerFactory.getLogger(GenerateAtmosphere.class);
	private String ruleFile;
	private String ruleFile2;
	private RuleBase ruleBase;
	private Reader source;
	private Double starLuminosity;
	private Double distancePrimaryInAUs;
	private Double planetRadius;
	private String colorType;
	private boolean strict;
	private boolean flexible;
	private boolean goofy;

	private AtmosphereDTO atmosphereDTO;
	private String effects; // like frozen, internally emitted ...
	
	// drools files for each type and their rules
	static{
		Map<String, String> drlFileMap = new HashMap<String, String>();
		drlFileMap.put(BLUE_SG_II, "BLUE_SG_II.drl");
		drlFileMap.put(LTBL_SG_II, "LTBL_SG_II.drl");
		drlFileMap.put(WHIT_SG_II, "WHIT_SG_II.drl");
		drlFileMap.put(PYEL_SG_II, "PYEL_SG_II.drl");
		drlFileMap.put(YELO_SG_II, "YELO_SG_II.drl");
		drlFileMap.put(ORNG_SG_II, "ORNG_SG_II.drl");
		drlFileMap.put(RED__SG_II, "RED__SG_II.drl");
		drlFileMap.put(BLUE_SG_I, "BLUE_SG_I.drl");
		drlFileMap.put(LTBL_SG_I, "LTBL_SG_I.drl");
		drlFileMap.put(WHIT_SG_I, "WHIT_SG_I.drl");
		drlFileMap.put(PYEL_SG_I, "PYEL_SG_I.drl");
		drlFileMap.put(YELO_SG_I, "YELO_SG_I.drl");
		drlFileMap.put(ORNG_SG_I, "ORNG_SG_I.drl");
		drlFileMap.put(RED__SG_I, "RED__SG_I.drl");
		drlFileMap.put(BLUE_GI_II, "BLUE_GI_II.drl");
		drlFileMap.put(LTBL_GI_II, "LTBL_GI_II.drl");
		drlFileMap.put(WHIT_GI_II, "WHIT_GI_II.drl");
		drlFileMap.put(PYEL_GI_II, "PYEL_GI_II.drl");
		drlFileMap.put(YELO_GI_II, "YELO_GI_II.drl");
		drlFileMap.put(ORNG_GI_II, "ORNG_GI_II.drl");
		drlFileMap.put(RED__GI_II, "RED__GI_II.drl");
		drlFileMap.put(BLUE_GI_I, "BLUE_GI_I.drl");
		drlFileMap.put(LTBL_GI_I, "LTBL_GI_I.drl");
		drlFileMap.put(WHIT_GI_I, "WHIT_GI_I.drl");
		drlFileMap.put(PYEL_GI_I, "PYEL_GI_I.drl");
		drlFileMap.put(YELO_GI_I, "YELO_GI_I.drl");
		drlFileMap.put(ORNG_GI_I, "ORNG_GI_I.drl");
		drlFileMap.put(RED__GI_I, "RED__GI_I.drl");
		drlFileMap.put(BLUE_SUBGI, "BLUE_SUBGI.drl");
		drlFileMap.put(LTBL_SUBGI, "LTBL_SUBGI.drl");
		drlFileMap.put(WHIT_SUBGI, "WHIT_SUBGI.drl");
		drlFileMap.put(PYEL_SUBGI, "PYEL_SUBGI.drl");
		drlFileMap.put(YELO_SUBGI, "YELO_SUBGI.drl");
		drlFileMap.put(ORNG_SUBGI, "ORNG_SUBGI.drl");
		drlFileMap.put(RED__SUBGI, "RED__SUBGI.drl");
		drlFileMap.put(BLUE_MAINS, "BLUE_MAINS.drl");
		drlFileMap.put(LTBL_MAINS, "LTBL_MAINS.drl");
		drlFileMap.put(WHIT_MAINS, "WHIT_MAINS.drl");
		drlFileMap.put(PYEL_MAINS, "PYEL_MAINS.drl");
		drlFileMap.put(YELO_MAINS, "YELO_MAINS.drl");
		drlFileMap.put(ORNG_MAINS, "ORNG_MAINS.drl");
		drlFileMap.put(RED__MAINS, "RED__MAINS.drl");
		drlFileMap.put(BLUE_SUBDW, "BLUE_SUBDW.drl");
		drlFileMap.put(LTBL_SUBDW, "LTBL_SUBDW.drl");
		drlFileMap.put(WHIT_SUBDW, "WHIT_SUBDW.drl");
		drlFileMap.put(PYEL_SUBDW, "PYEL_SUBDW.drl");
		drlFileMap.put(YELO_SUBDW, "YELO_SUBDW.drl");
		drlFileMap.put(ORNG_SUBDW, "ORNG_SUBDW.drl");
		drlFileMap.put(RED__SUBDW, "RED__SUBDW.drl");
		drlFileMap.put(BLUE_DWARF, "BLUE_DWARF.drl");
		drlFileMap.put(LTBL_DWARF, "LTBL_DWARF.drl");
		drlFileMap.put(WHIT_DWARF, "WHIT_DWARF.drl");
		drlFileMap.put(PYEL_DWARF, "PYEL_DWARF.drl");
		drlFileMap.put(YELO_DWARF, "YELO_DWARF.drl");
		drlFileMap.put(ORNG_DWARF, "ORNG_DWARF.drl");
		drlFileMap.put(RED__DWARF, "RED__DWARF.drl");
		drlFileMap.put(PURPLE_RED, "PURPLE_RED.drl");
		drlFileMap.put(BROWN_SUBS, "BROWN_SUBS.drl");		
	}

	
		Map<String, List<StarToChemicalProfile>> atmosphereProfileMap = new HashMap<String, List<StarToChemicalProfile>>();

		List<StarToChemicalProfile> BLUE_SG_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> LTBL_SG_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> WHIT_SG_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> PYEL_SG_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> YELO_SG_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> ORNG_SG_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> RED__SG_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> BLUE_SG_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> LTBL_SG_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> WHIT_SG_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> PYEL_SG_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> YELO_SG_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> ORNG_SG_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> RED__SG_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> BLUE_GI_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> LTBL_GI_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> WHIT_GI_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> PYEL_GI_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> YELO_GI_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> ORNG_GI_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> RED__GI_II_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> BLUE_GI_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> LTBL_GI_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> WHIT_GI_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> PYEL_GI_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> YELO_GI_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> ORNG_GI_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> RED__GI_I_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> BLUE_SUBGI_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> LTBL_SUBGI_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> WHIT_SUBGI_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> PYEL_SUBGI_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> YELO_SUBGI_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> ORNG_SUBGI_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> RED__SUBGI_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> BLUE_MAINS_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> LTBL_MAINS_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> WHIT_MAINS_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> PYEL_MAINS_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> YELO_MAINS_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> ORNG_MAINS_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> RED__MAINS_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> BLUE_SUBDW_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> LTBL_SUBDW_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> WHIT_SUBDW_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> PYEL_SUBDW_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> YELO_SUBDW_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> ORNG_SUBDW_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> RED__SUBDW_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> BLUE_DWARF_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> LTBL_DWARF_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> WHIT_DWARF_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> PYEL_DWARF_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> YELO_DWARF_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> ORNG_DWARF_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> RED__DWARF_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> PURPLE_RED_TypeProfile = new ArrayList<StarToChemicalProfile>();
		List<StarToChemicalProfile> BROWN_SUBS_TypeProfile = new ArrayList<StarToChemicalProfile>();

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		public GenerateAtmosphere(){
		StarToChemicalProfile starToChemicalProfile = new StarToChemicalProfile();
		
		// BLUE_SG_II
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(66.0); 
		starToChemicalProfile.setWeightDuringAnalysis(3.0); // high rates more
		BLUE_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(47.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		BLUE_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(66.0);
		BLUE_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		BLUE_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		BLUE_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		BLUE_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(1.0);
		BLUE_SG_II_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(BLUE_SG_II, BLUE_SG_II_TypeProfile);

		// LTBL_SG_II
			
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(64.0); 
		starToChemicalProfile.setWeightDuringAnalysis(4.0); // high rates more
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(45.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(64.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(14.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(11.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(LTBL_SG_II, LTBL_SG_II_TypeProfile);
		
		// WHIT_SG_II

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(62.0); 
		starToChemicalProfile.setWeightDuringAnalysis(5.0); // high rates more probable
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(49.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(7.0);
		starToChemicalProfile.setWeightDuringAnalysis(68.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(54.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(84.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(24.0);
		starToChemicalProfile.setWeightDuringAnalysis(16.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		
		atmosphereProfileMap.put(WHIT_SG_II, WHIT_SG_II_TypeProfile);

		// YELO_SG_II
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0); 
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high rates more likely
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(11.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(69.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(15.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(32.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(YELO_SG_II, YELO_SG_II_TypeProfile);
		atmosphereProfileMap.put(PYEL_SG_II, YELO_SG_II_TypeProfile);
		
		
		// ORNG_SG_II
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(66.0); 
		starToChemicalProfile.setWeightDuringAnalysis(3.0); // high rates more higher probability
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(47.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(66.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(1.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_II_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(ORNG_SG_II, ORNG_SG_II_TypeProfile);
				
		// RED__SG_II
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(66.0); 
		starToChemicalProfile.setWeightDuringAnalysis(3.0); // high means increased probability
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(47.0);
		starToChemicalProfile.setWeightDuringAnalysis(1.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(66.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(1.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__SG_II, RED__SG_II_TypeProfile);
			
	
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		// BLUE_SG_I
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(68.0); 
		starToChemicalProfile.setWeightDuringAnalysis(4.0); // high means greater probability
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(45.0);
		starToChemicalProfile.setWeightDuringAnalysis(11.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(68.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(97.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(BLUE_SG_I, BLUE_SG_I_TypeProfile);

		// LTBL_SG_1
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(62.0); 
		starToChemicalProfile.setWeightDuringAnalysis(4.0); // high rates more
		LTBL_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(43.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		LTBL_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(64.0);
		LTBL_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		LTBL_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(51.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		LTBL_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		LTBL_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(14.0);
		LTBL_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(11.0);
		LTBL_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		LTBL_SG_I_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(LTBL_SG_I, LTBL_SG_I_TypeProfile);
		
		// WHIT_SG_I

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0); 
		starToChemicalProfile.setWeightDuringAnalysis(7.0); // high rates more probable
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(51.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(7.0);
		starToChemicalProfile.setWeightDuringAnalysis(70.0);
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(84.0);
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		WHIT_SG_I_TypeProfile.add(starToChemicalProfile);
	
		atmosphereProfileMap.put(WHIT_SG_I, WHIT_SG_I_TypeProfile);

		// YELO_SG_I
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more likely
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(11.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(72.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(19.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(32.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SG_I_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(YELO_SG_I, YELO_SG_I_TypeProfile);
		atmosphereProfileMap.put(PYEL_SG_I, YELO_SG_I_TypeProfile);
		
		// ORNG_SG_I
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0); 
		starToChemicalProfile.setWeightDuringAnalysis(5.0); // high rates more higher probability
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(47.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(68.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SG_I_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(ORNG_SG_I, ORNG_SG_I_TypeProfile);
				
		// RED__SG_I
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0); 
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high means increased probability
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(68.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(1.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(17.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__SG_I, RED__SG_I_TypeProfile);
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		// BLUE_GI_II
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(70.0); 
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high means greater probability
		BLUE_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(43.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		BLUE_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(70.0);
		BLUE_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		BLUE_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		BLUE_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		BLUE_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(21.0);
		starToChemicalProfile.setWeightDuringAnalysis(14.0);
		BLUE_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		BLUE_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		BLUE_GI_II_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(BLUE_GI_II, BLUE_GI_II_TypeProfile);

		// LTBL_GI_II
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0); 
		starToChemicalProfile.setWeightDuringAnalysis(4.0); // high rates more
		LTBL_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(43.0);
		starToChemicalProfile.setWeightDuringAnalysis(16.0);
		LTBL_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(66.0);
		LTBL_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		LTBL_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		LTBL_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		LTBL_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(16.0);
		LTBL_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(11.0);
		LTBL_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		LTBL_GI_II_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(LTBL_GI_II, LTBL_GI_II_TypeProfile);

		// WHIT_GI_II

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(56.0); 
		starToChemicalProfile.setWeightDuringAnalysis(7.0); // high rates more probable
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(72.0);
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		WHIT_GI_II_TypeProfile.add(starToChemicalProfile);
	
		atmosphereProfileMap.put(WHIT_GI_II, WHIT_GI_II_TypeProfile);

		// YELO_GI_II
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0); 
		starToChemicalProfile.setWeightDuringAnalysis(10.0); // high rates more likely
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(14.0);
		starToChemicalProfile.setWeightDuringAnalysis(75.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(97.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(35.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(44.0);
		starToChemicalProfile.setWeightDuringAnalysis(14.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		YELO_GI_II_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(YELO_GI_II, YELO_GI_II_TypeProfile);
		atmosphereProfileMap.put(PYEL_GI_II, YELO_SG_I_TypeProfile);
		
		// ORNG_GI_II
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(65.0); 
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high rates more higher probability
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(70.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(54.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		ORNG_GI_II_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(ORNG_GI_II, ORNG_GI_II_TypeProfile);

		// RED__GI_II
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(62.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means increased probability
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(44.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(70.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(7.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(54.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(22.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__GI_II, RED__GI_II_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		// BLUE_SUBGI
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means greater probability
		BLUE_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(75.0);
		BLUE_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		BLUE_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(54.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		BLUE_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		BLUE_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		BLUE_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		BLUE_SUBGI_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(BLUE_SUBGI, BLUE_SUBGI_TypeProfile);

		// LTBL_SUBGI
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(62.0); 
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high rates more
		LTBL_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(45.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		LTBL_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(70.0);
		LTBL_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		LTBL_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(54.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		LTBL_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		LTBL_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(24.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		LTBL_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		LTBL_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		LTBL_SUBGI_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(LTBL_SUBGI, LTBL_SUBGI_TypeProfile);

		// WHIT_SUBGI

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0); 
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more probable
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(74.0);
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(54.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(22.0);
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(32.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		WHIT_SUBGI_TypeProfile.add(starToChemicalProfile);
	
		atmosphereProfileMap.put(WHIT_SUBGI, WHIT_SUBGI_TypeProfile);

		// YELO_SUBGI
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0); 
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high rates more likely
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(78.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(97.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(38.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(44.0);
		starToChemicalProfile.setWeightDuringAnalysis(14.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(9.0);
		starToChemicalProfile.setWeightDuringAnalysis(19.0);
		YELO_SUBGI_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(YELO_SUBGI, YELO_SUBGI_TypeProfile);
		atmosphereProfileMap.put(PYEL_SUBGI, YELO_SG_I_TypeProfile);
		
		// ORNG_SUBGI
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(70.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more higher probability
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(75.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(29.0);
		starToChemicalProfile.setWeightDuringAnalysis(24.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(39.0);
		starToChemicalProfile.setWeightDuringAnalysis(19.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(16.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		ORNG_SUBGI_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(ORNG_SUBGI, ORNG_SUBGI_TypeProfile);

		// RED__SUBGI
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(72.0); 
		starToChemicalProfile.setWeightDuringAnalysis(10.0); // high means increased probability
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(74.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(15.0);
		starToChemicalProfile.setWeightDuringAnalysis(28.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(7.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(29.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(29.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(28.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__SUBGI, RED__SUBGI_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		// BLUE_MAINS
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(62.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means greater probability
		BLUE_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(75.0);
		BLUE_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		BLUE_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		BLUE_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		BLUE_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(31.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		BLUE_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		BLUE_MAINS_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(BLUE_MAINS, BLUE_MAINS_TypeProfile);

		// LTBL_MAINS
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(62.0); 
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high rates more
		LTBL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(46.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		LTBL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(72.0);
		LTBL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		LTBL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		LTBL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		LTBL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		LTBL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		LTBL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		LTBL_MAINS_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(LTBL_MAINS, LTBL_MAINS_TypeProfile);

		// WHIT_MAINS

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0); 
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more probable
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(74.0);
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(54.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(22.0);
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(32.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		WHIT_MAINS_TypeProfile.add(starToChemicalProfile);
	
		atmosphereProfileMap.put(WHIT_MAINS, WHIT_MAINS_TypeProfile);

		// PYEL_MAINS

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(45.0); 
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more probable
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(78.0);
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(22.0);
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		PYEL_MAINS_TypeProfile.add(starToChemicalProfile);
	
		atmosphereProfileMap.put(PYEL_MAINS, PYEL_MAINS_TypeProfile);

		// YELO_MAINS
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0); 
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high rates more likely
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(78.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(45.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(95.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(42.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(7.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		YELO_MAINS_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(YELO_MAINS, YELO_MAINS_TypeProfile);

		// ORNG_MAINS
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(75.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more higher probability
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0);
		starToChemicalProfile.setWeightDuringAnalysis(25.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(15.0);
		starToChemicalProfile.setWeightDuringAnalysis(85.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(25.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		ORNG_MAINS_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(ORNG_MAINS, ORNG_MAINS_TypeProfile);

		// RED__MAINS
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(74.0); 
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high means increased probability
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(85.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(3.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(25.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(80.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(15.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__MAINS, RED__MAINS_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		// BLUE_SUBDW
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means greater probability
		BLUE_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(7.0);
		starToChemicalProfile.setWeightDuringAnalysis(75.0);
		BLUE_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(7.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		BLUE_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		BLUE_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		BLUE_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		BLUE_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		BLUE_SUBDW_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(BLUE_SUBDW, BLUE_SUBDW_TypeProfile);

		// LTBL_SUBDW
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0); 
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high rates more
		LTBL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		LTBL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(72.0);
		LTBL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		LTBL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(46.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		LTBL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		LTBL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		LTBL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(32.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		LTBL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		LTBL_SUBDW_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(LTBL_SUBDW, LTBL_SUBDW_TypeProfile);

		// WHIT_SUBDW

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(54.0); 
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more probable
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(74.0);
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(22.0);
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		WHIT_SUBDW_TypeProfile.add(starToChemicalProfile);
	
		atmosphereProfileMap.put(WHIT_SUBDW, WHIT_SUBDW_TypeProfile);

		// PYEL_SUBDW

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0); 
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more probable
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(78.0);
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(3.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(22.0);
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		PYEL_SUBDW_TypeProfile.add(starToChemicalProfile);
	
		atmosphereProfileMap.put(PYEL_SUBDW, PYEL_SUBDW_TypeProfile);

		// YELO_SUBDW
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(32.0); 
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high rates more likely
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(78.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(95.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(42.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(YELO_SUBDW, YELO_SUBDW_TypeProfile);

		// ORNG_SUBDW
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(70.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more higher probability
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(25.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(85.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(25.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(ORNG_SUBDW, ORNG_SUBDW_TypeProfile);
		
		// RED__SUBDW
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(74.0); 
		starToChemicalProfile.setWeightDuringAnalysis(14.0); // high means increased probability
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(3.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(26.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(3.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(25.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(84.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__SUBDW, RED__SUBDW_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		// BLUE_DWARF
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means greater probability
		BLUE_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(80.0);
		BLUE_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		BLUE_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		BLUE_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		BLUE_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		BLUE_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(9.0);
		BLUE_DWARF_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(BLUE_DWARF, BLUE_DWARF_TypeProfile);

		// LTBL_DWARF
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(53.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more
		LTBL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(22.0);
		LTBL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(76.0);
		LTBL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(3.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		LTBL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		LTBL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		LTBL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(22.0);
		LTBL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		LTBL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(24.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		LTBL_DWARF_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(LTBL_DWARF, LTBL_DWARF_TypeProfile);

		// WHIT_DWARF

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(56.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more probable
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(78.0);
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(28.0);
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(32.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		WHIT_DWARF_TypeProfile.add(starToChemicalProfile);
	
		atmosphereProfileMap.put(WHIT_DWARF, WHIT_DWARF_TypeProfile);

		// PYEL_DWARF

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0); 
		starToChemicalProfile.setWeightDuringAnalysis(11.0); // high rates more probable
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(25.0);
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(80.0);
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(3.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(28.0);
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(24.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(18.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PYEL_DWARF_TypeProfile.add(starToChemicalProfile);
	
		atmosphereProfileMap.put(PYEL_DWARF, PYEL_DWARF_TypeProfile);

		// YELO_DWARF
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0); 
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high rates more likely
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(78.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(14.0);
		starToChemicalProfile.setWeightDuringAnalysis(95.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
		starToChemicalProfile.setWeightDuringAnalysis(42.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(36.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(22.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(9.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(45.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(YELO_DWARF, YELO_DWARF_TypeProfile);

		// ORNG_DWARF
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0); 
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more higher probability
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(28.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(89.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(96.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(26.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(24.0);
		starToChemicalProfile.setWeightDuringAnalysis(28.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(24.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(14.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(ORNG_DWARF, ORNG_DWARF_TypeProfile);
		
		// RED__DWARF
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0); 
		starToChemicalProfile.setWeightDuringAnalysis(14.0); // high means increased probability
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(3.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(46.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(94.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(28.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(8.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(32.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.SulfuricAcid);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(28.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(48.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(RED__DWARF, RED__DWARF_TypeProfile);
		
		// PURPLE RED dwarf
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(50.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ammonia);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Chlorine);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methlacetylene);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ozone);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(PURPLE_RED,PURPLE_RED_TypeProfile);

		// BROWN_SUBS dwarf
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(50.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ammonia);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Chlorine);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methlacetylene);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ozone);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(BROWN_SUBS,BROWN_SUBS_TypeProfile);
		
		strict = false;
		flexible = false;
		goofy = false;
		}
		
	public AtmosphereDTO genAtmosphere(double star_luminosity,
			double distance_primary_au_s, double planet_radius,
			String star_color_type) {

		logger.info("start genAtmosphere");
		
		this.starLuminosity = star_luminosity;
		this.distancePrimaryInAUs = distance_primary_au_s;
		this.planetRadius = planet_radius;
		this.colorType = star_color_type;

		nextRuleExecution(ruleFile);
		nextRuleExecution(ruleFile2);

		atmosphereDTO = new AtmosphereDTO();
		return atmosphereDTO;
	}

	/**
	 * rule files are executed in sequence
	 * 
	 * @param ruleFile
	 */
	private void nextRuleExecution(String ruleFile) {
		if (ruleBase == null) {
			source = new InputStreamReader(
					GenerateAtmosphere.class.getResourceAsStream((ruleFile)));
		}

		PackageBuilder builder = new PackageBuilder();
		try {
			builder.addPackageFromDrl(source);
		} catch (DroolsParserException dpe) {
			dpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		if (builder.hasErrors()) {
			logger.error(builder.getErrors().toString());
			String _fail = "Unable to compile \"" + ruleFile + "\".";
			try {
				throw new AtmosphereGenException(_fail);
			} catch (AtmosphereGenException e0) {
				e0.printStackTrace();
			}
		}

		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		Package pkg = builder.getPackage();
		logger.info("add package next");
		try {
			ruleBase.addPackage(pkg);
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				throw new AtmosphereGenException(
						"Could not load rules file", e1);
			} catch (AtmosphereGenException e2) {
				e2.printStackTrace();	// bugger ...
			}
		}

		final StatefulSession session = ruleBase.newStatefulSession();
		
		session.addEventListener(new DebugAgendaEventListener());
		session.addEventListener(new DebugWorkingMemoryEventListener());
		session.insert(this);
		logger.info("firing all rules");
		session.fireAllRules();
		
	}

	public void setRuleFile(String ruleFile) {
		this.ruleFile = ruleFile;
	}
	public void setRuleFile2(String ruleFile2) {
		this.ruleFile2 = ruleFile2;
	}

	public Double getStarLuminosity() {
		return starLuminosity;
	}

	public void setStarLuminosity(Double starLuminosity) {
		this.starLuminosity = starLuminosity;
	}

	public Double getDistancePrimaryInAUs() {
		return distancePrimaryInAUs;
	}

	public void setDistancePrimaryInAUs(Double distancePrimaryInAUs) {
		this.distancePrimaryInAUs = distancePrimaryInAUs;
	}

	public Double getPlanetRadius() {
		return planetRadius;
	}

	public void setPlanetRadius(Double planetRadius) {
		this.planetRadius = planetRadius;
	}

	public String getColorType() {
		return colorType;
	}

	public void setColorType(String colorType) {
		this.colorType = colorType;
	}

	public String getEffects() {
		return effects;
	}

	public void setEffects(String effects) {
		this.effects = effects;
	}
	
	public List<StarToChemicalProfile> getAtmosphereProfileMap(String starColor){
		return atmosphereProfileMap.get(starColor);
	}

	public void strictDraw(List<StarToChemicalProfile> profileList){
		logger.info("in strict {} ", profileList.getClass().getName());
		strict = true;
	}
	
	public void flexDraw(List<StarToChemicalProfile> profileList){
		logger.info("in flex {} ", profileList.getClass().getName());
		flexible = true;
	}
	
	public void goofyDraw(List<StarToChemicalProfile> profileList){
		logger.info("in goofy {} ", profileList.getClass().getName());
		goofy = true;
	}
	
	public Boolean isDefaultState(){
		logger.info("test default");
		if(!strict && !flexible && !goofy){
			logger.info("default draw");
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public int drawRandom10(){
		int draw = DrawRolls.Instance().get_D10();
		logger.info("D10 draw: {}", draw);
		return draw;		
	}
	
	public int drawRandom100(){
		return DrawRolls.Instance().getD100();
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setRuleBase(RuleBase ruleBase) {
		this.ruleBase = ruleBase;
	}

	public void setSource(Reader source) {
		this.source = source;
	}

	public void setStrict(boolean strict) {
		this.strict = strict;
	}

	public void setFlexible(boolean flexible) {
		this.flexible = flexible;
	}

	public void setGoofy(boolean goofy) {
		this.goofy = goofy;
	}

	public void setAtmosphereDTO(AtmosphereDTO atmosphereDTO) {
		this.atmosphereDTO = atmosphereDTO;
	}

	public void setAtmosphereProfileMap(
			Map<String, List<StarToChemicalProfile>> atmosphereProfileMap) {
		this.atmosphereProfileMap = atmosphereProfileMap;
	}

	public void setBLUE_SG_II_TypeProfile(
			List<StarToChemicalProfile> bLUE_SG_II_TypeProfile) {
		BLUE_SG_II_TypeProfile = bLUE_SG_II_TypeProfile;
	}

	public void setLTBL_SG_II_TypeProfile(
			List<StarToChemicalProfile> lTBL_SG_II_TypeProfile) {
		LTBL_SG_II_TypeProfile = lTBL_SG_II_TypeProfile;
	}

	public void setWHIT_SG_II_TypeProfile(
			List<StarToChemicalProfile> wHIT_SG_II_TypeProfile) {
		WHIT_SG_II_TypeProfile = wHIT_SG_II_TypeProfile;
	}

	public void setPYEL_SG_II_TypeProfile(
			List<StarToChemicalProfile> pYEL_SG_II_TypeProfile) {
		PYEL_SG_II_TypeProfile = pYEL_SG_II_TypeProfile;
	}

	public void setYELO_SG_II_TypeProfile(
			List<StarToChemicalProfile> yELO_SG_II_TypeProfile) {
		YELO_SG_II_TypeProfile = yELO_SG_II_TypeProfile;
	}

	public void setORNG_SG_II_TypeProfile(
			List<StarToChemicalProfile> oRNG_SG_II_TypeProfile) {
		ORNG_SG_II_TypeProfile = oRNG_SG_II_TypeProfile;
	}

	public void setRED__SG_II_TypeProfile(
			List<StarToChemicalProfile> rED__SG_II_TypeProfile) {
		RED__SG_II_TypeProfile = rED__SG_II_TypeProfile;
	}

	public void setBLUE_SG_I_TypeProfile(
			List<StarToChemicalProfile> bLUE_SG_I_TypeProfile) {
		BLUE_SG_I_TypeProfile = bLUE_SG_I_TypeProfile;
	}

	public void setLTBL_SG_I_TypeProfile(
			List<StarToChemicalProfile> lTBL_SG_I_TypeProfile) {
		LTBL_SG_I_TypeProfile = lTBL_SG_I_TypeProfile;
	}

	public void setWHIT_SG_I_TypeProfile(
			List<StarToChemicalProfile> wHIT_SG_I_TypeProfile) {
		WHIT_SG_I_TypeProfile = wHIT_SG_I_TypeProfile;
	}

	public void setPYEL_SG_I_TypeProfile(
			List<StarToChemicalProfile> pYEL_SG_I_TypeProfile) {
		PYEL_SG_I_TypeProfile = pYEL_SG_I_TypeProfile;
	}

	public void setYELO_SG_I_TypeProfile(
			List<StarToChemicalProfile> yELO_SG_I_TypeProfile) {
		YELO_SG_I_TypeProfile = yELO_SG_I_TypeProfile;
	}

	public void setORNG_SG_I_TypeProfile(
			List<StarToChemicalProfile> oRNG_SG_I_TypeProfile) {
		ORNG_SG_I_TypeProfile = oRNG_SG_I_TypeProfile;
	}

	public void setRED__SG_I_TypeProfile(
			List<StarToChemicalProfile> rED__SG_I_TypeProfile) {
		RED__SG_I_TypeProfile = rED__SG_I_TypeProfile;
	}

	public void setBLUE_GI_II_TypeProfile(
			List<StarToChemicalProfile> bLUE_GI_II_TypeProfile) {
		BLUE_GI_II_TypeProfile = bLUE_GI_II_TypeProfile;
	}

	public void setLTBL_GI_II_TypeProfile(
			List<StarToChemicalProfile> lTBL_GI_II_TypeProfile) {
		LTBL_GI_II_TypeProfile = lTBL_GI_II_TypeProfile;
	}

	public void setWHIT_GI_II_TypeProfile(
			List<StarToChemicalProfile> wHIT_GI_II_TypeProfile) {
		WHIT_GI_II_TypeProfile = wHIT_GI_II_TypeProfile;
	}

	public void setPYEL_GI_II_TypeProfile(
			List<StarToChemicalProfile> pYEL_GI_II_TypeProfile) {
		PYEL_GI_II_TypeProfile = pYEL_GI_II_TypeProfile;
	}

	public void setYELO_GI_II_TypeProfile(
			List<StarToChemicalProfile> yELO_GI_II_TypeProfile) {
		YELO_GI_II_TypeProfile = yELO_GI_II_TypeProfile;
	}

	public void setORNG_GI_II_TypeProfile(
			List<StarToChemicalProfile> oRNG_GI_II_TypeProfile) {
		ORNG_GI_II_TypeProfile = oRNG_GI_II_TypeProfile;
	}

	public void setRED__GI_II_TypeProfile(
			List<StarToChemicalProfile> rED__GI_II_TypeProfile) {
		RED__GI_II_TypeProfile = rED__GI_II_TypeProfile;
	}

	public void setBLUE_GI_I_TypeProfile(
			List<StarToChemicalProfile> bLUE_GI_I_TypeProfile) {
		BLUE_GI_I_TypeProfile = bLUE_GI_I_TypeProfile;
	}

	public void setLTBL_GI_I_TypeProfile(
			List<StarToChemicalProfile> lTBL_GI_I_TypeProfile) {
		LTBL_GI_I_TypeProfile = lTBL_GI_I_TypeProfile;
	}

	public void setWHIT_GI_I_TypeProfile(
			List<StarToChemicalProfile> wHIT_GI_I_TypeProfile) {
		WHIT_GI_I_TypeProfile = wHIT_GI_I_TypeProfile;
	}

	public void setPYEL_GI_I_TypeProfile(
			List<StarToChemicalProfile> pYEL_GI_I_TypeProfile) {
		PYEL_GI_I_TypeProfile = pYEL_GI_I_TypeProfile;
	}

	public void setYELO_GI_I_TypeProfile(
			List<StarToChemicalProfile> yELO_GI_I_TypeProfile) {
		YELO_GI_I_TypeProfile = yELO_GI_I_TypeProfile;
	}

	public void setORNG_GI_I_TypeProfile(
			List<StarToChemicalProfile> oRNG_GI_I_TypeProfile) {
		ORNG_GI_I_TypeProfile = oRNG_GI_I_TypeProfile;
	}

	public void setRED__GI_I_TypeProfile(
			List<StarToChemicalProfile> rED__GI_I_TypeProfile) {
		RED__GI_I_TypeProfile = rED__GI_I_TypeProfile;
	}

	public void setBLUE_SUBGI_TypeProfile(
			List<StarToChemicalProfile> bLUE_SUBGI_TypeProfile) {
		BLUE_SUBGI_TypeProfile = bLUE_SUBGI_TypeProfile;
	}

	public void setLTBL_SUBGI_TypeProfile(
			List<StarToChemicalProfile> lTBL_SUBGI_TypeProfile) {
		LTBL_SUBGI_TypeProfile = lTBL_SUBGI_TypeProfile;
	}

	public void setWHIT_SUBGI_TypeProfile(
			List<StarToChemicalProfile> wHIT_SUBGI_TypeProfile) {
		WHIT_SUBGI_TypeProfile = wHIT_SUBGI_TypeProfile;
	}

	public void setPYEL_SUBGI_TypeProfile(
			List<StarToChemicalProfile> pYEL_SUBGI_TypeProfile) {
		PYEL_SUBGI_TypeProfile = pYEL_SUBGI_TypeProfile;
	}

	public void setYELO_SUBGI_TypeProfile(
			List<StarToChemicalProfile> yELO_SUBGI_TypeProfile) {
		YELO_SUBGI_TypeProfile = yELO_SUBGI_TypeProfile;
	}

	public void setORNG_SUBGI_TypeProfile(
			List<StarToChemicalProfile> oRNG_SUBGI_TypeProfile) {
		ORNG_SUBGI_TypeProfile = oRNG_SUBGI_TypeProfile;
	}

	public void setRED__SUBGI_TypeProfile(
			List<StarToChemicalProfile> rED__SUBGI_TypeProfile) {
		RED__SUBGI_TypeProfile = rED__SUBGI_TypeProfile;
	}

	public void setBLUE_MAINS_TypeProfile(
			List<StarToChemicalProfile> bLUE_MAINS_TypeProfile) {
		BLUE_MAINS_TypeProfile = bLUE_MAINS_TypeProfile;
	}

	public void setLTBL_MAINS_TypeProfile(
			List<StarToChemicalProfile> lTBL_MAINS_TypeProfile) {
		LTBL_MAINS_TypeProfile = lTBL_MAINS_TypeProfile;
	}

	public void setWHIT_MAINS_TypeProfile(
			List<StarToChemicalProfile> wHIT_MAINS_TypeProfile) {
		WHIT_MAINS_TypeProfile = wHIT_MAINS_TypeProfile;
	}

	public void setPYEL_MAINS_TypeProfile(
			List<StarToChemicalProfile> pYEL_MAINS_TypeProfile) {
		PYEL_MAINS_TypeProfile = pYEL_MAINS_TypeProfile;
	}

	public void setYELO_MAINS_TypeProfile(
			List<StarToChemicalProfile> yELO_MAINS_TypeProfile) {
		YELO_MAINS_TypeProfile = yELO_MAINS_TypeProfile;
	}

	public void setORNG_MAINS_TypeProfile(
			List<StarToChemicalProfile> oRNG_MAINS_TypeProfile) {
		ORNG_MAINS_TypeProfile = oRNG_MAINS_TypeProfile;
	}

	public void setRED__MAINS_TypeProfile(
			List<StarToChemicalProfile> rED__MAINS_TypeProfile) {
		RED__MAINS_TypeProfile = rED__MAINS_TypeProfile;
	}

	public void setBLUE_SUBDW_TypeProfile(
			List<StarToChemicalProfile> bLUE_SUBDW_TypeProfile) {
		BLUE_SUBDW_TypeProfile = bLUE_SUBDW_TypeProfile;
	}

	public void setLTBL_SUBDW_TypeProfile(
			List<StarToChemicalProfile> lTBL_SUBDW_TypeProfile) {
		LTBL_SUBDW_TypeProfile = lTBL_SUBDW_TypeProfile;
	}

	public void setWHIT_SUBDW_TypeProfile(
			List<StarToChemicalProfile> wHIT_SUBDW_TypeProfile) {
		WHIT_SUBDW_TypeProfile = wHIT_SUBDW_TypeProfile;
	}

	public void setPYEL_SUBDW_TypeProfile(
			List<StarToChemicalProfile> pYEL_SUBDW_TypeProfile) {
		PYEL_SUBDW_TypeProfile = pYEL_SUBDW_TypeProfile;
	}

	public void setYELO_SUBDW_TypeProfile(
			List<StarToChemicalProfile> yELO_SUBDW_TypeProfile) {
		YELO_SUBDW_TypeProfile = yELO_SUBDW_TypeProfile;
	}

	public void setORNG_SUBDW_TypeProfile(
			List<StarToChemicalProfile> oRNG_SUBDW_TypeProfile) {
		ORNG_SUBDW_TypeProfile = oRNG_SUBDW_TypeProfile;
	}

	public void setRED__SUBDW_TypeProfile(
			List<StarToChemicalProfile> rED__SUBDW_TypeProfile) {
		RED__SUBDW_TypeProfile = rED__SUBDW_TypeProfile;
	}

	public void setBLUE_DWARF_TypeProfile(
			List<StarToChemicalProfile> bLUE_DWARF_TypeProfile) {
		BLUE_DWARF_TypeProfile = bLUE_DWARF_TypeProfile;
	}

	public void setLTBL_DWARF_TypeProfile(
			List<StarToChemicalProfile> lTBL_DWARF_TypeProfile) {
		LTBL_DWARF_TypeProfile = lTBL_DWARF_TypeProfile;
	}

	public void setWHIT_DWARF_TypeProfile(
			List<StarToChemicalProfile> wHIT_DWARF_TypeProfile) {
		WHIT_DWARF_TypeProfile = wHIT_DWARF_TypeProfile;
	}

	public void setPYEL_DWARF_TypeProfile(
			List<StarToChemicalProfile> pYEL_DWARF_TypeProfile) {
		PYEL_DWARF_TypeProfile = pYEL_DWARF_TypeProfile;
	}

	public void setYELO_DWARF_TypeProfile(
			List<StarToChemicalProfile> yELO_DWARF_TypeProfile) {
		YELO_DWARF_TypeProfile = yELO_DWARF_TypeProfile;
	}

	public void setORNG_DWARF_TypeProfile(
			List<StarToChemicalProfile> oRNG_DWARF_TypeProfile) {
		ORNG_DWARF_TypeProfile = oRNG_DWARF_TypeProfile;
	}

	public void setRED__DWARF_TypeProfile(
			List<StarToChemicalProfile> rED__DWARF_TypeProfile) {
		RED__DWARF_TypeProfile = rED__DWARF_TypeProfile;
	}

	public void setPURPLE_RED_TypeProfile(
			List<StarToChemicalProfile> pURPLE_RED_TypeProfile) {
		PURPLE_RED_TypeProfile = pURPLE_RED_TypeProfile;
	}

	public void setBROWN_SUBS_TypeProfile(
			List<StarToChemicalProfile> bROWN_SUBS_TypeProfile) {
		BROWN_SUBS_TypeProfile = bROWN_SUBS_TypeProfile;
	}
}
