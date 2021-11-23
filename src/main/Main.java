package main;

import gui.ErrorGUI;
import gui.MainGUI;
import java.util.HashMap;
import java.util.Map;
import events.*;

public class Main {
	private Map<Integer, Event> allEvents;
	private MainGUI MainGUI;

	public static void main(String[] args) {
		Main Application = new Main();
		
		Application.show();
		
	}

	private Main() {
		if (LocalStorage.checkForCache()) {
			allEvents = LocalStorage.readCache();

		} else {
			allEvents = new HashMap<Integer, Event>();
		
		}
	}

	public void syncEvents(Map<Integer, Event> allEvents) {
		
		/*
		 * For inevitably when the Map gets out of sync, now it can be restored for storage
		 */
		
		this.allEvents = allEvents;
		
	}
	
	public void exit() {
		
		// Pass back through allEvents?? 
		
		/*
		 * On exit, writes allEvents to cache
		 * Closes GUI
		 */
		
		LocalStorage.writeCache(allEvents);

		MainGUI.end();
		
	}

	private void show() {
		
		/*
		 * Instantiates the MainGUI class
		 * Calls the MainGUI start event, which calls launch
		 * Also passes through the allEvents map in order for them to be displayed/tracked
		 */
		
		MainGUI = new MainGUI();
				
		try {
			MainGUI.start(allEvents);

		} catch (Exception e) {
			Error.createErrorGUI(e);
			
		}		
	}
}