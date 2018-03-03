package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SpitCubeAfterTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the switch from the right
 * side of the field in with a turn autonomous.
 */

public class AutoSwitchRightWithTurn extends CommandGroup {

    public AutoSwitchRightWithTurn() {
        
    	if(Robot.getSwitchSide() == 'L') {
    		addSequential(new AutoDrive(211.5));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoDrive(116));
	    	addSequential(new AutoTurn(-135));
	    	addParallel(new SpitCubeAfterTime(3, 2));
	    	addSequential(new AutoDrive(50));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoDrive(-12));
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addSequential(new AutoDrive(149.5));
        	addSequential(new AutoTurn(-90));
	    	addParallel(new SpitCubeAfterTime(2, 2));
        	addSequential(new AutoDrive(60));
        	addSequential(new AutoTimedSpinIntakeOut(1));
        	addSequential(new AutoDrive(-12));
        	addSequential(new AutoTurn(0));
    		//addSequential(new AutoDrive(33));
        	//addSequential(new AutoTurn(90));
    	}
    }
}