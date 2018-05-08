package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses two miniCIM motors to raise the elevator.
 */

public class RaiseElevator extends Command {

    public RaiseElevator() {
        requires(Robot.elevator);
        requires(Robot.intake);
    }


    protected void initialize() {
    }


    protected void execute() {
    	Robot.elevator.raise();
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
