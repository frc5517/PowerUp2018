package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoSetElevatorHeight extends Command {
	
	private double distance;
	private boolean useSmartDashboard = true;
	private String smartDashboardKey = "Drive To Distance";

    public AutoSetElevatorHeight(double d, boolean SD) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
        distance = d;
        useSmartDashboard = SD;
		if(!SmartDashboard.containsKey(smartDashboardKey) && useSmartDashboard) {
			SmartDashboard.putNumber(smartDashboardKey, 0);
		}
    }
	
	public AutoSetElevatorHeight(boolean SD) {
		this(0, SD);
	}
	
	public AutoSetElevatorHeight(double distance) {
		this(distance, false);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
		if(useSmartDashboard) {
			distance = SmartDashboard.getNumber(smartDashboardKey, distance);
		}
		
		Robot.elevator.setElevatorSetpoint(distance); // drive to distance
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.pidSetElevatorHeight(); // 9.92 ticks per inch
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
