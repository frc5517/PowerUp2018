package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	private double angle;
	
    public AutoTurn(int a) {
        requires(Robot.driveTrain);
        angle = a;
    }

    protected void initialize() {
    	Robot.driveTrain.setAngleToCurrent();
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
