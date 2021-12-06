package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Log {
	private static File logLocation = new File(System.getenv("APPDATA") + "/ProcrastinationApplication/Log.txt");

	public static void writeLog(String log) {
		checkFile();
		
		Writer File = null;
		
		try {
			File = new BufferedWriter(new FileWriter(logLocation, true));
			
		} catch (IOException e) {
			
		}
		
		try {
			File.append(System.currentTimeMillis() + " " + log + "\n");
			File.flush();
			
		} catch (IOException e) {

		}
	}
	
	private static void checkFile() {
		if (!logLocation.exists()) {
			try {
				logLocation.createNewFile();
				
			} catch (IOException e) {
				
			}
		}
	}	
}
