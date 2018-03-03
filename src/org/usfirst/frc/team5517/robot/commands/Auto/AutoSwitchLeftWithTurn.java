package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.LowerIntake;
import org.usfirst.frc.team5517.robot.commands.SpitCubeAfterTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the switch from the left
 * side of the field with a turn in autonomous.
 */

public class AutoSwitchLeftWithTurn extends CommandGroup {

    public AutoSwitchLeftWithTurn() {
        
    	if(Robot.getSwitchSide() == 'L') {
    		addSequential(new AutoDrive(149.5));
	    	addSequential(new AutoTurn(90));
	    	addParallel(new SpitCubeAfterTime(3, 2));
	    	addSequential(new AutoDrive(60));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoDrive(-12));
	    	addSequential(new AutoTurn(0));
	    	addSequential(new LowerIntake());
    	}
    	
    	else if(Robot.getSwitchSide() == 'R') {
    		addSequential(new AutoDrive(211.5));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(116));
	    	addSequential(new AutoTurn(135));
	    	addParallel(new SpitCubeAfterTime(2, 2));
	    	addSequential(new AutoDrive(50));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoDrive(-12));
	    	addSequential(new LowerIntake());
    	}
    }
}