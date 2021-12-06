package main;

import gui.MainGUI;
import java.util.HashMap;
import java.util.Map;
import events.*;

public class Main {
	private Map<Long, Event> allEvents;
	private MainGUI MainGUI;

	public static void main(String[] args) {
		Main Application = new Main();
		
		LocalStorage.enforceCache();
		
		Application.show();
			
	}

	private Main() {
		if (LocalStorage.checkForCache()) {
			allEvents = LocalStorage.readCache();
			allEvents = Sort.sortMapByValue(allEvents);

		} else {
			allEvents = new HashMap<Long, Event>();
		
		}
	}

	public void syncEvents(Map<Long, Event> allEvents) {
		
		/*
		 * For inevitably when the Map gets out of sync, now it can be restored for storage
		 */
		
		this.allEvents = allEvents;
		
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
			e.printStackTrace();
			
		}
	}
}