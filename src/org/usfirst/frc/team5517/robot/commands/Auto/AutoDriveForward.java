package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveForward extends Command {

	private double distance;
	
    public AutoDriveForward(int d) {
        requires(Robot.driveTrain);
        distance = d;
    }

    protected void initialize() {
    	Robot.driveTrain.setDistanceSetpoint(distance);
    	Robot.driveTrain.setAngleToCurrent();
    }

    protected void execute() {
    	Robot.driveTrain.drivePidAngleAndDist();
    }

    protected boolean isFinished() {
    	return Robot.driveTrain.hasReachedDistance();
    }

    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
