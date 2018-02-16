package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses two Bag motors, spinning in opposite directions,
 * to spit the cube out.
 */

public class SpinIntakeOut extends Command {

    public SpinIntakeOut() {
        requires(Robot.intake);
    }


    protected void initialize() {
    }


    protected void execute() {
    	Robot.intake.intakeOut();
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
