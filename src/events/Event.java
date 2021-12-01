/*
* August Meyers
* ProcastinationApplication
* Event.java
* Event Interface
*/

package events;

public interface Event extends Comparable<Event> {
	public String getName();
	
	public String getDescription();

	public long getTime();

}
