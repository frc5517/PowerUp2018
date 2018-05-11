/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5517.robot;

import org.usfirst.frc.team5517.robot.commands.Climb;
import org.usfirst.frc.team5517.robot.commands.IntakePinch;
import org.usfirst.frc.team5517.robot.commands.IntakeRelease;
import org.usfirst.frc.team5517.robot.commands.LowerElevator;
import org.usfirst.frc.team5517.robot.commands.LowerIntake;
import org.usfirst.frc.team5517.robot.commands.RaiseElevator;
import org.usfirst.frc.team5517.robot.commands.RaiseIntake;
import org.usfirst.frc.team5517.robot.commands.SetElevatorHeight;
import org.usfirst.frc.team5517.robot.commands.SlowIntakeOut;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeIn;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;
import org.usfirst.frc.team5517.robot.utils.Gamepad;
import org.usfirst.frc.team5517.robot.utils.JoystickAnalogButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {

	private Gamepad mainDriverGamepad = new Gamepad(RobotMap.mainDriverGamepadPort);
	private Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepadPort);
	private Joystick steeringWheel = new Joystick(RobotMap.steeringWheelPort);
	private Joystick throttle = new Joystick(RobotMap.throttlePort);
	private JoystickAnalogButton operatorTriggerR, operatorTriggerL;
	
	private SendableChooser<DriveMode> driveChooser;
	private SendableChooser<ControlMode> controlChooser;
	public enum DriveMode {
		CURVATURE_DRIVE,
		ARCADE_DRIVE
	}
	public enum ControlMode {
		WHEEL_THROTTLE,
		GAMEPAD
	}
	
	public OI() {
		controlChooser = new SendableChooser<>();
		driveChooser = new SendableChooser<>();
		
		// Add all drive modes
		driveChooser.addDefault("Arcade Drive", DriveMode.ARCADE_DRIVE);
		driveChooser.addObject("Curvature Drive", DriveMode.CURVATURE_DRIVE);
		
		// Add all driver control modes
		controlChooser.addDefault("Steering Wheel + Throttle Stick", ControlMode.WHEEL_THROTTLE);
		controlChooser.addObject("Xbox Controller", ControlMode.GAMEPAD);
		
		// Send mode choosers to the dashboard
		//SmartDashboard.putData("Drive Mode", driveChooser);
		SmartDashboard.putData("Control Mode", controlChooser);
		
		operatorTriggerL = new JoystickAnalogButton(operatorGamepad, Gamepad.AXIS_LEFT_TRIGGER, 0.5);
		operatorTriggerR = new JoystickAnalogButton(operatorGamepad, Gamepad.AXIS_RIGHT_TRIGGER, 0.5);
		
		bindControls();
	}
	
	private void bindControls() {
		operatorGamepad.getButtonA().whileHeld(new SpinIntakeIn());
		operatorGamepad.getButtonB().whileHeld(new SlowIntakeOut());
		operatorGamepad.getButtonX().whenPressed(new IntakePinch());
		operatorGamepad.getButtonY().whenPressed(new IntakeRelease());
		operatorGamepad.getRightShoulder().whenPressed(new RaiseIntake());
		operatorGamepad.getLeftShoulder().whenPressed(new LowerIntake());
		operatorGamepad.getStartButton().whileHeld(new Climb());
		operatorGamepad.getBackButton().whileHeld(new SpinIntakeOut());
		operatorTriggerR.whileActive(new RaiseElevator());
		operatorTriggerL.whileActive(new LowerElevator());
		mainDriverGamepad.getButtonA().whenPressed(new SetElevatorHeight(610.08));
		mainDriverGamepad.getButtonB().whenPressed(new SetElevatorHeight(9.92));
		mainDriverGamepad.getButtonX().whenPressed(new SetElevatorHeight(357.12));
		mainDriverGamepad.getButtonY().whenPressed(new SetElevatorHeight(446.4));
	}
	
	public Gamepad getMainController() {
		return mainDriverGamepad;
	}
	
	public Gamepad getOperatorController() {
		return operatorGamepad;
	}
	
	public Joystick getSteeringWheel() {
		return steeringWheel;
	}
	
	public Joystick getThrottle() {
		return throttle;
	}
	
	public double getMainDriverGamepadY() {
		double y = mainDriverGamepad.getLeftY();
		double sign = 1;
		if(y < 0) {
			sign = -1;
			y = -y;
		}
		return -(y * y * sign);
	}
	
	public double getMainDriverGamepadX() {
		double x = mainDriverGamepad.getRightX();
		double sign = 1;
		if(x < 0) {
			sign = -1;
			x = -x;
		}
		return -(x * x * sign);
	}
	
	public double getSteeringWheelX() {
		double x = steeringWheel.getX();
		return x;
	}
	
	public double getThrottleY() {
		double y = throttle.getY();
		return y;
	}
	
	public double getOperatorLeftY() {
		double y = operatorGamepad.getLeftY();
		return y;
	}

	/**
	 * Gets the x value from either main driver game pad, or steering wheel
	 * (based on chosen control mode)
	 * @return double
	 */
	public double getDriveX() {
		switch(controlChooser.getSelected()) {
			case WHEEL_THROTTLE:
				return getMainDriverGamepadX();
		
			case GAMEPAD:
			default:
				return getSteeringWheelX();
		}
	}

	/**
	 * Gets the y value from either main driver game pad, or throttle stick
	 * (based on chosen control mode)
	 * @return double
	 */
	public double getDriveY() {
		switch(controlChooser.getSelected()) {
			case WHEEL_THROTTLE:
				return getMainDriverGamepadY();
		
			case GAMEPAD:
			default:
				return getThrottleY();
		}
	}
}