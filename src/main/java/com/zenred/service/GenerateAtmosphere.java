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
	
	private Double STRONG_ULTRA_VIOLET = 400.0;
	private Double ONE_RADIUS = 3959.0;

	private Logger logger = LoggerFactory.getLogger(GenerateAtmosphere.class);
	private String ruleFile = "/Atmosphere.drl";
	private String ruleFile2 = "/Atmosphere2.drl";
	
	private String BLUE_SG_II_drl ="/BLUE_SG_II.drl";
	private String LTBL_SG_II_drl ="/LTBL_SG_II.drl";
	private String WHIT_SG_II_drl ="/WHIT_SG_II.drl";
	private String PYEL_SG_II_drl ="/PYEL_SG_II.drl";
	private String YELO_SG_II_drl ="/YELO_SG_II.drl";
	private String ORNG_SG_II_drl ="/ORNG_SG_II.drl";
	private String RED__SG_II_drl ="/RED__SG_II.drl";
	private String BLUE_SG_I_drl ="/BLUE_SG_I.drl";
	private String LTBL_SG_I_drl ="/LTBL_SG_I.drl";
	private String WHIT_SG_I_drl ="/WHIT_SG_I.drl";
	private String PYEL_SG_I_drl ="/PYEL_SG_I.drl";
	private String YELO_SG_I_drl ="/YELO_SG_I.drl";
	private String ORNG_SG_I_drl ="/ORNG_SG_I.drl";
	private String RED__SG_I_drl ="/RED__SG_I.drl";
	private String BLUE_GI_II_drl ="/BLUE_GI_II.drl";
	private String LTBL_GI_II_drl ="/LTBL_GI_II.drl";
	private String WHIT_GI_II_drl ="/WHIT_GI_II.drl";
	private String PYEL_GI_II_drl ="/PYEL_GI_II.drl";
	private String YELO_GI_II_drl ="/YELO_GI_II.drl";
	private String ORNG_GI_II_drl ="/ORNG_GI_II.drl";
	private String RED__GI_II_drl ="/RED__GI_II.drl";
	private String BLUE_GI_I_drl ="/BLUE_GI_I.drl";
	private String LTBL_GI_I_drl ="/LTBL_GI_I.drl";
	private String WHIT_GI_I_drl ="/WHIT_GI_I.drl";
	private String PYEL_GI_I_drl ="/PYEL_GI_I.drl";
	private String YELO_GI_I_drl ="/YELO_GI_I.drl";
	private String ORNG_GI_I_drl ="/ORNG_GI_I.drl";
	private String RED__GI_I_drl ="/RED__GI_I.drl";
	private String BLUE_SUBGI_drl ="/BLUE_SUBGI.drl";
	private String LTBL_SUBGI_drl ="/LTBL_SUBGI.drl";
	private String WHIT_SUBGI_drl ="/WHIT_SUBGI.drl";
	private String PYEL_SUBGI_drl ="/PYEL_SUBGI.drl";
	private String YELO_SUBGI_drl ="/YELO_SUBGI.drl";
	private String ORNG_SUBGI_drl ="/ORNG_SUBGI.drl";
	private String RED__SUBGI_drl ="/RED__SUBGI.drl";
	private String BLUE_MAINS_drl ="/BLUE_MAINS.drl";
	private String LTBL_MAINS_drl ="/LTBL_MAINS.drl";
	private String WHIT_MAINS_drl ="/WHIT_MAINS.drl";
	private String PYEL_MAINS_drl ="/PYEL_MAINS.drl";
	private String YELO_MAINS_drl ="/YELO_MAINS.drl";
	private String ORNG_MAINS_drl ="/ORNG_MAINS.drl";
	private String RED__MAINS_drl ="/RED__MAINS.drl";
	private String BLUE_SUBDW_drl ="/BLUE_SUBDW.drl";
	private String LTBL_SUBDW_drl ="/LTBL_SUBDW.drl";
	private String WHIT_SUBDW_drl ="/WHIT_SUBDW.drl";
	private String PYEL_SUBDW_drl ="/PYEL_SUBDW.drl";
	private String YELO_SUBDW_drl ="/YELO_SUBDW.drl";
	private String ORNG_SUBDW_drl ="/ORNG_SUBDW.drl";
	private String RED__SUBDW_drl ="/RED__SUBDW.drl";
	private String BLUE_DWARF_drl ="/BLUE_DWARF.drl";
	private String LTBL_DWARF_drl ="/LTBL_DWARF.drl";
	private String WHIT_DWARF_drl ="/WHIT_DWARF.drl";
	private String PYEL_DWARF_drl ="/PYEL_DWARF.drl";
	private String YELO_DWARF_drl ="/YELO_DWARF.drl";
	private String ORNG_DWARF_drl ="/ORNG_DWARF.drl";
	private String RED__DWARF_drl ="/RED__DWARF.drl";
	private String PURPLE_RED_drl ="/PURPLE_RED.drl";
	private String BROWN_SUBS_drl ="/BROWN_SUBS.drl";
	private String DRKBRN_SDW_drl ="/DRKBRN_SDW.drl";

	
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

	Map<String, String> drlFileMap = new HashMap<String, String>();

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
	List<StarToChemicalProfile> DRKBRN_SDW_TypeProfile = new ArrayList<StarToChemicalProfile>();

	// uv and reducing: 01 is extreme UV and 99 is extreme reducing
	public GenerateAtmosphere() {
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

		starToChemicalProfile = new StarToChemicalProfile();
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
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(62.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0); // high rates more
															// probable
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
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high rates more
															// likely
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
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(66.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0); // high rates more
															// higher
															// probability
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
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(66.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0); // high means
															// increased
															// probability
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
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(35.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		RED__SG_II_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__SG_II, RED__SG_II_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		// BLUE_SG_I
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(68.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0); // high means
															// greater
															// probability
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
		
		starToChemicalProfile = new StarToChemicalProfile();
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
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0); // high rates more
															// probable
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
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more
															// likely
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
		PYEL_GI_I_TypeProfile.addAll(YELO_SG_I_TypeProfile);
		atmosphereProfileMap.put(YELO_SG_I, YELO_SG_I_TypeProfile);
		atmosphereProfileMap.put(PYEL_SG_I, PYEL_GI_I_TypeProfile);

		// ORNG_SG_I

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0); // high rates more
															// higher
															// probability
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
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high means
															// increased
															// probability
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
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(38.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0);
		RED__SG_I_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__SG_I, RED__SG_I_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		// BLUE_GI_II
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(70.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high means
															// greater
															// probability
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
		atmosphereProfileMap.put(BLUE_GI_I, BLUE_GI_II_TypeProfile);

		// LTBL_GI_II

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		starToChemicalProfile = new StarToChemicalProfile();
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
		LTBL_GI_I_TypeProfile.addAll(BLUE_SG_II_TypeProfile);
		atmosphereProfileMap.put(LTBL_GI_II, LTBL_GI_II_TypeProfile);
		atmosphereProfileMap.put(LTBL_GI_I, LTBL_GI_I_TypeProfile);

		// WHIT_GI_II

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(56.0);
		starToChemicalProfile.setWeightDuringAnalysis(7.0); // high rates more
															// probable
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
		WHIT_GI_I_TypeProfile.addAll(WHIT_GI_II_TypeProfile);
		atmosphereProfileMap.put(WHIT_GI_II, WHIT_GI_II_TypeProfile);
		atmosphereProfileMap.put(WHIT_GI_I, WHIT_GI_I_TypeProfile);

		// YELO_GI_II

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0); // high rates more
																// likely
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
		atmosphereProfileMap.put(YELO_GI_I, YELO_GI_II_TypeProfile);
		atmosphereProfileMap.put(PYEL_GI_I, YELO_SG_I_TypeProfile);

		// ORNG_GI_II

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(65.0);
		starToChemicalProfile.setWeightDuringAnalysis(6.0); // high rates more
															// higher
															// probability
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
		ORNG_GI_I_TypeProfile.addAll(ORNG_GI_II_TypeProfile);
		atmosphereProfileMap.put(ORNG_GI_II, ORNG_GI_II_TypeProfile);
		atmosphereProfileMap.put(ORNG_GI_I, ORNG_GI_I_TypeProfile);

		// RED__GI_II

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(62.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means
															// increased
															// probability
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
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__GI_II_TypeProfile.add(starToChemicalProfile);
		
		RED__GI_I_TypeProfile.addAll(RED__GI_II_TypeProfile);
		atmosphereProfileMap.put(RED__GI_II, RED__GI_II_TypeProfile);
		atmosphereProfileMap.put(RED__GI_I, RED__GI_I_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		// BLUE_SUBGI
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means
															// greater
															// probability
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

		starToChemicalProfile = new StarToChemicalProfile();
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
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more
															// probable
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
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high rates more
																// likely
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

		PYEL_SUBGI_TypeProfile.addAll(YELO_SUBGI_TypeProfile);
		atmosphereProfileMap.put(YELO_SUBGI, YELO_SUBGI_TypeProfile);
		atmosphereProfileMap.put(PYEL_SUBGI, PYEL_SUBGI_TypeProfile);

		// ORNG_SUBGI

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(70.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more
															// higher
															// probability
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

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(72.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0); // high means
																// increased
																// probability
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
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		RED__SUBGI_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__SUBGI, RED__SUBGI_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		// BLUE_MAINS

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(62.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means
															// greater
															// probability
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

		starToChemicalProfile = new StarToChemicalProfile();
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
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more
															// probable
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
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more
															// probable
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
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high rates more
																// likely
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
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more
															// higher
															// probability
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
		
		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(74.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high means
																// increased
																// probability
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(86.0);
		starToChemicalProfile.setWeightDuringAnalysis(85.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(84.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(16.0);
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
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
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
		starToChemicalProfile.setUltraVioletReducingScale(60.0);
		starToChemicalProfile.setWeightDuringAnalysis(80.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(45.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		RED__MAINS_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__MAINS, RED__MAINS_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		// BLUE_SUBDW

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(58.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means
															// greater
															// probability
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

		starToChemicalProfile = new StarToChemicalProfile();
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
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more
															// probable
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
		starToChemicalProfile.setWeightDuringAnalysis(9.0); // high rates more
															// probable
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
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high rates more
																// likely
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(25.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(46.0);
		starToChemicalProfile.setWeightDuringAnalysis(78.0);
		YELO_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(75.0);
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
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more
															// higher
															// probability
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(25.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(40.0);
		starToChemicalProfile.setWeightDuringAnalysis(85.0);
		ORNG_SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(68.0);
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

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(74.0);
		starToChemicalProfile.setWeightDuringAnalysis(14.0); // high means
																// increased
																// probability
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(84.0);
		starToChemicalProfile.setWeightDuringAnalysis(90.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(73.0);
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
		starToChemicalProfile.setUltraVioletReducingScale(53.0);
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
		starToChemicalProfile.setUltraVioletReducingScale(48.0);
		starToChemicalProfile.setWeightDuringAnalysis(84.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(18.0);
		RED__SUBDW_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__SUBDW, RED__SUBDW_TypeProfile);

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing

		// BLUE_DWARF

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(50.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high means
															// greater
															// probability
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

		starToChemicalProfile = new StarToChemicalProfile();
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
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more
															// probable
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
		starToChemicalProfile.setWeightDuringAnalysis(11.0); // high rates more
																// probable
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
		starToChemicalProfile.setWeightDuringAnalysis(12.0); // high rates more
																// likely
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(22.0);
		starToChemicalProfile.setWeightDuringAnalysis(15.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(84.0);
		starToChemicalProfile.setWeightDuringAnalysis(78.0);
		YELO_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(84.0);
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
		starToChemicalProfile.setWeightDuringAnalysis(8.0); // high rates more
															// higher
															// probability
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(30.0);
		starToChemicalProfile.setWeightDuringAnalysis(28.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(78.0);
		starToChemicalProfile.setWeightDuringAnalysis(89.0);
		ORNG_DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(76.0);
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

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(60.0);
		starToChemicalProfile.setWeightDuringAnalysis(14.0); // high means
																// increased
																// probability
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(93.0);
		starToChemicalProfile.setWeightDuringAnalysis(92.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(92.0);
		starToChemicalProfile.setWeightDuringAnalysis(99.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(46.0);
		starToChemicalProfile.setWeightDuringAnalysis(4.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(94.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(26.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(5.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
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
		starToChemicalProfile.setUltraVioletReducingScale(66.0);
		starToChemicalProfile.setWeightDuringAnalysis(88.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(10.0);
		starToChemicalProfile.setWeightDuringAnalysis(48.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(28.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		RED__DWARF_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(RED__DWARF, RED__DWARF_TypeProfile);

		// PURPLE RED dwarf

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(82.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(81.0);
		starToChemicalProfile.setWeightDuringAnalysis(50.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(44.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(72.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ammonia);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(32.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Chlorine);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.Methlacetylene);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ozone);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		PURPLE_RED_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(PURPLE_RED, PURPLE_RED_TypeProfile);

		// BROWN_SUBS dwarf

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Methane);
		starToChemicalProfile.setUltraVioletReducingScale(92.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(91.0);
		starToChemicalProfile.setWeightDuringAnalysis(50.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.CarbonDioxide);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(40.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(84.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(92.0);
		starToChemicalProfile.setWeightDuringAnalysis(30.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ammonia);
		starToChemicalProfile.setUltraVioletReducingScale(92.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ethane);
		starToChemicalProfile.setUltraVioletReducingScale(72.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Chlorine);
		starToChemicalProfile.setUltraVioletReducingScale(42.0);
		starToChemicalProfile.setWeightDuringAnalysis(20.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.Methlacetylene);
		starToChemicalProfile.setUltraVioletReducingScale(2.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Ozone);
		starToChemicalProfile.setUltraVioletReducingScale(32.0);
		starToChemicalProfile.setWeightDuringAnalysis(10.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sodium);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(5.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Sulfur);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(8.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Potassium);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile
				.setAtmosphereParts(AtmosphereParts.HydrogenSulfide);
		starToChemicalProfile.setUltraVioletReducingScale(12.0);
		starToChemicalProfile.setWeightDuringAnalysis(3.0);
		BROWN_SUBS_TypeProfile.add(starToChemicalProfile);

		DRKBRN_SDW_TypeProfile.addAll(BROWN_SUBS_TypeProfile);
		atmosphereProfileMap.put(BROWN_SUBS, BROWN_SUBS_TypeProfile);
		atmosphereProfileMap.put(DRKBRN_SDW, DRKBRN_SDW_TypeProfile);

		strict = false;
		flexible = false;
		goofy = false;
		
		drlFileMap.put(YELO_MAINS, YELO_MAINS_drl);
		drlFileMap.put(BLUE_SG_II, BLUE_SG_II_drl);
		drlFileMap.put(LTBL_SG_II, LTBL_SG_II_drl);
		drlFileMap.put(WHIT_SG_II, WHIT_SG_II_drl);
		drlFileMap.put(PYEL_SG_II, PYEL_SG_II_drl);
		drlFileMap.put(YELO_SG_II, YELO_SG_II_drl);
		drlFileMap.put(ORNG_SG_II, ORNG_SG_II_drl);
		drlFileMap.put(RED__SG_II, RED__SG_II_drl);
		drlFileMap.put(BLUE_SG_I, BLUE_SG_I_drl);
		drlFileMap.put(LTBL_SG_I, LTBL_SG_I_drl);
		drlFileMap.put(WHIT_SG_I, WHIT_SG_I_drl);
		drlFileMap.put(PYEL_SG_I, PYEL_SG_I_drl);
		drlFileMap.put(YELO_SG_I, YELO_SG_I_drl);
		drlFileMap.put(ORNG_SG_I, ORNG_SG_I_drl);
		drlFileMap.put(RED__SG_I, RED__SG_I_drl);
		drlFileMap.put(BLUE_GI_II, BLUE_GI_II_drl);
		drlFileMap.put(LTBL_GI_II, LTBL_GI_II_drl);
		drlFileMap.put(WHIT_GI_II, WHIT_GI_II_drl);
		drlFileMap.put(PYEL_GI_II, PYEL_GI_II_drl);
		drlFileMap.put(YELO_GI_II, YELO_GI_II_drl);
		drlFileMap.put(ORNG_GI_II, ORNG_GI_II_drl);
		drlFileMap.put(RED__GI_II, RED__GI_II_drl);
		drlFileMap.put(BLUE_GI_I, BLUE_GI_I_drl);
		drlFileMap.put(LTBL_GI_I, LTBL_GI_I_drl);
		drlFileMap.put(WHIT_GI_I, WHIT_GI_I_drl);
		drlFileMap.put(PYEL_GI_I, PYEL_GI_I_drl);
		drlFileMap.put(YELO_GI_I, YELO_GI_I_drl);
		drlFileMap.put(ORNG_GI_I, ORNG_GI_I_drl);
		drlFileMap.put(RED__GI_I, RED__GI_I_drl);
		drlFileMap.put(BLUE_SUBGI, BLUE_SUBGI_drl);
		drlFileMap.put(LTBL_SUBGI, LTBL_SUBGI_drl);
		drlFileMap.put(WHIT_SUBGI, WHIT_SUBGI_drl);
		drlFileMap.put(PYEL_SUBGI, PYEL_SUBGI_drl);
		drlFileMap.put(YELO_SUBGI, YELO_SUBGI_drl);
		drlFileMap.put(ORNG_SUBGI, ORNG_SUBGI_drl);
		drlFileMap.put(RED__SUBGI, RED__SUBGI_drl);
		drlFileMap.put(BLUE_MAINS, BLUE_MAINS_drl);
		drlFileMap.put(LTBL_MAINS, LTBL_MAINS_drl);
		drlFileMap.put(WHIT_MAINS, WHIT_MAINS_drl);
		drlFileMap.put(PYEL_MAINS, PYEL_MAINS_drl);
		drlFileMap.put(ORNG_MAINS, ORNG_MAINS_drl);
		drlFileMap.put(RED__MAINS, RED__MAINS_drl);
		drlFileMap.put(BLUE_SUBDW, BLUE_SUBDW_drl);
		drlFileMap.put(LTBL_SUBDW, LTBL_SUBDW_drl);
		drlFileMap.put(WHIT_SUBDW, WHIT_SUBDW_drl);
		drlFileMap.put(PYEL_SUBDW, PYEL_SUBDW_drl);
		drlFileMap.put(YELO_SUBDW, YELO_SUBDW_drl);
		drlFileMap.put(ORNG_SUBDW, ORNG_SUBDW_drl);
		drlFileMap.put(RED__SUBDW, RED__SUBDW_drl);
		drlFileMap.put(BLUE_DWARF, BLUE_DWARF_drl);
		drlFileMap.put(LTBL_DWARF, LTBL_DWARF_drl);
		drlFileMap.put(WHIT_DWARF, WHIT_DWARF_drl);
		drlFileMap.put(PYEL_DWARF, PYEL_DWARF_drl);
		drlFileMap.put(YELO_DWARF, YELO_DWARF_drl);
		drlFileMap.put(ORNG_DWARF, ORNG_DWARF_drl);
		drlFileMap.put(RED__DWARF, RED__DWARF_drl);
		drlFileMap.put(PURPLE_RED, PURPLE_RED_drl);
		drlFileMap.put(BROWN_SUBS, BROWN_SUBS_drl);
		drlFileMap.put(DRKBRN_SDW, DRKBRN_SDW_drl);


	}

	public AtmosphereDTO genAtmosphere(double star_luminosity,
			double distance_primary_au_s, double planet_radius,
			String star_color_type) {

		logger.info("start genAtmosphere");

		this.starLuminosity = star_luminosity;
		this.distancePrimaryInAUs = distance_primary_au_s;
		this.planetRadius = planet_radius;
		this.colorType = star_color_type;
		
		atmosphereDTO = new AtmosphereDTO();
		atmosphereDTO.setAtmosphereCompenent(new ArrayList<AtmosphereComponent>());

		nextRuleExecution(ruleFile);
		nextRuleExecution(ruleFile2);
		logger.info("star rule file: {} {}", colorType, drlFileMap.get(colorType));
		nextRuleExecution(drlFileMap.get(colorType));

		
		return atmosphereDTO;
	}
	/**
	 * 
	 * @param star_luminosity
	 * @param distance_primary_au_s
	 * @param planet_radius
	 * @param star_color_type
	 * @return normalized percentages
	 */
	public AtmosphereDTO genAtmosphereNormalized(double star_luminosity,
			double distance_primary_au_s, double planet_radius,
			String star_color_type) {
		AtmosphereDTO atmosphereDTO = genAtmosphere(star_luminosity,
				distance_primary_au_s, planet_radius, star_color_type);
		List<AtmosphereComponent> atmosphereListComponent = atmosphereDTO.getAtmosphereCompenent();
		Double runningDivisor = 0.0;
		for(AtmosphereComponent atmosphereComponent : atmosphereListComponent){
			runningDivisor += atmosphereComponent.getUn_normalized_percent();
		}
		for(AtmosphereComponent atmosphereComponent : atmosphereListComponent){
			atmosphereComponent.setNormalized_percent(atmosphereComponent.getUn_normalized_percent()/runningDivisor * 100.0);
		}
		
		if(atmosphereListComponent.size() == 0){
			AtmosphereComponent atmosphereComponent = new AtmosphereComponent();
			atmosphereComponent.setLiquid(Boolean.FALSE);
			atmosphereComponent.setSolid(Boolean.FALSE);
			atmosphereComponent.setNormalized_percent(100.0);
			atmosphereComponent.setUn_normalized_percent(100.0);
			atmosphereComponent.setSymbol("residue");
			atmosphereComponent.setVisulualized_symbol("residue");
			atmosphereDTO.getAtmosphereCompenent().add(atmosphereComponent);
		}
		
		return atmosphereDTO;
	}

	/**
	 * 
	 * @param star_luminosity
	 * @param distance_primary_au_s
	 * @return
	 */
	private static double temperature(double star_luminosity,
			double distance_primary_au_s) {
		 double a_temperature = 295.0 * (Math.pow(star_luminosity
				/ (distance_primary_au_s * distance_primary_au_s), .25));
		 return a_temperature;
	}

	/**
	 * common builder performs typical actions when generating a planetoids atmosphere
	 */
	private void commonBuilder() {
		double a_temperture = temperature(starLuminosity, distancePrimaryInAUs);
		logger.info("temperature: {} ", a_temperture);
		List<StarToChemicalProfile> starToChemicalProfileList = atmosphereProfileMap
				.get(colorType);
		for (StarToChemicalProfile starToChemicalProfile : starToChemicalProfileList) {
			Double gaseousState = starToChemicalProfile.getAtmosphereParts()
					.getGas();
			Double solidState = starToChemicalProfile.getAtmosphereParts()
					.getSolid();
			String symbol = starToChemicalProfile.getAtmosphereParts()
					.getSymbol();
			String viz_symbol = starToChemicalProfile.getAtmosphereParts()
					.getText() + "_" + symbol;
			logger.info("solidState :: gaseousState {} temperature {} ", solidState + "::" + gaseousState, a_temperture);
			Double running_scale = starToChemicalProfile
					.getUltraVioletReducingScale();
			double draw = drawRandom100();
			logger.info("draw {} scale {}", draw, running_scale);

			// in the hot range, reduce probability
			if (a_temperture > STRONG_ULTRA_VIOLET) {
				Double uv_scale = (100.0 - running_scale) / drawRandom100();
				Double newrv_value = running_scale - uv_scale;
				starToChemicalProfile.setUltraVioletReducingScale(newrv_value);
			}

			if (draw < running_scale) {
				Double un_normalized_percent = starToChemicalProfile
						.getWeightDuringAnalysis() * drawRandom100();
				Double density = (planetRadius * 0.95 + drawRandom10())
						/ ONE_RADIUS;
				atmosphereDTO.setDensity(density);
				AtmosphereComponent atmosphereComponent = new AtmosphereComponent();
				atmosphereComponent.setSymbol(symbol);
				atmosphereComponent.setVisulualized_symbol(viz_symbol);
				atmosphereComponent
						.setUn_normalized_percent(un_normalized_percent);
				if (a_temperture < solidState) {
					atmosphereComponent.setSolid(Boolean.TRUE);
				}
				if (a_temperture > solidState && a_temperture < gaseousState) {
					atmosphereComponent.setLiquid(Boolean.TRUE);
				}
				
				atmosphereDTO.getAtmosphereCompenent().add(atmosphereComponent);
				logger.info("adding {}", atmosphereComponent);
			}

		}
	}
	
	/**
	 * rule files are executed in sequence
	 * 
	 * @param ruleFile
	 */
	private void nextRuleExecution(String ruleFile) {
		logger.info("RULE FILE: {}", ruleFile);
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
				throw new AtmosphereGenException("Could not load rules file",
						e1);
			} catch (AtmosphereGenException e2) {
				e2.printStackTrace(); // bugger ...
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
		return this.colorType;
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

	public List<StarToChemicalProfile> getAtmosphereProfileMap(String starColor) {
		logger.info("color: {}", starColor);
		return atmosphereProfileMap.get(starColor);
	}

	public void strictDraw(List<StarToChemicalProfile> profileList) {
		logger.info("in strict {} ", profileList.getClass().getName());
		strict = true;
	}

	public void flexDraw(List<StarToChemicalProfile> profileList) {
		logger.info("in flex {} ", profileList.getClass().getName());
		flexible = true;
	}

	public void goofyDraw(List<StarToChemicalProfile> profileList) {
		logger.info("in goofy {} ", profileList.getClass().getName());
		goofy = true;
	}

	public Boolean isDefaultState() {
		logger.info("test default");
		if (!strict && !flexible && !goofy) {
			logger.info("default draw");
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public int drawRandom10() {
		int draw = DrawRolls.Instance().get_D10();
		logger.info("D10 draw: {}", draw);
		return draw;
	}

	public int drawRandom100() {
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

	public Boolean isStrictOnly() {
		return strict & !flexible & !goofy;
	}

	public Boolean isStrictAndFlexible() {
		return strict & flexible & !goofy;
	}

	public Boolean isStrictAndGoofy() {
		return strict & !flexible & goofy;
	}

	public Boolean isAllPossible() {
		return strict & flexible & goofy;
	}

	public Boolean isFlexibleAndGoofy() {
		return !strict & flexible & goofy;
	}

	public Boolean isFlexibleOnly() {
		return !strict & flexible & !goofy;
	}

	public Boolean isGoofyOnly() {
		return !strict & !flexible & goofy;
	}

	public void genStrictOnly() {
		commonBuilder();
		logger.info("gen strict for {} ", colorType);
	}

	public void genStrictAndFlexible() {
		commonBuilder();
		logger.info("gen strict and flexible for {} ", colorType);
	}

	public void genStrictAndGoofy() {
		commonBuilder();
		logger.info("gen strict and goofy for {} ", colorType);
	}

	public void genAllPossible() {
		commonBuilder();
		logger.info("gen all possible for {} ", colorType);
	}

	public void genFlexibleAndGoofy() {
		commonBuilder();
		logger.info("gen flexible and goofy for {} ", colorType);
	}

	public void genFlexibleOnly() {
		logger.info("gen flexible for {} ", colorType);
	}

	public void genGoofyOnly() {
		commonBuilder();
		logger.info("gen goofy for {} ", colorType);
	}

	public void setAtmosphereDTO(AtmosphereDTO atmosphereDTO) {
		this.atmosphereDTO = atmosphereDTO;
	}

	public void setAtmosphereProfileMap(
			Map<String, List<StarToChemicalProfile>> atmosphereProfileMap) {
		this.atmosphereProfileMap = atmosphereProfileMap;
	}

	public void setYELO_MAINS_drl(String YELO_MAINS_drl) {
		this.YELO_MAINS_drl = YELO_MAINS_drl;
		drlFileMap.put(YELO_MAINS, YELO_MAINS_drl);
	}

	public void setBLUE_SG_II_drl(String bLUE_SG_II_drl) {
		this.BLUE_SG_II_drl = bLUE_SG_II_drl;
		drlFileMap.put(BLUE_SG_II, BLUE_SG_II_drl);
	}

	public void setLTBL_SG_II_drl(String lTBL_SG_II_drl) {
		this.LTBL_SG_II_drl = lTBL_SG_II_drl;
		drlFileMap.put(LTBL_SG_II, LTBL_SG_II_drl);
	}

	public void setWHIT_SG_II_drl(String wHIT_SG_II_drl) {
		this.WHIT_SG_II_drl = wHIT_SG_II_drl;
		drlFileMap.put(WHIT_SG_II, WHIT_SG_II_drl);
	}

	public void setPYEL_SG_II_drl(String pYEL_SG_II_drl) {
		this.PYEL_SG_II_drl = pYEL_SG_II_drl;
		drlFileMap.put(PYEL_SG_II, PYEL_SG_II_drl);
	}

	public void setYELO_SG_II_drl(String yELO_SG_II_drl) {
		this.YELO_SG_II_drl = yELO_SG_II_drl;
		drlFileMap.put(YELO_SG_II, YELO_SG_II_drl);
	}

	public void setORNG_SG_II_drl(String oRNG_SG_II_drl) {
		this.ORNG_SG_II_drl = oRNG_SG_II_drl;
		drlFileMap.put(ORNG_SG_II, ORNG_SG_II_drl);
	}

	public void setRED__SG_II_drl(String rED__SG_II_drl) {
		this.RED__SG_II_drl = rED__SG_II_drl;
		drlFileMap.put(RED__SG_II, RED__SG_II_drl);
	}

	public void setBLUE_SG_I_drl(String bLUE_SG_I_drl) {
		this.BLUE_SG_I_drl = bLUE_SG_I_drl;
		drlFileMap.put(BLUE_SG_I, BLUE_SG_I_drl);
	}

	public void setLTBL_SG_I_drl(String lTBL_SG_I_drl) {
		this.LTBL_SG_I_drl = lTBL_SG_I_drl;
		drlFileMap.put(LTBL_SG_I, LTBL_SG_I_drl);
	}

	public void setWHIT_SG_I_drl(String wHIT_SG_I_drl) {
		this.WHIT_SG_I_drl = wHIT_SG_I_drl;
		drlFileMap.put(WHIT_SG_I, WHIT_SG_I_drl);
	}

	public void setPYEL_SG_I_drl(String pYEL_SG_I_drl) {
		this.PYEL_SG_I_drl = pYEL_SG_I_drl;
		drlFileMap.put(PYEL_SG_I, PYEL_SG_I_drl);
	}

	public void setYELO_SG_I_drl(String yELO_SG_I_drl) {
		this.YELO_SG_I_drl = yELO_SG_I_drl;
		drlFileMap.put(YELO_SG_I, YELO_SG_I_drl);
	}

	public void setORNG_SG_I_drl(String oRNG_SG_I_drl) {
		this.ORNG_SG_I_drl = oRNG_SG_I_drl;
		drlFileMap.put(ORNG_SG_I, ORNG_SG_I_drl);
	}

	public void setRED__SG_I_drl(String rED__SG_I_drl) {
		this.RED__SG_I_drl = rED__SG_I_drl;
		drlFileMap.put(RED__SG_I, RED__SG_I_drl);
	}

	public void setBLUE_GI_II_drl(String bLUE_GI_II_drl) {
		this.BLUE_GI_II_drl = bLUE_GI_II_drl;
		drlFileMap.put(BLUE_GI_II, BLUE_GI_II_drl);
	}

	public void setLTBL_GI_II_drl(String lTBL_GI_II_drl) {
		this.LTBL_GI_II_drl = lTBL_GI_II_drl;
		drlFileMap.put(LTBL_GI_II, LTBL_GI_II_drl);
	}

	public void setWHIT_GI_II_drl(String wHIT_GI_II_drl) {
		this.WHIT_GI_II_drl = wHIT_GI_II_drl;
		drlFileMap.put(WHIT_GI_II, WHIT_GI_II_drl);
	}

	public void setPYEL_GI_II_drl(String pYEL_GI_II_drl) {
		this.PYEL_GI_II_drl = pYEL_GI_II_drl;
		drlFileMap.put(PYEL_GI_II, PYEL_GI_II_drl);
	}

	public void setYELO_GI_II_drl(String yELO_GI_II_drl) {
		this.YELO_GI_II_drl = yELO_GI_II_drl;
		drlFileMap.put(YELO_GI_II, YELO_GI_II_drl);
	}

	public void setORNG_GI_II_drl(String oRNG_GI_II_drl) {
		this.ORNG_GI_II_drl = oRNG_GI_II_drl;
		drlFileMap.put(ORNG_GI_II, ORNG_GI_II_drl);
	}

	public void setRED__GI_II_drl(String rED__GI_II_drl) {
		this.RED__GI_II_drl = rED__GI_II_drl;
		drlFileMap.put(RED__GI_II, RED__GI_II_drl);
	}

	public void setBLUE_GI_I_drl(String bLUE_GI_I_drl) {
		this.BLUE_GI_I_drl = bLUE_GI_I_drl;
		drlFileMap.put(BLUE_GI_I, BLUE_GI_I_drl);
	}

	public void setLTBL_GI_I_drl(String lTBL_GI_I_drl) {
		this.LTBL_GI_I_drl = lTBL_GI_I_drl;
		drlFileMap.put(LTBL_GI_I, LTBL_GI_I_drl);
	}

	public void setWHIT_GI_I_drl(String wHIT_GI_I_drl) {
		this.WHIT_GI_I_drl = wHIT_GI_I_drl;
		drlFileMap.put(WHIT_GI_I, WHIT_GI_I_drl);
	}

	public void setPYEL_GI_I_drl(String pYEL_GI_I_drl) {
		this.PYEL_GI_I_drl = pYEL_GI_I_drl;
		drlFileMap.put(PYEL_GI_I, PYEL_GI_I_drl);
	}

	public void setYELO_GI_I_drl(String yELO_GI_I_drl) {
		this.YELO_GI_I_drl = yELO_GI_I_drl;
		drlFileMap.put(YELO_GI_I, YELO_GI_I_drl);
	}

	public void setORNG_GI_I_drl(String oRNG_GI_I_drl) {
		this.ORNG_GI_I_drl = oRNG_GI_I_drl;
		drlFileMap.put(ORNG_GI_I, ORNG_GI_I_drl);
	}

	public void setRED__GI_I_drl(String rED__GI_I_drl) {
		this.RED__GI_I_drl = rED__GI_I_drl;
		drlFileMap.put(RED__GI_I, RED__GI_I_drl);
	}

	public void setBLUE_SUBGI_drl(String bLUE_SUBGI_drl) {
		this.BLUE_SUBGI_drl = bLUE_SUBGI_drl;
		drlFileMap.put(BLUE_SUBGI, BLUE_SUBGI_drl);
	}

	public void setLTBL_SUBGI_drl(String lTBL_SUBGI_drl) {
		this.LTBL_SUBGI_drl = lTBL_SUBGI_drl;
		drlFileMap.put(LTBL_SUBGI, LTBL_SUBGI_drl);
	}

	public void setWHIT_SUBGI_drl(String wHIT_SUBGI_drl) {
		this.WHIT_SUBGI_drl = wHIT_SUBGI_drl;
		drlFileMap.put(WHIT_SUBGI, WHIT_SUBGI_drl);
	}

	public void setPYEL_SUBGI_drl(String pYEL_SUBGI_drl) {
		this.PYEL_SUBGI_drl = pYEL_SUBGI_drl;
		drlFileMap.put(PYEL_SUBGI, PYEL_SUBGI_drl);
	}

	public void setYELO_SUBGI_drl(String yELO_SUBGI_drl) {
		this.YELO_SUBGI_drl = yELO_SUBGI_drl;
		drlFileMap.put(YELO_SUBGI, YELO_SUBGI_drl);
	}

	public void setORNG_SUBGI_drl(String oRNG_SUBGI_drl) {
		this.ORNG_SUBGI_drl = oRNG_SUBGI_drl;
		drlFileMap.put(ORNG_SUBGI, ORNG_SUBGI_drl);
	}

	public void setRED__SUBGI_drl(String rED__SUBGI_drl) {
		this.RED__SUBGI_drl = rED__SUBGI_drl;
		drlFileMap.put(RED__SUBGI, RED__SUBGI_drl);
	}

	public void setBLUE_MAINS_drl(String bLUE_MAINS_drl) {
		this.BLUE_MAINS_drl = bLUE_MAINS_drl;
		drlFileMap.put(BLUE_MAINS, BLUE_MAINS_drl);
	}

	public void setLTBL_MAINS_drl(String lTBL_MAINS_drl) {
		this.LTBL_MAINS_drl = lTBL_MAINS_drl;
		drlFileMap.put(LTBL_MAINS, LTBL_MAINS_drl);
	}

	public void setWHIT_MAINS_drl(String wHIT_MAINS_drl) {
		this.WHIT_MAINS_drl = wHIT_MAINS_drl;
		drlFileMap.put(WHIT_MAINS, WHIT_MAINS_drl);
	}

	public void setPYEL_MAINS_drl(String pYEL_MAINS_drl) {
		this.PYEL_MAINS_drl = pYEL_MAINS_drl;
		drlFileMap.put(PYEL_MAINS, PYEL_MAINS_drl);
	}

	public void setORNG_MAINS_drl(String oRNG_MAINS_drl) {
		this.ORNG_MAINS_drl = oRNG_MAINS_drl;
		drlFileMap.put(ORNG_MAINS, ORNG_MAINS_drl);
	}

	public void setRED__MAINS_drl(String rED__MAINS_drl) {
		this.RED__MAINS_drl = rED__MAINS_drl;
		drlFileMap.put(RED__MAINS, RED__MAINS_drl);
	}

	public void setBLUE_SUBDW_drl(String bLUE_SUBDW_drl) {
		this.BLUE_SUBDW_drl = bLUE_SUBDW_drl;
		drlFileMap.put(BLUE_SUBDW, BLUE_SUBDW_drl);
	}

	public void setLTBL_SUBDW_drl(String lTBL_SUBDW_drl) {
		this.LTBL_SUBDW_drl = lTBL_SUBDW_drl;
		drlFileMap.put(LTBL_SUBDW, LTBL_SUBDW_drl);
	}

	public void setWHIT_SUBDW_drl(String wHIT_SUBDW_drl) {
		this.WHIT_SUBDW_drl = wHIT_SUBDW_drl;
		drlFileMap.put(WHIT_SUBDW, WHIT_SUBDW_drl);
	}

	public void setPYEL_SUBDW_drl(String pYEL_SUBDW_drl) {
		this.PYEL_SUBDW_drl = pYEL_SUBDW_drl;
		drlFileMap.put(PYEL_SUBDW, PYEL_SUBDW_drl);
	}

	public void setYELO_SUBDW_drl(String yELO_SUBDW_drl) {
		this.YELO_SUBDW_drl = yELO_SUBDW_drl;
		drlFileMap.put(YELO_SUBDW, YELO_SUBDW_drl);
	}

	public void setORNG_SUBDW_drl(String oRNG_SUBDW_drl) {
		this.ORNG_SUBDW_drl = oRNG_SUBDW_drl;
		drlFileMap.put(ORNG_SUBDW, ORNG_SUBDW_drl);
	}

	public void setRED__SUBDW_drl(String rED__SUBDW_drl) {
		this.RED__SUBDW_drl = rED__SUBDW_drl;
		drlFileMap.put(RED__SUBDW, RED__SUBDW_drl);
	}

	public void setBLUE_DWARF_drl(String bLUE_DWARF_drl) {
		this.BLUE_DWARF_drl = bLUE_DWARF_drl;
		drlFileMap.put(BLUE_DWARF, BLUE_DWARF_drl);
	}

	public void setLTBL_DWARF_drl(String lTBL_DWARF_drl) {
		this.LTBL_DWARF_drl = lTBL_DWARF_drl;
		drlFileMap.put(LTBL_DWARF, LTBL_DWARF_drl);
	}

	public void setWHIT_DWARF_drl(String wHIT_DWARF_drl) {
		this.WHIT_DWARF_drl = wHIT_DWARF_drl;
		drlFileMap.put(WHIT_DWARF, WHIT_DWARF_drl);
	}

	public void setPYEL_DWARF_drl(String pYEL_DWARF_drl) {
		this.PYEL_DWARF_drl = pYEL_DWARF_drl;
		drlFileMap.put(PYEL_DWARF, PYEL_DWARF_drl);
	}

	public void setYELO_DWARF_drl(String yELO_DWARF_drl) {
		this.YELO_DWARF_drl = yELO_DWARF_drl;
		drlFileMap.put(YELO_DWARF, YELO_DWARF_drl);
	}

	public void setORNG_DWARF_drl(String oRNG_DWARF_drl) {
		this.ORNG_DWARF_drl = oRNG_DWARF_drl;
		drlFileMap.put(ORNG_DWARF, ORNG_DWARF_drl);
	}

	public void setRED__DWARF_drl(String rED__DWARF_drl) {
		this.RED__DWARF_drl = rED__DWARF_drl;
		drlFileMap.put(RED__DWARF, RED__DWARF_drl);
	}

	public void setPURPLE_RED_drl(String pURPLE_RED_drl) {
		this.PURPLE_RED_drl = pURPLE_RED_drl;
		drlFileMap.put(PURPLE_RED, PURPLE_RED_drl);
	}

	public void setBROWN_SUBS_drl(String bROWN_SUBS_drl) {
		this.BROWN_SUBS_drl = bROWN_SUBS_drl;
		drlFileMap.put(BROWN_SUBS, BROWN_SUBS_drl);
	}
	public void setDRKBRN_SDW_drl(String DRKBRN_SDW_drl) {
		this.DRKBRN_SDW_drl = DRKBRN_SDW_drl;
		drlFileMap.put(DRKBRN_SDW, DRKBRN_SDW_drl);
	}

	public void setDrlFileMap(Map<String, String> drlFileMap) {
		this.drlFileMap = drlFileMap;
	}

	public void setStrongUV(Double strongUV){
		STRONG_ULTRA_VIOLET = strongUV;
	}

	
}
