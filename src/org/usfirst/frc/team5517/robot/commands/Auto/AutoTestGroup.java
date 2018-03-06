package org.usfirst.frc.team5517.robot.commands.Auto;


import org.usfirst.frc.team5517.robot.commands.SetElevatorHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTestGroup extends CommandGroup {

    public AutoTestGroup() {
    	addSequential(new AutoDrive(24));
    }
 
}
