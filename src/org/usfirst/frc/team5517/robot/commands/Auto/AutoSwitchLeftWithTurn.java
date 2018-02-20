package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the switch from the left
 * side of the field with a turn in autonomous.
 */

public class AutoSwitchLeftWithTurn extends CommandGroup {

    public AutoSwitchLeftWithTurn() {
        
    	if(Robot.getSwitchSide() == 'L') {
    		addParallel(new AutoDrive(168));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addParallel(new AutoDrive(222));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoDrive(15));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoDrive(6));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
    	}
    }
}