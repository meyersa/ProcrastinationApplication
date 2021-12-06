/*
 * Procrastination Application
 * August Meyers
 * LocalStorage.java
 * Handles all storage tasks
 */

package main;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

import events.*;

	/**
	 * LocalStorage Class
	 * Wraps LocalStorage methods
	 */

public class LocalStorage {

	/**
	 * checkForCache Method
	 * Checks for cache and returns if there is existing cache
	 * 
	 * @return boolean If there is cache or not
	 */
	
	public static boolean checkForCache() {
		Log.writeLog("LocalStorage.checkForCache Method Called");
		
		File cacheFolder = new File(System.getenv("APPDATA") + "/ProcrastinationApplication/Cache/");
		String[] cacheFiles = cacheFolder.list();
		boolean whatToReturn = false;
		// Creates our local variables 
		
		try {
			if (cacheFiles.length > 0) {
				whatToReturn = true;
				
			}
			// If there's any files in cache directory there is cache, so it returns true 
			
		} catch (NullPointerException e) {
			Log.writeLog("-- Cache File is empty -- ");
			whatToReturn = false;
			
		}
		// If there are no files, there is no cache, returns false 
		
		try {
			if (!cacheFolder.exists()) {
				Log.writeLog("-- Created Cache Folder --");
				cacheFolder.mkdirs();
				// If there is no local cache folder we need to make one 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return whatToReturn;
	}
	
	/**
	 * readCache Method
	 * Reads the cache from a file and returns findings
	 * 
	 * @return Map<Long, Event> Cache found
	 */
	
	public static Map<Long, Event> readCache() {
		Log.writeLog("LocalStorage.readCache Method Called");
		
		File cacheFile = null;
		Scanner sc = null;
		
		TreeSet<String> sortedList = new TreeSet<String>();
		Map<Long, Event> map = new HashMap<Long, Event>();
		String currentLine[] = null;
		// Declaring our variables 
		
		try {
			cacheFile = new File(System.getenv("APPDATA") + "/ProcrastinationApplication/Cache/");
			// Sets our cacheFile = to the entire Cache directory 
			
		} catch (Exception e) {
			Log.writeLog(e.toString());
			
		}
				
		for (String cacheFileName : cacheFile.list()) {		
			sortedList.add(cacheFileName.substring(0, cacheFileName.indexOf(".")));
			// Add the file names (Doubles) to the treeSet for sorting 
			
		}
		
		try {
			cacheFile = new File(System.getenv("APPDATA") + "/ProcrastinationApplication/Cache/" + sortedList.last() + ".txt");
			sc = new Scanner(cacheFile);
			// Sets the cacheFile to the most recent file by name
			
		} catch(Exception e) {
			Log.writeLog(e.toString());
			
		}
				
		while (sc.hasNextLine()) {
			currentLine = sc.nextLine().split(",");
					
			if (currentLine[0].equals("ReminderEvent")) {
				map.put(Long.parseLong(currentLine[3]), new ReminderEvent(currentLine[1], currentLine[2], Long.parseLong(currentLine[3])));
				
			} else if (currentLine[0].equals("ScheduledEvent")) {
				map.put(Long.parseLong(currentLine[3]), new ScheduledEvent(currentLine[1], currentLine[2], Long.parseLong(currentLine[3]), Long.parseLong(currentLine[4])));

			}
			
			currentLine = null;
			
		}
		// Makes the map entries 
		
		return map;
		
	}
	
	/**
	 * enforceCache Method
	 * Deletes excess cache
	 * 
	 * @return void
	 */
	
	public static void enforceCache() {
		Log.writeLog("LocalStorage.enforceCache Method Called");
		
		File cacheFile = null;
		TreeSet<String> fileList = new TreeSet<String>();

		try {
			cacheFile = new File(System.getenv("APPDATA") + "/ProcrastinationApplication/Cache/");
			
		} catch (Exception e) {
			Log.writeLog(e.toString());
			
		}
		
		for (String cacheFileName : cacheFile.list()) {		
			fileList.add(cacheFileName.substring(0, cacheFileName.indexOf(".")));
			
		}
		
		Log.writeLog("-- Before Deletion " + fileList.size() + " --");

		while (fileList.size() > 5) {
			cacheFile = new File(System.getenv("APPDATA") + "/ProcrastinationApplication/Cache/" + fileList.first() + ".txt");
			cacheFile.delete();
			fileList.remove(fileList.first());
			
		}

		Log.writeLog("-- After Deletion " + fileList.size() + " --");
		
	}
	
	/**
	 * writeCache Method
	 * Writes map to cache
	 * 
	 * @param Map<Long, T> map that should be written
	 * @return void
	 */
	
	public static <T> void writeCache(Map<Long, T> map) {		
		Log.writeLog("LocalStorage.writeCache Method Called");
		
		// Check for 5 existing cache, then delete older, sort by biggest long int 
		
		File cacheFile = null;
		FileWriter fw = null;
		// Declares our two variables 
		
		try {
			cacheFile = new File(System.getenv("APPDATA") + "/ProcrastinationApplication/Cache/" + System.currentTimeMillis() + ".txt");
			cacheFile.createNewFile();
			
			fw = new FileWriter(cacheFile);
			
		} catch (Exception e) {
			Log.writeLog(e.toString());
		
		}
		// Creates the cache file made with the specific MS 
		// Later when we look for the right cache file, we can just look at the time it was created by the biggest number
		
		for (Long eventID : map.keySet()) {
			try {
				fw.write(map.get(eventID).toString() + "\n");
				
			} catch (Exception e) {
				Log.writeLog(e.toString());

			}
		}
		// Writes all of the map entries to the file 
		
		try {
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
		// Writes to file 
		
	}
}