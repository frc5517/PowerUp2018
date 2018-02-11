/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5517.robot;

import org.usfirst.frc.team5517.robot.commands.IntakePinch;
import org.usfirst.frc.team5517.robot.commands.IntakeRelease;
import org.usfirst.frc.team5517.robot.commands.LowerElevator;
import org.usfirst.frc.team5517.robot.commands.LowerIntake;
import org.usfirst.frc.team5517.robot.commands.RaiseElevator;
import org.usfirst.frc.team5517.robot.commands.RaiseIntake;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeIn;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;
import org.usfirst.frc.team5517.robot.utils.Gamepad;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	Gamepad mainDriverGamepad = new Gamepad(RobotMap.mainDriverGamepadPort);
	Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepadPort);
	
	public OI() {
		bindControls();
	}
	
	
	private void bindControls() {
		operatorGamepad.getButtonA().whileHeld(new SpinIntakeIn());
		operatorGamepad.getButtonB().whileHeld(new SpinIntakeOut());
		operatorGamepad.getButtonX().whileHeld(new IntakePinch());
		operatorGamepad.getButtonY().whileHeld(new IntakeRelease());
		operatorGamepad.getRightShoulder().whenPressed(new RaiseIntake());
		operatorGamepad.getLeftShoulder().whenPressed(new LowerIntake());
		operatorGamepad.getRightTriggerClick().whileActive(new RaiseElevator());
		operatorGamepad.getLeftTriggerClick().whileHeld(new LowerElevator());
	}
	
	public Gamepad getMainController() {
		return mainDriverGamepad;
	}
	
	public Gamepad getOperatorController() {
		return operatorGamepad;
	}


	public double getMainDriverGamepadY() {
		double y = mainDriverGamepad.getRightY();
		return y;

		/*
		double sign = 1;
		if(y < 0) {
			sign = -1;
			y = -y;
		}
		return -(y * y * sign);*/
	}

	public double getMainDriverGamepadX() {
		double x = mainDriverGamepad.getLeftX();
		return x;

		/*
		double sign = 1;
		if(x < 0) {
			sign = -1;
			x = -x;
		}
		return -(x * x * sign);*/
	}
}