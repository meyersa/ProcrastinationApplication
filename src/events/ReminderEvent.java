/*
* Procrastination Application
* August Meyers
* ReminderEvent.java
* ReminderEvent Object
*/

package events;

	/*
	 * ReminderEvent Class
	 * For making ReminderEvent object
	 * Implements both Event and Comparable
	 */

public class ReminderEvent implements Event, Comparable<Event> {
	
	private String eventName;
	private String eventDescription;
	private long eventTime;
	// Private instance variables 
	
	/*
	 * Constructor Scheduled Event
	 * Default constructor sets default options
	 */
	
	public ReminderEvent() {
		eventName = null;
		eventDescription = null;
		eventTime = System.currentTimeMillis();
		
	}

	/*
	 * Constructor Overloaded ReminderEvent
	 * Creates event with included options
	 * 
	 * @param eventName String name of the event
	 * @param eventDescription String description of event
	 * @param eventTime Long time of when event was created 
	 */
	
	public ReminderEvent(String eventName, String eventDescription, Long eventTime) {
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventTime = eventTime;
		
	}

	/*
	 * String Method getName
	 * 
	 * @return String name of event
	 */
	
	public String getName() {
		return eventName;

	}

	/*
	 * String Method getDescription
	 * 
	 * @return String description of event
	 */
	
	public String getDescription() {
		return eventDescription;

	}
	
	/*
	 * Long Method getTime
	 * 
	 * @return Long time of event creation
	 */
	
	public long getTime() {
		return eventTime;

	}
	
	/*
	 * String Method toString
	 * 
	 * @return Formatted output of all event information 
	 */
	
	public String toString() {
		return "ReminderEvent," + eventName + "," + eventDescription + "," + eventTime;
		
	}
	
	/*
	 * int Method compareTo
	 * Method that implements Comparable in order to make the event comparable
	 * 
	 * @param Event that is being compared to
	 * @return int positive or negative denouncing sorting position
	 */
	
	@Override
	public int compareTo(Event o) {

		/*
		 * If we are comparing to a ScheduledEvent, then we should return 0 since this is not as important
		 * But if it is another ReminderEvent, then we should compare the Titles alphabetically 
		 */

		if (o instanceof ScheduledEvent) {
			return -1;
			
		} else { 
			return o.getName().toLowerCase().compareTo(eventName.toLowerCase());
			
		}
	}
}