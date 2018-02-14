package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchMiddle extends CommandGroup {

    public AutoSwitchMiddle() {
        
    	if(Robot.getSwitchSide() == 'L') {
	    	addSequential(new AutoTurn(-90));
    		addParallel(new AutoDrive(5));
	    	addSequential(new TimedRaise(2));
	    	addSequential(new SpinIntakeOut());
	    	addSequential(new TimedLower(2));
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addSequential(new AutoTurn(90));
    		addParallel(new AutoDrive(5));
    		addSequential(new TimedRaise(2));
    		addSequential(new SpinIntakeOut());
    		addSequential(new TimedLower(2));
    	}
    }
}