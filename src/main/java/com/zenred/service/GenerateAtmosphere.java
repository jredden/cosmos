package com.zenred.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zenred.cosmos.Atmosphere;
import com.zenred.cosmos.StarAtributesIF;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.event.DebugAgendaEventListener;
import org.drools.event.DebugWorkingMemoryEventListener;
import org.drools.rule.Package;

public class GenerateAtmosphere implements StarAtributesIF {

	Logger logger = LoggerFactory.getLogger(GenerateAtmosphere.class);
	private String ruleFile;
	private RuleBase ruleBase;
	private Reader source;
	private Double starLuminosity;
	private Double distancePrimaryInAUs;
	private Double planetRadius;
	private String colorType;

	private AtmosphereDTO atmosphereDTO;
	private String effects; // like frozen, internally emitted ...

	static {
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

		StarToChemicalProfile starToChemicalProfile = new StarToChemicalProfile();
		
		// BLUE_SG_II
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(66.0); // for this one
																	// only
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
		starToChemicalProfile.setUltraVioletReducingScale(66.0); // for this one
																	// only
		starToChemicalProfile.setWeightDuringAnalysis(3.0); // high rates more
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(47.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(66.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(1.0);
		LTBL_SG_II_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(LTBL_SG_II, BLUE_SG_II_TypeProfile);
		
		// WHIT_SG_II

		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(66.0); // for this one
																	// only
		starToChemicalProfile.setWeightDuringAnalysis(3.0); // high rates more
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(47.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(66.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(1.0);
		WHIT_SG_II_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(WHIT_SG_II, BLUE_SG_II_TypeProfile);

		// YELO_SG_II
		
		// uv and reducing: 01 is extreme UV and 99 is extreme reducing
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(66.0); // for this one
																	// only
		starToChemicalProfile.setWeightDuringAnalysis(3.0); // high rates more
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Carbon);
		starToChemicalProfile.setUltraVioletReducingScale(47.0);
		starToChemicalProfile.setWeightDuringAnalysis(13.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Helium);
		starToChemicalProfile.setUltraVioletReducingScale(4.0);
		starToChemicalProfile.setWeightDuringAnalysis(66.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Hydrogen);
		starToChemicalProfile.setUltraVioletReducingScale(1.0);
		starToChemicalProfile.setWeightDuringAnalysis(98.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Neon);
		starToChemicalProfile.setUltraVioletReducingScale(52.0);
		starToChemicalProfile.setWeightDuringAnalysis(2.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Nitrogen);
		starToChemicalProfile.setUltraVioletReducingScale(6.0);
		starToChemicalProfile.setWeightDuringAnalysis(86.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Oxygen);
		starToChemicalProfile.setUltraVioletReducingScale(20.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(1.0);
		YELO_SG_II_TypeProfile.add(starToChemicalProfile);
		
		atmosphereProfileMap.put(YELO_SG_II, BLUE_SG_II_TypeProfile);
		
		
		
		
		
		
		
		
		
		
		
		// BLUE_SG_I
		
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Argon);
		starToChemicalProfile.setUltraVioletReducingScale(68.0); // for this one
																	// only
		starToChemicalProfile.setWeightDuringAnalysis(4.0); // high rates more
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
		starToChemicalProfile.setUltraVioletReducingScale(3.0);
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
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Silicon);
		starToChemicalProfile.setUltraVioletReducingScale(34.0);
		starToChemicalProfile.setWeightDuringAnalysis(12.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		starToChemicalProfile = new StarToChemicalProfile();
		starToChemicalProfile.setAtmosphereParts(AtmosphereParts.Water);
		starToChemicalProfile.setUltraVioletReducingScale(33.0);
		starToChemicalProfile.setWeightDuringAnalysis(1.0);
		BLUE_SG_I_TypeProfile.add(starToChemicalProfile);

		atmosphereProfileMap.put(BLUE_SG_I, BLUE_SG_I_TypeProfile);
	}

	public AtmosphereDTO genAtmosphere(double star_luminosity,
			double distance_primary_au_s, double planet_radius,
			String star_color_type) {

		this.starLuminosity = star_luminosity;
		this.distancePrimaryInAUs = distance_primary_au_s;
		this.planetRadius = planet_radius;
		this.colorType = star_color_type;

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
		}

		atmosphereDTO = new AtmosphereDTO();
		return atmosphereDTO;
	}

	public void setRuleFile(String ruleFile) {
		this.ruleFile = ruleFile;
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

}
