package org.usfirst.frc.team5517.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveForward extends CommandGroup {

    public AutoDriveForward() {
        addSequential(new AutoDrive(120));
        addSequential(new AutoTimedSpinIntakeOut(2));
    }
    
}
