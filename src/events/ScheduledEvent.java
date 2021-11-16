package events;

public class ScheduledEvent implements Event, Comparable<Event> {
	
	private String eventName;
	private String eventDescription;
	private long eventTime;
	
	private long eventOffHold;
	// UNIX Time when the event is scheduled for 
	
	public ScheduledEvent() {
		eventName = null;
		eventDescription = null;
		eventTime = System.currentTimeMillis();
		eventOffHold = -1;
		
	}
	
	public ScheduledEvent(String eventName, String eventDescription, long eventTime, long eventOffHold) {
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventTime = eventTime;
		this.eventOffHold = eventOffHold;
		
	}	
	
	public String getName() {
		return eventName;
	
	}

	public String getDescription() {
		return eventDescription;
	
	}

	public long getTime() {
		return eventTime;
	
	}

	public long getOffHold() {
		return eventOffHold;
		
	}
	
	public String toString() {
		return "ScheduledEvent," + eventName + "," + eventDescription + "," + eventTime + "," + eventOffHold;
		
	}

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