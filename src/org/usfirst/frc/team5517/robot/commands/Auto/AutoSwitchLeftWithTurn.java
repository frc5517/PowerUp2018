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
    		addSequential(new AutoDrive(146.5));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(3));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	//addSequential(new AutoDrive(-12));
	    	addSequential(new AutoTurn(0));
    		//addSequential(new AutoDrive(33));
	    	addSequential(new AutoTurn(90));
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addParallel(new AutoDrive(208));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoDrive(15));
	    	addSequential(new AutoTurn(0));
	    	addSequential(new AutoDrive(6));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoDrive(-12));
	    	addSequential(new AutoTurn(90));
    	}
    }
}