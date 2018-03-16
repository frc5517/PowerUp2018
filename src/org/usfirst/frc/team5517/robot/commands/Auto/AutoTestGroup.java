package org.usfirst.frc.team5517.robot.commands.Auto;



import org.usfirst.frc.team5517.robot.commands.SpitCubeAfterTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTestGroup extends CommandGroup {

    public AutoTestGroup() {
    	addSequential(new AutoDrive(24.25));
    	addSequential(new AutoTurn(45));
    	addSequential(new AutoDrive(60));
    	addSequential(new AutoTurn(0));
    	addParallel(new SpitCubeAfterTime(2,2));
    	addSequential(new AutoDrive(30));
    	addSequential(new AutoTimedSpinIntakeOut(1));
    }
 
}
