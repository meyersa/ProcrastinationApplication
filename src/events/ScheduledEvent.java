/*
 * Procrastination Application
 * August Meyers
 * ScheduledEvent.java
 * ScheduledEvent Object
 */

package events;

	/**
	 * ScheduledEvent Class
	 * For making ScheduledEvent object
	 * Implements both Event and Comparable
	 */

public class ScheduledEvent implements Event, Comparable<Event> {
	
	private String eventName;
	private String eventDescription;
	private long eventTime;
	// Private instance variables 

	private long eventOffHold;
	// UNIX Time when the event is scheduled for 
	
	/**
	 * Constructor Scheduled Event
	 * Default constructor sets default options 
	 */
	
	public ScheduledEvent() {
		eventName = null;
		eventDescription = null;
		eventTime = System.currentTimeMillis();
		eventOffHold = -1;
		
	}

	/**
	 * Constructor Overloaded ScheduledEvent
	 * Creates event with included options 
	 * 
	 * @param eventName String name of the event
	 * @param eventDescription String description of event
	 * @param eventTime Long time of when event was created
	 * @param eventOffHold Long time of when the event is scheduled for 
	 */
	
	public ScheduledEvent(String eventName, String eventDescription, long eventTime, long eventOffHold) {
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventTime = eventTime;
		this.eventOffHold = eventOffHold;
		
	}	

	/**
	 * String Method getName
	 * 
	 * @return String name of event
	 */
	
	public String getName() {
		return eventName;
	
	}

	/**
	 * String Method getDescription
	 * 
	 * @return String description of event
	 */
	
	public String getDescription() {
		return eventDescription;
	
	}

	/**
	 * Long Method getTime
	 * 
	 * @return Long time of event creation
	 */
	
	public long getTime() {
		return eventTime;
	
	}

	/**
	 * Long Method getOffHold
	 * 
	 * @return Long time event is scheduled for 
	 */
	
	public long getOffHold() {
		return eventOffHold;
		
	}

	/**
	 * String Method toString
	 * 
	 * @return Formatted output of all event information 
	 */
	
	public String toString() {
		return "ScheduledEvent," + eventName + "," + eventDescription + "," + eventTime + "," + eventOffHold;
		
	}

	/**
	 * int Method compareTo
	 * Method that implements Comparable in order to make the event comparable
	 * 
	 * @param Event that is being compared to
	 * @return int positive or negative denouncing sorting position
	 */

	@Override
	public int compareTo(Event o) {
		/*
		 * If the object we are comparing to also is a ScheduledEvent, then we can compare the times
		 * If not, then it is just a normal event so this takes priority
		 */		
		
		if (o instanceof ReminderEvent) {
			return 2;
			
		} 
		else {
			return Long.valueOf(((ScheduledEvent) o).getOffHold()).compareTo(Long.valueOf(eventOffHold));
			
		}
	}	
}