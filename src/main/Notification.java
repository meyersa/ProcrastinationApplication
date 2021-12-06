/*
 * Procrastination Application
 * August Meyers
 * Notification.java
 * Notification Utilities
 */

package main;

import java.awt.*;
import events.Event;
import events.ReminderEvent;
import events.ScheduledEvent;
import java.awt.TrayIcon.MessageType;
import java.util.HashMap;
// Easier with JavaAWT from the looks of it
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

	/**
	 * Notification Class
	 * Wraps Notification utilities 
	 */

public class Notification {
	private static Map<Long, Timer> Timers = new HashMap<Long, Timer>();
	// Local List for managing the Timers
	
	/**
	 * initNotification Method
	 * Initializes notifications
	 * Clears the old ones and starts the new ones with the input list 
	 * 
	 * @param Map<Long, Event> allEvents
	 * @return void
	 */
	
	public static void initNotification(Map<Long, Event> allEvents) {
		Log.writeLog("Notification.initNotification Method Called");
		
		clearTimers();
		buildTimers(allEvents);
		
	}
	
	/**
	 * buildTimers Method
	 * Starts the timers based off of the input from the allEvents list
	 * 
	 * @param Map<Long, Event> allEvents
	 * @return void
	 */
	
	private static void buildTimers(Map<Long, Event> allEvents) {
		Log.writeLog("Notification.buildTimers Method Called");
		
		for (Event current : allEvents.values()) {
			// Itterates through all of the events 
			
			if (current instanceof ScheduledEvent) {
				// If the event is scheduled 
				
				Timers.put(current.getTime(), new Timer());
				// Creates the entry 
				
				try {
					Timers.get(current.getTime()).schedule(new TimerTask() {
						// Schedules the created timer at the specified time
						
						@Override
						public void run() {
							sendNotification(current.getName(), current.getDescription());
							// Calls the notification method when the time happens 
							
						}
					}, ((ScheduledEvent) current).getOffHold() - current.getTime() - 900000);
					
				} catch (Exception e) {
					Log.writeLog("buildTimers: " + e.toString());
					Timers.remove(current.getTime());
					// If it can't make the event then delete it from the list 
					
				}
			}
		}
	}

	/**
	 * clearTimers Method
	 * Clears all of the timers
	 * 
	 * @return void
	 */
	
	private static void clearTimers() {
		Log.writeLog("Notification.clearTimers Method Called");
		
		for (Timer current : Timers.values()) {
			// Itterates through all of the timers
			
			current.cancel();
			// Uses the Timer cancel method to remove them 
			
		}
	}

	/**
	 * sendNotification Method
	 * Sends the notification with input
	 * 
	 * @param String notificationTitle The title of the event
	 * @param String notificationDescription the description of the event 
	 * @return void
	 */
	
	public static void sendNotification(String notificationTitle, String notificationDescription) {
		Log.writeLog("Notification.sendNotification Method Called");
		
		try{
		    SystemTray tray = SystemTray.getSystemTray();
		    // Gets the system tray in order to start building the icon 
		    
		    Image image = Toolkit.getDefaultToolkit().createImage("src/gui/assets/logo.png");
		    // Gets our icon to apply to the notification 
		    
		    TrayIcon trayIcon = new TrayIcon(image, "ProcrastinationApplication");
		    // Sets the icon to the notification, secondary Alt text is also added for accessibility 
		    
		    trayIcon.setImageAutoSize(true);
		    // Auto-size to make larger/smaller depending on the resolution of the end users computer 
		    
		    trayIcon.setToolTip("ProcrastinationApplication Notification");
		    // Sets the message for when you hover over the notification 
		    
		    tray.add(trayIcon);
		    // Adds the icon to the notification
		    
		    trayIcon.displayMessage(notificationTitle, notificationDescription, MessageType.INFO);
		    // And finally displays the notification 
		    
		}catch(Exception e){
			Log.writeLog(e.toString());
			// Catches any exceptions, such as unable to display notification 
			
		}
	}
}