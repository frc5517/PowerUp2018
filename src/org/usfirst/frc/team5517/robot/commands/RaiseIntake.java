package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses one solenoid controlling one large cylinder
 * to lift the intake arms.
 */


public class RaiseIntake extends Command {

    public RaiseIntake() {
        requires(Robot.intake);
    }


    protected void initialize() {
    }


    protected void execute() {
    	Robot.intake.liftIntake();
    	Robot.intake.intakePinch();
    }


    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    	Robot.intake.stopIntakeLift();
    	Robot.intake.stopIntakePinch();
    }


    protected void interrupted() {
    	end();
    }
}
