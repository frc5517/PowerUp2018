package org.usfirst.frc.team5517.robot.utils;

import edu.wpi.first.wpilibj.buttons.Button;

public class DPadButton extends Button {

	public static final int NORTH = 0,
				 	  		NORTHEAST = 1,
				 	  		EAST = 2,
				 	  		SOUTHEAST = 3,
				 	  		SOUTH = 4,
				 	  		SOUTHWEST = 5,
				 	  		WEST = 6, 
				 	  		NORTHWEST = 7,
				 	  		AXIS = Gamepad.AXIS_DPAD;
	
	private int direction;
	
	Gamepad joystick;
	
	public DPadButton(Gamepad js, int dir) {
		joystick = js;
		direction = dir;
	}
	
	@Override
	public boolean get() {
		int dirPressed = (int)joystick.getRawAxis(AXIS);
		return (dirPressed == direction);
		
	}

}
