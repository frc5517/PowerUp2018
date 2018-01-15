/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5517.robot;

import org.usfirst.frc.team5517.robot.utils.Gamepad;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	Gamepad mainDriverGamepad = new Gamepad(RobotMap.mainDriverGamepadPort);
	Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepadPort);
	Joystick leftJoystick = new Joystick(RobotMap.leftJoystickPort);
	Joystick rightJoystick = new Joystick(RobotMap.rightJoystickPort);
	// Button button = new JoystickButton(stick, buttonNumber);
	
	public double getLeftJoystickY() {
		double y = mainDriverGamepad.getLeftY();
		double sign = 1;
		
		if(y < 0) {
			sign = -1;
			y = -y;
		}
		return -(y * y * sign);
	}
	
	public double getRightJoystickX() {
		double y = mainDriverGamepad.getRightX();
		double sign = 1;
		
		if(y < 0) {
			sign = -1;
			y = -y;
		}
		return -(y * y * sign);
	}
}
