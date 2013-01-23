package com.zenred.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.zenred.cosmos.Atmosphere;
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

public class GenerateAtmosphere {

	Logger logger = LoggerFactory.getLogger(GenerateAtmosphere.class);
	private String ruleFile;
	private RuleBase ruleBase;
	private Reader source;

	public AtmosphereDTO genAtmosphere(double star_luminosity,
			double distance_primary_au_s, double planet_radius,
			String star_color_type) {

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

		AtmosphereDTO atmosphere = new AtmosphereDTO();
		return atmosphere;
	}

	public void setRuleFile(String ruleFile) {
		this.ruleFile = ruleFile;
	}
}
