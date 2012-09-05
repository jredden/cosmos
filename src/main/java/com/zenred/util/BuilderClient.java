package com.zenred.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.util.List;
import java.util.ListIterator;
import java.util.Observer;
import java.util.ArrayList;

// import com.zenred.vehicletest.BuilderReader;

/** This class is an abstract "helper" class that is
 *  responsible for initializing the xml parser and
 *  managing Builder creation.
 */
public abstract class BuilderClient implements
	SAXBuilderEventObserver {

	private BuilderReaderIF myParser;

	private ArrayList myBuilders = new ArrayList();

    /**
       * likely to be delegated by child.
       */
    public void setMyParser(BuilderReaderIF myparser){
	System.out.println(myparser.getClass().getName());
	this.myParser = myparser;
    }

    /** Create the Builder implementation to be used based
     *  on the name provided.
     */
    public abstract Builder createBuilder(String tagName);

    /** Start this BuilderClient.  The implementation of
     *  this method should first call the initialize method
     *  below to parse the configuration file and instantiate
     *  the Builders (and all sub components).  Subsequently,
     *  these objects are available for use.
     */
    public abstract void start();

	/** Retrieve the List of Builder instances. */
    public List getBuilders() { return myBuilders; }

    /** Read the configuration file and create the Builder objects. */
    public void initialize(String fileName) {

        File configFile = new File(fileName);

        // Check for configuration file
        if (configFile.isFile() == false) {
            System.out.println("Please provide the configuration file " +
              "name as a command line argument.");
            return;
        }

        try {
            myParser.parse(fileName);
	    }
	    catch (IOException ioe) {
			System.out.println(ioe);
		}
	    catch (SecurityException se) {
			System.out.println(se);
	    }
	    catch (SAXException se) {
			System.out.println(se.getStackTrace());
		}

	    // Build all components for each builder - remove any
	    // Builders that fail
	    ListIterator iter = myBuilders.listIterator();
	    while (iter.hasNext()) {
			Builder b = (Builder)iter.next();
			if (b.buildComponents() == false) {
				iter.remove();
			}
		}
    }

    /** Notification when a new Builder must be created
     *  and registered.
     */
    public void registerNewBuilder(String name) {

		// Create builder instance
		Builder builder = createBuilder(name);

		// Add the current Builder to the list of Builders
        myBuilders.add(builder);

		// Set the Builder for the parser
		myParser.setBuilder(builder);

	}
}

