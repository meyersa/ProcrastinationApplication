/*
 * Procrastination Application
 * August Meyers
 * Log.java
 * Logging Class
 */

package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

	/**
	 * Log Class
	 * Wraps Logger
	 */

public class Log {
	private static File logLocation = new File(System.getenv("APPDATA") + "/ProcrastinationApplication/Log.txt");
	// Static file location 
	
	/**
	 * writeLog Method
	 * Takes static reference and writes the String to the log file 
	 * 
	 * @param String log
	 */
	
	public static void writeLog(String log) {
		checkFile();
		
		Writer File = null;
		
		try {
			File = new BufferedWriter(new FileWriter(logLocation, true));
			// Loads to file 
			
		} catch (IOException e) {
			// No point logging... There's no where for it to go!

		}
		
		try {
			File.append(System.currentTimeMillis() + " " + log + "\n");
			File.flush();
			// Writes to file 
			
		} catch (IOException e) {
			// No point logging... There's no where for it to go!

		}
	}
	
	/**
	 * checkFile Method
	 * In case the log file has not already been created, it creates the log file
	 * 
	 */
	
	private static void checkFile() {
		if (!logLocation.exists()) {
			try {
				logLocation.createNewFile();
				// Creates file
				
			} catch (IOException e) {
				// No point logging... There's no where for it to go!
				
			}
		}
	}	
}
