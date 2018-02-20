/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5517.robot;

import org.usfirst.frc.team5517.robot.commands.Auto.AutoDoNothing;
import org.usfirst.frc.team5517.robot.commands.Auto.AutoScaleLeft;
import org.usfirst.frc.team5517.robot.commands.Auto.AutoScaleMiddle;
import org.usfirst.frc.team5517.robot.commands.Auto.AutoScaleRight;
import org.usfirst.frc.team5517.robot.commands.Auto.AutoSwitchLeftStraight;
import org.usfirst.frc.team5517.robot.commands.Auto.AutoSwitchLeftWithTurn;
import org.usfirst.frc.team5517.robot.commands.Auto.AutoSwitchMiddle;
import org.usfirst.frc.team5517.robot.commands.Auto.AutoSwitchRightWithTurn;
import org.usfirst.frc.team5517.robot.commands.Auto.AutoTestGroup;
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

//TODO: comment out print statements
//TODO: change TimedRaise/Lower to encoder values

public class Robot extends TimedRobot {

	/**
	 * Set to true to enable debugging print statements
	 */
	private static boolean ENABLE_DEBUG_LOGGING = true;
	
	// Subsystems
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Elevator elevator = new Elevator();
	public static final Intake intake = new Intake();
	public static OI oi;
	
	//private static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static boolean matchStarted = false;
	private static String fmsGameData = "";
	
	Command autoCommand;
	SendableChooser<Command> autoChooser;
	
	public boolean isMatchStarted() {
		return matchStarted;
	}
	
	// Autonomous game data
	public static String getGameDataString() {
		return fmsGameData;
	} 
	
	public static char getSwitchSide() {
		if(fmsGameData.length() < 3)  {
			return ' ';
		}
		
		return fmsGameData.charAt(0);
	}
	
	public static char getScaleSide() {
		if(fmsGameData.length() < 3) {
			return ' ';
		}
		
		return fmsGameData.charAt(1);
	}
	
	public static char getOpponentSwitchSide() {
		if(fmsGameData.length() < 3)  {
			return ' ';
		}
		
		return fmsGameData.charAt(2);
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		System.out.println("Robot initializing...");

		// enable camera if set to true from SmartDashboard
		// or if value is not set at all
		if(SmartDashboard.getBoolean("Enable Camera", true)) {
			CameraServer camera = CameraServer.getInstance();
			camera.startAutomaticCapture(0);
		}

		// Create controls
		oi = new OI();

		driveTrain.calibrateGyro();

		// Add all auton modes
		autoChooser = new SendableChooser<>();
		autoChooser.addDefault("Do Nothing", new AutoDoNothing());
		autoChooser.addObject("Auto Testing", new AutoTestGroup());
		autoChooser.addObject("Switch Straight", new AutoSwitchLeftStraight());
		autoChooser.addObject("Switch Right", new AutoSwitchRightWithTurn());
		autoChooser.addObject("Switch Middle", new AutoSwitchMiddle());
		autoChooser.addObject("Switch Left", new AutoSwitchLeftWithTurn());
		autoChooser.addObject("Scale Right", new AutoScaleRight());
		autoChooser.addObject("Scale Middle", new AutoScaleMiddle());
		autoChooser.addObject("Scale Left", new AutoScaleLeft());
		SmartDashboard.putData("Auto mode", autoChooser);
	}

	@Override
	public void disabledInit() {
		System.out.println("Robot Disabled");
		matchStarted = false;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		driveTrain.autoReinitGyro();
	}

	/**
	 * Autonomous Initialization
	 */
	@Override
	public void autonomousInit() {
		System.out.println("Autonomous Init");
		matchStarted = true;
		
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

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void robotPeriodic() {
		// send sensor values from drive train to SmartDashboard
		driveTrain.sendDataToSmartDashboard();
		
		// Send Power Distribution Panel to the SmartDashboard
		//SmartDashboard.putData(pdp);
	}

	@Override
	public void testPeriodic() {}
	
	/**
	 * Logs a debug message to the robot log, if debug logging is enabled
	 * @param msg message to log
	 */
	public static void logDebug(String msg) {
		if(ENABLE_DEBUG_LOGGING) {
			System.out.println(msg);
		}
	}
	
	/**
	 * Logs a message to robot log
	 * @param msg message to log
	 */
	public static void log(String msg) {
		System.out.println(msg);
	}
}