package main;

import gui.MainGUI;

import java.util.HashMap;
import java.util.Map;

import events.*;

public class Main {

	public static void main(String[] args) {

		Map<Integer, Event> allEvents = null;
		
		if (LocalStorage.checkForCache()) {
			allEvents = LocalStorage.readCache();
			
		}
		
		allEvents = new HashMap<Integer, Event>();

		MainGUI MainGUI = new MainGUI();
		
		//try {
		//	MainGUI.start();
		//} catch (Exception e) {
		//	System.err.println("Fatal. Failed to start application.");
		//	e.printStackTrace();
		//	
		//}
		// Starts GUI
		
		// -----------------------------------------
		
		// Testing 
		
		// String eventName, String eventDescription, int eventTime
		
		//events.put(1635910704, new ReminderEvent("Event 3", "This is me making the first event", 1635910704));
		//events.put(1635910864, new ReminderEvent("Event 5", "This is me making the first event", 1635910864));
		//events.put(1635910870, new ScheduledEvent("Event 2", "This is me making the first event", 1635910870, 1636010870));

		//LocalStorage.writeCache(events);

		//System.out.println(LocalStorage.checkForCache());

		
	}
}