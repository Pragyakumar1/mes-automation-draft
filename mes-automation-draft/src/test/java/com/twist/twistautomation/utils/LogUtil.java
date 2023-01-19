package com.twist.twistautomation.utils;

import org.apache.commons.io.output.WriterOutputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

public class LogUtil {

    private static PrintStream printStream;
    private static FileWriter fileWriter; 


    static {
    	 try {
			fileWriter = new FileWriter("target/failsafe-reports/LogFile.txt");
			printStream = new PrintStream(new WriterOutputStream(fileWriter), true) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void logInfo(String message)  {
    	String date = new Date().toString();
    	try {
			fileWriter.write(date+" INFO :: "+ message);
			fileWriter.write(System.lineSeparator());
	    	fileWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static PrintStream getPrintStream() {
    	return printStream;
    }
    
    public static void logError(String className,String message) {
    	String date = new Date().toString();
    	try {
			fileWriter.write(System.lineSeparator());
			fileWriter.write(date+" ERROR :: "+ message);
			fileWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}
