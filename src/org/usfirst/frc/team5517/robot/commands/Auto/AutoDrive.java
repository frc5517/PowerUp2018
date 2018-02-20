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

    
    public AutoDrive(boolean SD) {
    	this(0, SD);
    }
    public AutoDrive(double d) {
    	this(d, false);
    }
    public AutoDrive(double d, boolean SD) {
        requires(Robot.driveTrain);
        distance = d;
        useSmartDashboard = SD;
		SmartDashboard.putNumber("Drive To Distance", 0);
    }

    protected void initialize() {
    	if(useSmartDashboard) {
    		double val = SmartDashboard.getNumber("Drive To Distance", 0);
    		if(val == 0)
        		SmartDashboard.putNumber("Drive To Distance", 0);
            distance = val;
    	}
        
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
