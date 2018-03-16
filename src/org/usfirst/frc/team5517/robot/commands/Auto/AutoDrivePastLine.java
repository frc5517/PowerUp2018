package org.usfirst.frc.team5517.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives robot up to and slightly past auto line
 */
public class AutoDrivePastLine extends CommandGroup {

    public AutoDrivePastLine() {
        addSequential(new AutoDrive(149.5)); // auto line is 120 inches from wall
    }
}
