package com.zenred.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;

/* This class will filter and return files in the local directory
 * that have a ".txn" extenstion.  Subsequent calls to
 * extract returns individual files.
 */
public class FileExtractor
    implements ExtractorStrategy {

    private LinkedList myFileList= new LinkedList();
    private File myExtractDir = null;
    private String myExtractDirName = null;
    private boolean myFirstExtract = true;

    public void setSource(Object obj) {
		myExtractDirName = (String)obj;
	}

	// Return a file object of the appropriate type
    public Object extract() {

		if (myFirstExtract == true) {
			if (myExtractDirName == null) {
				System.out.println("Can't extract!  Source directory " +
				"path has not been specified!  Check config file.");
				return null;
			}

			myExtractDir = new File(myExtractDirName);

			if (myExtractDir.isDirectory() == false) {
				System.out.println("Can't extract!  Source directory " +
				"path specified (" + myExtractDir.getAbsolutePath() +
				") is not a directory!");
				return null;
			}
			else if (myExtractDir.exists() == false) {
				System.out.println("Can't extract!  Source directory " +
				"path specified (" + myExtractDir.getAbsolutePath() +
				") does not exist!");
				return null;
			}
		}

        File files[] = myExtractDir.listFiles();

		// Add files that need to be processed to the file set
        for (int i = 0; i < files.length; i++) {
            if (files[i].exists() && files[i].isFile()) {
				if (files[i].getName().endsWith(".txn")) {
                    if (myFileList.contains(files[i]) == false) {
                        myFileList.add(files[i]);
                    }
				}
			}
		}

		if (myFirstExtract == true && myFileList.size() == 0) {
			System.out.println("No \".txn\" files found in " +
			"directory " + myExtractDir.getAbsolutePath());
			return null;
		}

		Object returnObj = null;

        if (myFileList.size() > 0) {
			returnObj = myFileList.removeFirst();
		}

		myFirstExtract = false;

		return returnObj;

	}

	// Notify the extractor that processing has finished
	// on the specified Object
    public void finished(Object obj) {

        File processedFile = (File)obj;
        processedFile.delete();

    }

    public static void main(String[] args) {

		FileExtractor fe = new FileExtractor();
		fe.setSource(args[0]);
		Object obj = null;
		while ((obj = fe.extract()) != null) {
			System.out.println("Extracted : " + obj);
			fe.finished(obj);
		}
	}
}
