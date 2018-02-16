package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses one solenoid controlling two cylinders to
 * pinch the Power Cube.
 */

public class IntakePinch extends Command {

    public IntakePinch() {
        requires(Robot.intake);
    }


    protected void initialize() {
    }


    protected void execute() {
    	Robot.intake.intakePinch();
    }


    protected boolean isFinished() {
        return false;
    }

    
    protected void end() {
    	Robot.intake.stopIntakePinch();
    }



    protected void interrupted() {
    	end();
    }
}
