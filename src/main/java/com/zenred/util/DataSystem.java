package com.zenred.util;

import org.xml.sax.Attributes;

/**
 * This class represents a bank builder.  It builds
 * the extract, translate and load components as
 * defined in the configuration file.
 */
public class DataSystem implements Builder {

	private static final String PATH = "path";

    private String myName = null;
	private String myExtractorClassName = null;
	private String myExtractorPath = null;
	private String myTranslatorClassName = null;
	private String myLoaderClassName = null;
	private ExtractorStrategy myExtractor = null;
	private TranslatorStrategy myTranslator = null;
	private LoaderStrategy myLoader = null;

	public DataSystem(String name) {
		myName = name;
	}

	public String getName() { return myName; }

	/* Called by the xml parser to set a builder component */
    public void setComponent(Attributes attr,
      String tagName, String value) {

		if (value == null) {
		    System.out.println("ERROR: Missing value for tag "
		      + tagName + ".  Check xml config file.");
		    return;
		}

		if (tagName.equals("extractor")) {
			myExtractorPath = attr.getValue(PATH);
		    myExtractorClassName = value;
		}
		else if (tagName.equals("translator")) {
		    myTranslatorClassName = value;
		}
		else if (tagName.equals("loader")) {
		    myLoaderClassName = value;
        }

    }

	/* Called by the client to build this complex object */
    public boolean buildComponents() {

		boolean built = false;

		myExtractor =
			(ExtractorStrategy)ObjectFactory.newInstance(
				myExtractorClassName);

		myTranslator =
			(TranslatorStrategy)ObjectFactory.newInstance(
				myTranslatorClassName);
		myLoader =
			(LoaderStrategy)ObjectFactory.newInstance(
				myLoaderClassName);

		if (myExtractor != null && myTranslator != null &&
		    myLoader != null) {
				myExtractor.setSource(myExtractorPath);
				built = true;

		}

		return built;

    }

    public ExtractorStrategy getExtractor() {
		return myExtractor;
	}

	public TranslatorStrategy getTranslator() {
		return myTranslator;
	}

	public LoaderStrategy getLoader() {
		return myLoader;
	}
}
