package events;

public class ScheduledEvent implements Event {
	
	private String eventName;
	private String eventDescription;
	private int eventTime;
	
	private int eventOffHold;
	// UNIX Time when the event is scheduled for 
	
	public ScheduledEvent() {
		eventName = null;
		eventDescription = null;
		eventTime = -1;
		eventOffHold = -1;
		
	}
	
	public ScheduledEvent(String eventName, String eventDescription, int eventTime, int eventOffHold) {
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

	public int getTime() {
		return eventTime;
	
	}

	public int getOffHold() {
		return eventOffHold;
		
	}
	
	public String toString() {
		return "ScheduledEvent," + eventName + "," + eventDescription + "," + eventTime + "," + eventOffHold;
		
	}
}