package org.usfirst.frc.team5517.robot.utils;

import edu.wpi.first.wpilibj.Timer;

/**
 * Compensates for "jumps" in analog signals sources.
 * 
 * Original source from:
 * https://github.com/Team254/FRC-2013/blob/master/src/com/team254/lib/util/Debouncer.java
 * 
 * Modified to add comments and to auto-reset if update hasn't been called for
 *   longer than the timer period.
 * 
 * @author tom@team254.com (Tom Bottiglieri)
 * @author James
 */
public class Debouncer {
	private Timer t = new Timer();
	private double time;
	private double lastUpdate = 0.0;
	private boolean first = true;

	/**
	 * The default constructor
	 * @param time positive duration (seconds) that the value needs to be
	 *   maintained before returning true.
	 */
	public Debouncer(double time) {
		if (time < 0.0) {
			time = 0.0;
		}
		
		this.time = time;
	}

	/**
	 * This method should be called periodically with the boolean value
	 *   under inspection. This method must be called periodically at a
	 *   frequency greater than the time specified in the constructor.  
	 * @param val the value to check. 
	 * @return true if the value has held true for the duration
	 *   specified in the constructor
	 */
	public boolean update(boolean val) {
		if (first) {
			first = false;
			t.start();
		}
		if (!val || (t.get() - lastUpdate) >= time) {
			t.reset();
		}
		
		lastUpdate = t.get();
		return t.get() > time;
	}

	/**
	 * Reset the timer.
	 */
	public void reset() {
		t.reset();
		lastUpdate = t.get();
	}
}