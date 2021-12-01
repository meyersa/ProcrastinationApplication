// Class for sending system notifications 

package main;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
// Easier with JavaAWT from the looks of it

public class Notification {
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
