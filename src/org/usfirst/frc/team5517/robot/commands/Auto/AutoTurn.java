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
	
	public AutoTurn(int a) {
		this(a, false);	
	}
	public AutoTurn(boolean SD) {
		this(0, SD);
	}
	
    public AutoTurn(int a, boolean SD) {
        requires(Robot.driveTrain);

    	useSmartDashboard = SD;
        angle = a;
    }

    protected void initialize() {
    	if(useSmartDashboard) {
			double val = SmartDashboard.getNumber("Turn To Angle", 0);
	    	if(val == 0)
	    		SmartDashboard.putNumber("Turn To Angle", 0);
	        angle = val;
	        System.out.println("Turn to angle from SD = "+angle);
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
