package org.usfirst.frc.team5517.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * This is to delay our autonomous, in case we possibly run into
 * other robots when turning or going to the switch/scale.
 */

public class AutoWait extends TimedCommand {

    public AutoWait(double timeout) {
        super(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after timeout
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
