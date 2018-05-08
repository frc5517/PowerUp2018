package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Dual stick Arcade drive
 * Forward/Reverse on the left stick,
 * Turning left/right on the right stick.
 */

public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        requires(Robot.driveTrain);
    }

    
    protected void initialize() {
    }

    //Getting the Y-value of the left stick and the X-value of the right stick on the main driver gamepad.
    protected void execute() {
    	Robot.driveTrain.arcadeDrive(
    		Robot.oi.getMainController().getLeftY(), // speed
    		Robot.oi.getMainController().getRightX() // rotation
    	);
    }


    protected boolean isFinished() {
        return false;
    }


    protected void end() {
    	Robot.driveTrain.stop();
    }

    
    protected void interrupted() {
    	end();
    }
}
