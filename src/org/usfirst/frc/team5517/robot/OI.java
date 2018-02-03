/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5517.robot;

import org.usfirst.frc.team5517.robot.commands.LowerElevator;
import org.usfirst.frc.team5517.robot.commands.LowerIntake;
import org.usfirst.frc.team5517.robot.commands.RaiseElevator;
import org.usfirst.frc.team5517.robot.commands.RaiseIntake;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeIn;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;
import org.usfirst.frc.team5517.robot.subsystems.Intake;
import org.usfirst.frc.team5517.robot.utils.DPadButton;
import org.usfirst.frc.team5517.robot.utils.Gamepad;
import org.usfirst.frc.team5517.robot.utils.JoystickAnalogButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	Gamepad mainDriverGamepad = new Gamepad(RobotMap.mainDriverGamepadPort);
	Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepadPort);
	JoystickAnalogButton operatorRightTrigger, operatorLeftTrigger;
	
	public OI() {
		Button operatorButtonA = new JoystickButton(mainDriverGamepad, 1);
		Button operatorButtonB = new JoystickButton(mainDriverGamepad, 2);
		Button operatorButtonX = new JoystickButton(mainDriverGamepad, 3);
		Button operatorButtonY = new JoystickButton(mainDriverGamepad, 4);
		Button operatorDPadUp = new DPadButton(operatorGamepad, DPadButton.NORTH);
		Button operatorDPadDown = new DPadButton(operatorGamepad, DPadButton.SOUTH);
		Trigger operatorRightTrigger = new JoystickAnalogButton(operatorGamepad, Gamepad.AXIS_RIGHT_TRIGGER, 0.5);
		Trigger operatorLeftTrigger = new JoystickAnalogButton(operatorGamepad, Gamepad.AXIS_LEFT_TRIGGER, 0.5);
		
		bindControls();
	}
	
	
	private void bindControls() {
		operatorGamepad.getButtonA().whileHeld(new SpinIntakeIn());
		operatorGamepad.getButtonB().whileHeld(new SpinIntakeOut());
		operatorGamepad.getButtonX().whileHeld(null); //TODO make pinch
		operatorGamepad.getButtonY().whileHeld(null); //TODO make release
		operatorGamepad.getRightShoulder().whenPressed(new RaiseIntake());
		operatorGamepad.getLeftShoulder().whenPressed(new LowerIntake());
		operatorGamepad.getRightTriggerClick().whileActive(new RaiseElevator());
		operatorGamepad.getLeftTriggerClick().whileHeld(new LowerElevator());
	}


	public double getMainDriverGamepadY() {
		double y = mainDriverGamepad.getY();
		double sign = 1;

		if(y < 0) {
			sign = -1;
			y = -y;
		}
		return -(y * y * sign);
	}

	public double getMainDriverGamepadX() {
		double y = mainDriverGamepad.getX();
		double sign = 1;

		if(y < 0) {
			sign = -1;
			y = -y;
		}
		return -(y * y * sign);
	}
}