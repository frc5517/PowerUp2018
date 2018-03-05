package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This allows the robot to drive forward or backward
 * based on distance, d.
 * @param distance
 */

public class AutoDrive extends Command {

	private double distance;
	private boolean useSmartDashboard = false;
	private String smartDashboardKey = "Drive To Distance";

	/**
	 * Drive a distance (inches)
	 * @param d distance to drive in inches
	 * @param SD whether or not to use value from SmartDashboard
	 */
	public AutoDrive(double d, boolean SD) {
		requires(Robot.driveTrain);
		distance = d;
		useSmartDashboard = SD;
		if(!SmartDashboard.containsKey(smartDashboardKey) && useSmartDashboard) {
			SmartDashboard.putNumber(smartDashboardKey, 0);
		}
	}
	
	public AutoDrive(boolean SD) {
		this(0, SD);
	}
	
	public AutoDrive(double distance) {
		this(distance, false);
	}

	protected void initialize() {
		if(useSmartDashboard) {
			distance = SmartDashboard.getNumber(smartDashboardKey, distance);
		}

		Robot.driveTrain.setAngleSetpointToCurrent(); // keep robot going straight
		Robot.driveTrain.setDistanceSetpoint(distance); // drive to distance
	}

	protected void execute() {
		Robot.driveTrain.drivePidAngleAndDist();
	}

	protected boolean isFinished() {
		return Robot.driveTrain.hasReachedDistance();
	}

	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
