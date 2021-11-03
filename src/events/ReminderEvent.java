package events;

public class ReminderEvent {

	private String eventName;
	private String eventDescription;
	private int eventTime;

	public ReminderEvent() {
		eventName = null;
		eventDescription = null;
		eventTime = -1;
		
	}

	public ReminderEvent(String eventName, String eventDescription, int eventTime) {
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventTime = eventTime;
		
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
}