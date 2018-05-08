package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses two Bag motors, spinning in opposite
 * directions, to intake the cube.
 */

public class SpinIntakeIn extends Command {

    public SpinIntakeIn() {
        requires(Robot.intake);
    }


    protected void initialize() {
    }


    protected void execute() {
    	Robot.intake.intakeIn();
    }


    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    	Robot.intake.stopIntake();
    }


    protected void interrupted() {
    	end();
    }
}
