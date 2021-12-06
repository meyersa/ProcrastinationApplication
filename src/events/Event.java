/*
 * Procrastination Application
 * August Meyers
 * Event.java
 * Event Interface
 */

package events;

	/*
	 * Event Interface
	 * Standardizes the different Events
	 * Requires a getName, getDescription, and getTime method
	 * Also requires the Comparable interface 
	 */

public interface Event extends Comparable<Event> {


	/*
	 * String Method getName
	 * 
	 * @return the String name of the event
	 */
	
	public String getName();
	
	/*
	 * String Method getDescription
	 * 
	 * @return the String definition of the event
	 */

	public String getDescription();

	/*
	 * long Methode getTime
	 * 
	 * @return the Long time of the event (UNIX Time)
	 */

	public long getTime();

}
