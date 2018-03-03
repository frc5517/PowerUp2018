package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.commands.SpitCubeAfterTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTestGroup extends CommandGroup {

    public AutoTestGroup() {
    	addParallel(new SpitCubeAfterTime(3, 2));
    	addSequential(new AutoDrive(58));
    }
 
}
