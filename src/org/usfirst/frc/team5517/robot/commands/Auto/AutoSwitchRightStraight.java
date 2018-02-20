package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the switch from the left
 * side of the field without a turn in autonomous.
 */

public class AutoSwitchRightStraight extends CommandGroup {

    public AutoSwitchRightStraight() {
    	if(Robot.getSwitchSide() == 'L') {
    		addParallel(new AutoDrive(222));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(180));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(6));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addParallel(new AutoDrive(-2));
	    	addSequential(new AutoTurn(-90));
    	
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addSequential(new AutoDrive(140));
    		addSequential(new AutoTimedSpinIntakeOut(1));
    		addParallel(new AutoDrive(-2));
	    	addSequential(new AutoTurn(90));
    		addSequential(new AutoDrive(33));
    	}
    }
}
