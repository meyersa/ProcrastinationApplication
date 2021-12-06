// Class for sending system notifications 

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

public class Notification {
	private static Map<Long, Timer> Timers = new HashMap<Long, Timer>();
	
	public static void initNotification(Map<Long, Event> allEvents) {
		clearTimers();
		buildTimers(allEvents);
		
	}
	
	private static void buildTimers(Map<Long, Event> allEvents) {
		for (Event current : allEvents.values()) {
			if (current instanceof ScheduledEvent) {
				Timers.put(current.getTime(), new Timer());
				
				try {
					Timers.get(current.getTime()).schedule(new TimerTask() {

						@Override
						public void run() {
							sendNotification(current.getName(), current.getDescription());
							
						}
					}, ((ScheduledEvent) current).getOffHold() - current.getTime() - 900000);
					
				} catch (Exception e) {
					System.out.println("buildTimers: " + e.toString());
					Timers.remove(current.getTime());
					
				}
			}
		}
	}

	private static void clearTimers() {
		for (Timer current : Timers.values()) {
			current.cancel();
			
		}
	}

	public static void sendNotification(String notificationTitle, String notificationDescription) {
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
			System.out.println(e.toString());
			// Catches any exceptions, such as unable to display notification 
			
		}
	}
}
