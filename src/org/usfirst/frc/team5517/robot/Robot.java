/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5517.robot;

import org.usfirst.frc.team5517.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5517.robot.subsystems.Elevator;
import org.usfirst.frc.team5517.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

	// Subsystems
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Elevator elevator = new Elevator();
	//public static final DriveTrain_PID driveTrainPID = new DriveTrain_PID();
	public static final Elevator arm = new Elevator();
	public static final Intake intake = new Intake();
	public static OI oi;
	
	private static boolean matchStarted = false;
	private static String fmsGameData = null;

	// Gyro variables
	/*private double curAngle;
	private double lastAngle;
	private boolean gyroCalibrating;
	private boolean lastGyroCalibrating;
	private int gyroReinits;
	private Debouncer gyroDriftDetector;*/

	Command autoCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();
	
	public boolean isMatchStarted() {
		return matchStarted;
	}
	
	// Autonomous game data
	public static String getGameDataString() {
		return fmsGameData;
	} 
	
	public static char getSwitchSide() {
		return fmsGameData.charAt(0);
	}
	
	public static char getScaleSide() {
		return fmsGameData.charAt(1);
	}
	
	public static char getOpponentSwitchSide() {
		return fmsGameData.charAt(2);
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		System.out.println("Robot initializing...");

		CameraServer server = CameraServer.getInstance();
		server.startAutomaticCapture();

		// Create controls
		oi = new OI();

		// Gyro stuff
		//gyroDriftDetector = new Debouncer(1.0);
		//driveTrain.calibrateGyro();

		// autoChooser.addDefault("Default Auto", new AutoCommand());
		// autoChooser.addObject("My Auto", new OtherAutoCommand());
		SmartDashboard.putData("Auto mode", autoChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		System.out.println("Disabled Init");
		matchStarted = false;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * Autonomous Initializtion
	 */
	@Override
	public void autonomousInit() {
		System.out.println("Autonomous Init");
		
		// get selected autonomous from dashboard
		autoCommand = autoChooser.getSelected();
		
		// get plate assignment from FMS
		fmsGameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("Received plate assignment from FMS: " + fmsGameData);

		// schedule the autonomous command
		if (autoCommand != null) {
			autoCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		System.out.println("Teleop Init");
		matchStarted = true;
		if (autoCommand != null) {
			autoCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}