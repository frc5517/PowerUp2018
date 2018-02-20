package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the scale from the left
 * side of the field in autonomous.
 */

public class AutoScaleLeft extends CommandGroup {

    public AutoScaleLeft() {
    	if(Robot.getScaleSide() == 'L') {
    		addParallel(new AutoDrive(299));
	    	addSequential(new TimedRaise(4));
	    	addSequential(new AutoTimedSpinIntakeOut(2));
	    	addSequential(new TimedLower(4));
    	}
    	
    	else if(Robot.getScaleSide() == 'R') {
	    	addSequential(new AutoDrive(222));
	    	addSequential(new AutoTurn(90));
	    	addParallel(new AutoDrive(251));
	    	addSequential(new TimedRaise(4)); 
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new TimedLower(4)); 
    	}
    }
}
