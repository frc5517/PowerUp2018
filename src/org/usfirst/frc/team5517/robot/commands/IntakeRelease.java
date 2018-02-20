package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses one solenoid controlling two cylinders to 
 * release the Power Cube.
 */

public class IntakeRelease extends Command {

    public IntakeRelease() {
        requires(Robot.intake);
    }


    protected void initialize() {
    }


    protected void execute() {
    	Robot.intake.intakeRelease();
    }


    protected boolean isFinished() {
        return true;
    }


    protected void end() {
    	Robot.intake.stopIntakePinch();
    }


    protected void interrupted() {
    	end();
    }
}
