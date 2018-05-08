package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses two miniCIM motors to lower the elevator 
 * and one solenoid controlling two cylinders to pinch, 
 * as to not scrape the walls of the robot.
 */

public class LowerElevator extends Command {

    public LowerElevator() {
        requires(Robot.elevator);
        requires(Robot.intake);
    }


    protected void initialize() {
    }


    protected void execute() {
    	Robot.elevator.lower();
    	Robot.intake.intakePinch();
    }


    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    	Robot.elevator.stop();
    	Robot.intake.stopIntakePinch();
    }

    
    protected void interrupted() {
    	end();
    }
}
