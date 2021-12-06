/*
 * Procrastination Application
 * August Meyers
 * Main.java
 * Main class file, accesses everything else
 */

package main;

import gui.MainGUI;
import java.util.HashMap;
import java.util.Map;
import events.*;
	
	/**
	 * Main Class 
	 * Wraps the Main Class
	 */

public class Main {
	private Map<Long, Event> allEvents;
	private MainGUI MainGUI;
	// Private variables to be used and stored in Main object
	
	/**
	 * Main Method
	 * The Main method called on startup that starts the other classes
	 * Creates a main object for storing data and making non-static calls
	 * Clears extra cache
	 * Starts the application
	 * 
	 * @param args that are entered via cmd
	 */
	
	public static void main(String[] args) {
		Log.writeLog("Main.main Method Called");
		
		Main Application = new Main();
		
		LocalStorage.enforceCache();
		
		Application.show();
			
	}

	/**
	 * Main Constructor
	 * Default constructor creates object
	 * Builds cache by calling checkForCache method
	 */
	
	private Main() {
		if (LocalStorage.checkForCache()) {
			allEvents = LocalStorage.readCache();
			allEvents = Sort.sortMapByValue(allEvents);

		} else {
			allEvents = new HashMap<Long, Event>();
		
		}
	}

	/**
	 * show Method
	 * Shows the GUI
	 * 
	 * @return void
	 */
	
	private void show() {
		Log.writeLog("Main.show Method Called");
		
		/*
		 * Instantiates the MainGUI class
		 * Calls the MainGUI start event, which calls launch
		 * Also passes through the allEvents map in order for them to be displayed/tracked
		 */
		
		MainGUI = new MainGUI();
				
		try {
			MainGUI.start(allEvents);

		} catch (Exception e) {
			
			Log.writeLog(e.toString());
			
		}
	}
}