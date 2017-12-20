package model;


/**
 * A class that helps components performing a periodic action
 * @author Tartiflotte
 *
 */
public class Timer {
	
	long start;
	long end;
	
	
	

	/**
	 * Default constructor
	 */
	public Timer() {
		
	}
	
	
	/**
	 * Construct a timer and starts it
	 * @param scheduledTime
	 */
	public Timer(long scheduledTime) {
		start(scheduledTime);
	}
	
	
	/**
	 * Schedule a time we want to be warned at
	 * @param scheduledTime milliseconds before warning
	 */
	public void start(long scheduledTime){
		start = System.currentTimeMillis();
		end = start + scheduledTime;
	}
	
	
	/**
	 * Tell the user whether the fixed time is elapsed
	 * @return whether the time is elapsed
	 */
	public boolean elapsed(){
		long current = System.currentTimeMillis();
		return current >= end;
	}

}
