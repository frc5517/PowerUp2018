package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {

	private double distance;
	private double angle;
	
    public AutoDrive(int d, int a) {
        requires(Robot.driveTrain);
        distance = d;
        angle = a;
    }

    protected void initialize() {
    	Robot.driveTrain.setAngleToCurrent();
    	Robot.driveTrain.setDistanceSetpoint(distance);
    	Robot.driveTrain.setAngleSetpoint(angle);
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
    	end();
    }
}
