package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the switch from the right
 * side of the field in with a turn autonomous.
 */

public class AutoSwitchRightWithTurn extends CommandGroup {

    public AutoSwitchRightWithTurn() {
        
    	if(Robot.getSwitchSide() == 'L') {
    		addSequential(new AutoDrive(222));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoDrive(180));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoDrive(6));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addParallel(new AutoDrive(168));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
    	}
    }
}