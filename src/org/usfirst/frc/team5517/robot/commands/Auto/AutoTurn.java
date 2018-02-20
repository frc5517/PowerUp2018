package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This allows the robot to turn to an angle between
 * -180 and 180 during autonomous.
 * @param angle
 */

public class AutoTurn extends Command {

	private double angle;
	private boolean useSmartDashboard = false;
	private String smartDashboardKey = "Turn To Angle";

	/**
	 * Turns to specified angle 
	 * 0 degrees = the orientation robot was placed at start of match
	 * @param ang angle to turn to
	 * @param SD whether or not to use SmartDashboard's angle input
	 */
	public AutoTurn(int ang, boolean SD) {
		requires(Robot.driveTrain);
		angle = ang;
		useSmartDashboard = SD;
		SmartDashboard.putNumber(smartDashboardKey, 0);
	}
	public AutoTurn(int a) {
		this(a, false);	
	}
	public AutoTurn(boolean SD) {
		this(0, SD);
	}

	protected void initialize() {
		if(useSmartDashboard) {
			angle = SmartDashboard.getNumber(smartDashboardKey, angle);
		}
		Robot.driveTrain.setAngleSetpoint(angle);
	}

	protected void execute() {
		Robot.driveTrain.drivePidAngleAndDist();
	}

	protected boolean isFinished() {
		return Robot.driveTrain.hasReachedAngle();
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
