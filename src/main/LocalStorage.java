package main;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

import events.*;

public class LocalStorage {

	public static boolean checkForCache() {
		File cacheFolder = new File("Cache/");
		String[] cacheFiles = cacheFolder.list();
		boolean whatToReturn = false;
		// Creates our local variables 
		
		try {
			if (cacheFiles.length > 0) {
				whatToReturn = true;
				
			}
			// If there's any files in cache directory there is cache, so it returns true 
			
		} catch (NullPointerException e) {
			whatToReturn = false;
			
		}
		// If there are no files, there is no cache, returns false 
		
		return whatToReturn;
	}
	
	public static Map<Integer, Event> readCache() {
		File cacheFile = null;
		Scanner sc = null;
		
		TreeSet<String> sortedList = new TreeSet<String>();
		Map<Integer, Event> map = new HashMap<Integer, Event>();
		String currentLine[] = null;
		// Declaring our variables 
		
		try {
			cacheFile = new File("Cache/");
			// Sets our cacheFile = to the entire Cache directory 
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
				
		for (String cacheFileName : cacheFile.list()) {		
			sortedList.add(cacheFileName.substring(0, cacheFileName.indexOf(".")));
			// Add the file names (Doubles) to the treeSet for sorting 
			
		}
		
		try {
			cacheFile = new File("Cache/" + sortedList.last() + ".txt");
			sc = new Scanner(cacheFile);
			// Sets the cacheFile to the most recent file by name
			
		} catch(Exception e) {
			System.out.println(e);
			
		}
				
		while (sc.hasNextLine()) {
			currentLine = sc.nextLine().split(",");
					
			if (currentLine[0].equals("ReminderEvent")) {
				map.put((int) Long.parseLong(currentLine[3]), new ReminderEvent(currentLine[1], currentLine[2], Long.parseLong(currentLine[3])));
				
			} else if (currentLine[0].equals("ScheduledEvent")) {
				map.put((int) Long.parseLong(currentLine[3]), new ScheduledEvent(currentLine[1], currentLine[2], Long.parseLong(currentLine[3]), Long.parseLong(currentLine[4])));

			}
			
			currentLine = null;
			
		}
		// Makes the map entries 
		
		return map;
		
	}
	
	public static <T> void writeCache(Map<Integer, T> map) {		
		// Check for 5 existing cache, then delete older, sort by biggest long int 
		
		File cacheFile = null;
		FileWriter fw = null;
		// Declares our two variables 
		
		try {
			cacheFile = new File("Cache/" + System.currentTimeMillis() + ".txt");
			cacheFile.createNewFile();
			
			fw = new FileWriter(cacheFile);
			
		} catch (Exception e) {
			System.out.println(e);
		
		}
		// Creates the cache file made with the specific MS 
		// Later when we look for the right cache file, we can just look at the time it was created by the biggest number
		
		for (int eventID : map.keySet()) {
			try {
				fw.write(map.get(eventID).toString() + "\n");
				
			} catch (Exception e) {
				System.out.println(e);

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