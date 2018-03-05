package org.usfirst.frc.team5517.robot.commands.Auto;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTestGroup extends CommandGroup {

    public AutoTestGroup() {
    	addSequential(new AutoSetElevatorHeight(357.12));
    }
 
}
