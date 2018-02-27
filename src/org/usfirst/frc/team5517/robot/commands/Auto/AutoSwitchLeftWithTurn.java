package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.LowerIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the switch from the left
 * side of the field with a turn in autonomous.
 */

public class AutoSwitchLeftWithTurn extends CommandGroup {

    public AutoSwitchLeftWithTurn() {
        
    	if(Robot.getSwitchSide() == 'L') {
    		addSequential(new AutoDrive(146.5));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(34));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoDrive(-12));
	    	addSequential(new AutoTurn(0));
	    	addSequential(new LowerIntake());
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addSequential(new AutoDrive(211.5));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(173));
	    	addSequential(new AutoTurn(180));
	    	addSequential(new AutoDrive(25));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoDrive(-12));
	    	addSequential(new LowerIntake());
    	}
    }
}