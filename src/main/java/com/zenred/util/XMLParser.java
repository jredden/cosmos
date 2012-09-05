package com.zenred.util;

import org.xml.sax.helpers.XMLReaderAdapter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.io.CharArrayWriter;

/**
  * This class parses XML configuration files and
  * notifies observers (builders and the client of
  * the builders) of specific events.
  */
public class XMLParser extends XMLReaderAdapter {

	private static final String BUILDER = "builder";
	private static final String TRUE = "true";
	private SAXBuilderEventObserver myBuilderObserver = null;
	private Builder myBuilder = null;
	private Attributes myCurrentAttributes = null;
	private CharArrayWriter myCharArrayWriter =
	  new CharArrayWriter();

	/**
	  * Create a parser with a SAXBuilderEventObserver
	  * (builders' client) that will be notified each
	  * time a "builder='true'" attribute is encountered in
	  * a configuration file.
	  */
    public XMLParser(SAXBuilderEventObserver seo)
        throws SAXException {

		super();
		myBuilderObserver = seo;

    }

	/**
	 * This method is called by the SAXBuilderEventObserver
	 * to register a new Builder instance for event
	 * notification.
	 */
    public void setBuilder(Builder builder) {
		myBuilder = builder;
	}

	/**
	 *  This method is called on a SAX "startElement" event.
	 *  Check to see if a "builder='true'" attribute is set.
	 *  If so, notify the SAXBuilderEventObserver (SBEO) object.
	 *  In turn, the SBEO will create a new Builder instance
	 *  and register it via the "setBuilder(Builder b)" method.
	 */
    public void startElement(String namespaceURI,
      String localName, String qName,
      Attributes attr) {

        String isBuilder = attr.getValue(BUILDER);
        if (isBuilder != null &&
            isBuilder.equalsIgnoreCase(TRUE)) {

			myBuilderObserver.registerNewBuilder(qName);
		}

        myCurrentAttributes = attr;
        myCharArrayWriter.reset();

    }

	/**
	 *  This method is called on a SAX "characters" event.
	 */
    public void characters(char[] chars, int start,
      int length) {

        myCharArrayWriter.write(chars, start, length);

    }

	/**
	 *  This method is called on a SAX "endElement" event.
	 *  Notify the current registered builder object of
	 *  the attributes, tag and class name (value) read.
	 */
    public void endElement(String namespaceURI,
      String localName, String qName) {

         String value = myCharArrayWriter.toString();

         myBuilder.setComponent(myCurrentAttributes,
           qName, value);

     }
 }

