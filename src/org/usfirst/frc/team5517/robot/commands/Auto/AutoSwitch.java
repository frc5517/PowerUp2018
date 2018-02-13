package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;
import org.usfirst.frc.team5517.robot.commands.TimedRaise;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitch extends CommandGroup {

    public AutoSwitch() {
        
    	addParallel(new AutoDriveForward(5));
    	addSequential(new TimedRaise(2));
    	addSequential(new SpinIntakeOut());
    }
}
