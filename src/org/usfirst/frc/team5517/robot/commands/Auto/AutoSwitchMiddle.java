package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchMiddle extends CommandGroup {

    public AutoSwitchMiddle() {
        
    	if(Robot.getGameDataString() == "LLL") {
	    	addParallel(new AutoDrive(5, -90));
	    	addSequential(new TimedRaise(2));
	    	addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(2));
    	}
    	
    	else if(Robot.getGameDataString() == "LLR") {
    		addParallel(new AutoDrive(5, -90));
	    	addSequential(new TimedRaise(2));
	    	addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(2));
    	}
    	
    	else if(Robot.getGameDataString() == "LRL") {
    		addParallel(new AutoDrive(5, -90));
	    	addSequential(new TimedRaise(2));
	    	addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(2));
    	}
    	
    	else if(Robot.getGameDataString() == "LRR") {
    		addParallel(new AutoDrive(5, -90));
	    	addSequential(new TimedRaise(2));
	    	addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(2));
    	}
    	
    	else if(Robot.getGameDataString() == "RRR") {
    		addParallel(new AutoDrive(5, 90));
    		addSequential(new TimedRaise(2));
    		addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(2));
    	}
    	
    	else if(Robot.getGameDataString() == "RRL") {
    		addParallel(new AutoDrive(5, 90));
    		addSequential(new TimedRaise(2));
    		addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(2));
    	}
    	
    	else if(Robot.getGameDataString() == "RLR") {
    		addParallel(new AutoDrive(5, 90));
    		addSequential(new TimedRaise(2));
    		addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(2));
    	}
    	
    	else if(Robot.getGameDataString() == "RLL") {
    		addParallel(new AutoDrive(5, 90));
    		addSequential(new TimedRaise(2));
    		addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(2));
    	}
    }
}
