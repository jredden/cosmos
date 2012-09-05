package com.zenred.util;

import java.io.ByteArrayOutputStream;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/**
  * This class contains utility methods for creating
  * xml documents
  */
public final class XMLDocumentUtils {


    private static DocumentBuilderFactory ourDocFactory = null;
    private static DocumentBuilder ourDocBuilder = null;
    private static Transformer ourTransformer = null;

	/** Create document factory and transformer. */
    static {

        try {
		    ourDocFactory = DocumentBuilderFactory.newInstance();
		}
		catch (FactoryConfigurationError fce) {
			System.out.println(fce);
		}

		try {
			ourDocBuilder = ourDocFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException pce) {
			System.out.println(pce);
		}

		try {
			ourTransformer =
			  TransformerFactory.newInstance().newTransformer();
			ourTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
		}
		catch (TransformerConfigurationException tce) {
			System.out.println(tce);
		}
	}

	/** Create an xml document */
	public static Document createDocument() {
		Document doc = ourDocBuilder.newDocument();
		return doc;
	}

	/** Convert an xml Document object to a String */
	public static String convertDocumentToString(Document doc) {

		StreamResult sr = new StreamResult(new ByteArrayOutputStream());
		try {
		    ourTransformer.transform(new DOMSource(doc), sr);
		}
		catch (TransformerException te) {
			System.out.println(te);
		}

		String docAsString = sr.getOutputStream().toString();
		return docAsString;
	}

	/** Add an Element to a Document */
	public static Element addChildElement(Document doc, Node parent,
	    String tag) {

	    Element e = doc.createElement(tag);
	    doc.importNode(e, true);
	    parent.appendChild(e);
	    return e;
	}

	/** Add a Text element to a Document */
	public static Text addChildTextElement(Document doc, Node parent,
	    String tag, String value) {

	    Element e = doc.createElement(tag);
	    Text t = doc.createTextNode(value);
	    e.appendChild(t);
	    parent.appendChild(e);
	    return t;
    }

	public static void main(String[] args) {

		Document testDoc = createDocument();
		Element root = addChildElement(testDoc, testDoc, "root");
		addChildTextElement(testDoc, root, "child", "10");
		System.out.println(convertDocumentToString(testDoc));
	}
}