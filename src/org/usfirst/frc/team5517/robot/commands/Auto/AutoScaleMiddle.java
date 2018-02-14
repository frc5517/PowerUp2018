package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScaleMiddle extends CommandGroup {

    public AutoScaleMiddle() {
    	
    	if(Robot.getScaleSide() == 'L') {
    		addSequential(new AutoTurn(-90));
	    	addParallel(new AutoDrive(10));
	    	addSequential(new TimedRaise(4));
	    	addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(4));
    	}
    	
    	else if(Robot.getScaleSide() == 'R') {
    		addSequential(new AutoTurn(90));
    		addParallel(new AutoDrive(10));
    		addSequential(new TimedRaise(4));
    		addSequential(new SpinIntakeOut());
    		addSequential(new TimedLower(4));
    	}    
    }
}