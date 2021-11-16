package events;

public class ReminderEvent implements Event, Comparable<Event> {

	/*
	 * Implements Event interface that provides the mandatory Methods
	 * Implements Comparable to ensure that it is able to be compared 
	 */
	
	private String eventName;
	private String eventDescription;
	private long eventTime;
	// Private instance variables 
	
	public ReminderEvent() {
		eventName = null;
		eventDescription = null;
		eventTime = System.currentTimeMillis();
		
	}
	// Default constructor that just sets values generically 
	
	public ReminderEvent(String eventName, String eventDescription, Long eventTime) {
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventTime = eventTime;
		
	}
	// Overloaded constructor for initializing the event
	
	public String getName() {
		return eventName;

	}
	// Get method for returning the name of the event
	
	public String getDescription() {
		return eventDescription;

	}
	// Get method for returning the description of the event
	
	public long getTime() {
		return eventTime;

	}
	// Get method for returning the time the event was created
	
	public String toString() {
		return "ReminderEvent," + eventName + "," + eventDescription + "," + eventTime;
		
	}
	// toString method to return a formatted String of this Object
	// Particularly, we use this for storing the Object
	
	@Override
	public int compareTo(Event o) {
		
		/*
		 * If we are comparing to a ScheduledEvent, then we should return 0 since this is not as important
		 * But if it is another ReminderEvent, then we should compare the Titles alphabetically 
		 */

		if (!(o instanceof ReminderEvent)) {
			return 0;
			
		} else { 
			return eventName.compareTo(o.getName());
			
		}
	}
}