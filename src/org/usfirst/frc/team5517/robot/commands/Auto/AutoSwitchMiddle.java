package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the switch from the middle
 * of the field in autonomous.
 */

public class AutoSwitchMiddle extends CommandGroup {

    public AutoSwitchMiddle() {
        
    	if(Robot.getSwitchSide() == 'L') {
    		addSequential(new AutoDrive(95));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoDrive(24));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(44));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addSequential(new AutoDrive(95));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(24));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoDrive(44));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
    	}
    }
}