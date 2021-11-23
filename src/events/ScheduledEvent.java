/*
* August Meyers
* ProcastinationApplication
* ScheduledEvent.java
* Scheduled Event Object File
*/

package events;

public class ScheduledEvent implements Event, Comparable<Event> {
	
	/*
	 * Implements Event interface that provides the mandatory Methods
	 * Implements Comparable to ensure that it is able to be compared 
	 */
	
	private String eventName;
	private String eventDescription;
	private long eventTime;
	// Private instance variables 

	private long eventOffHold;
	// UNIX Time when the event is scheduled for 
	
	public ScheduledEvent() {
		eventName = null;
		eventDescription = null;
		eventTime = System.currentTimeMillis();
		eventOffHold = -1;
		
	}
	// Default constructor that just sets values generically 

	public ScheduledEvent(String eventName, String eventDescription, long eventTime, long eventOffHold) {
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventTime = eventTime;
		this.eventOffHold = eventOffHold;
		
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
	
	public long getOffHold() {
		return eventOffHold;
		
	}
	// Get method for returning the time the event is scheduled for
	
	public String toString() {
		return "ScheduledEvent," + eventName + "," + eventDescription + "," + eventTime + "," + eventOffHold;
		
	}
	// toString method to return a formatted String of this Object
	// Particularly, we use this for storing the Object

	@Override
	public int compareTo(Event o) {
		
		/*
		 * If the object we are comparing to also is a ScheduledEvent, then we can compare the times
		 * If not, then it is just a normal event so this takes priority
		 */
		
		if (o instanceof ScheduledEvent) {
			return Double.valueOf(eventOffHold).compareTo((double) ((ScheduledEvent) o).getOffHold());
			
		} else {
			return 1;
			
		}
	}	
}