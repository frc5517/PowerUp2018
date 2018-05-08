package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.LowerIntake;

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
	    	addSequential(new AutoDrive(-12));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new LowerIntake());
    	
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addSequential(new AutoDrive(99));
    		addSequential(new AutoTimedSpinIntakeOut(1));
    		addSequential(new AutoDrive(-12));
	    	addSequential(new AutoTurn(90));
    		addSequential(new AutoDrive(33));
	    	addSequential(new LowerIntake());
    	}
    }
}
