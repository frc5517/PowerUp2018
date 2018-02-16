package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This allows the robot to drive forward or backward
 * based on distance, d.
 * @param distance
 */

public class AutoDrive extends Command {

	private double distance;
	
    public AutoDrive(int d) {
        requires(Robot.driveTrain);
        distance = d;
    }

    protected void initialize() {
    	Robot.driveTrain.setAngleToCurrent();
    	Robot.driveTrain.setDistanceSetpoint(distance);
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
