package com.zenred.util;

import java.io.*;
import java.io.FileWriter;
import org.w3c.dom.Document;

/**
  * Load input data to a file
  */
public class FileLoader implements LoaderStrategy {

    private static final String INTERFACE_DATA = "interface.data";

	/**
	  * Load the data contained in the objectToLoad to
	  * a local file (transaction.data).
	  */
    public void load(DataObject objectToLoad) {

        try {
            FileWriter fw = new FileWriter(INTERFACE_DATA, true);

            // write string data
            String data = objectToLoad.getStringData();
            if (data != null) {
		write(data, fw);

		// write xml docs
		Object document_data  = objectToLoad.getDocumentData();
		if (document_data!= null) {
		    // reflect document_data
		}
		
		fw.close();
	    }
	}
	catch (IOException ioe) {
	    System.out.println(ioe);
	}
    }

    private void write(String data, FileWriter fw)
	throws IOException {
	fw.write(data);
	fw.write("\n");
    }
}
