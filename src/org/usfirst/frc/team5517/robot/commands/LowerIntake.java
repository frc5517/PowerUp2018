package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses one solenoid controlling one large cylinder
 * to lower the intake arms.
 */

public class LowerIntake extends Command {

    public LowerIntake() {
        requires(Robot.intake);
    }


    protected void initialize() {
    }


    protected void execute() {
    	Robot.intake.lowerIntake();
    }


    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    	Robot.intake.stopIntakeLift();
    }


    protected void interrupted() {
    	end();
    }
}
