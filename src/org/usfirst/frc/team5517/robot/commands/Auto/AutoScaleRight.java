package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the scale from the right
 * side of the field in autonomous.
 */

public class AutoScaleRight extends CommandGroup {

    public AutoScaleRight() {
    	if(Robot.getScaleSide() == 'L') {
	    	addSequential(new AutoDrive(222));
	    	addSequential(new AutoTurn(-90));
	    	addParallel(new AutoDrive(251));
	    	addSequential(new TimedRaise(4)); 
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new TimedLower(4)); 
    	}
    	
    	else if(Robot.getScaleSide() == 'R') {
    		addParallel(new AutoDrive(140));
    		addSequential(new TimedRaise(4)); 
        	addSequential(new AutoTimedSpinIntakeOut(1));
    		addSequential(new TimedLower(4)); 
    	}
    }
}
