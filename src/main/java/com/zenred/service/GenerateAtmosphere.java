package com.zenred.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

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

public class GenerateAtmosphere implements StarAtributesIF{


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
