/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5517.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Joysticks (test)
	public static int leftJoystickPort = 0;
	public static int rightJoystickPort = 1;

	// Gamepads
	public static int mainDriverGamepadPort = 2;
	public static int operatorGamepadPort = 3;

	// Drive Motors
	public static int driveTrainLeftMotorPWM = 0;
	public static int driveTrainRightMotorPWM = 1;
	
	// Mechanism Motors
	public static int elevatorLeftMotorPWMPort = 2;
	public static int elevatorRightMotorPWMPort = 3;
	public static int intakeLeftMotorPWMPort = 4;
	public static int intakeRightMotorPWMPort = 5;
	


	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
