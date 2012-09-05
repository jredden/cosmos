package com.zenred.cosmos.dao.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractSqlFileDAO {

	private FileWriter sqlfile = null;
	private PrintWriter printwriter = null;

	public static String getSqlGo() {
		return "\n";
	}

	public void establishFileWriter(String filename) {
		if (sqlfile == null) {
			try {
				new File(filename).createNewFile();
				sqlfile = new FileWriter(filename);
				printwriter = new PrintWriter(sqlfile);
			} catch (IOException ioe) {
				System.err.println(ioe.toString());
			}
		}
	}

	public void stopPrintWriter() {
		printwriter.flush();
		printwriter.close();
		sqlfile = null;
	}
	
	public PrintWriter getFileWriter(){
		return printwriter;
	}
	
	public void printWrite(String sqlstatement){
        printwriter.print(sqlstatement + "\n");
        printwriter.print(getSqlGo() + "\n");

	}

}
