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

	// Gamepads
	public static int mainDriverGamepadPort = 0;
	public static int operatorGamepadPort = 1;
	public static int steeringWheelPort = 2;
	public static int throttlePort = 3;

	// Drive Motors
	public static int driveTrainRightMotorPWM = 0;
	public static int driveTrainLeftMotorPWM = 1;
	
	// Mechanism Motors
	public static int elevatorLeftMotorPWMPort = 2;
	public static int elevatorRightMotorPWMPort = 3;
	public static int intakeLeftMotorPWMPort = 4;
	public static int intakeRightMotorPWMPort = 5;
	
	// Solenoid Ports
	public static int intakeSolenoidPinch   = 1; // color: blue
	public static int intakeSolenoidRelease = 6; // color: green-yellow
	public static int intakeSolenoidLift    = 7; // color: green-black
	public static int intakeSolenoidLower   = 0; // color: orange
	
	// Encoder Digital Ports
	public static int driveEncoderA    = 0, // drive: blue
					  driveEncoderB    = 1,
					  elevatorEncoderA = 2, // elevator: orange
					  elevatorEncoderB = 3;
	
}
