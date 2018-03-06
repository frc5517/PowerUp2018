package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SetElevatorHeight;
import org.usfirst.frc.team5517.robot.commands.SpinIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the scale from the middle
 * of the field in autonomous.
 */

public class AutoScaleMiddle extends CommandGroup {

    public AutoScaleMiddle() {
    	
    	if(Robot.getScaleSide() == 'L') {
    		addSequential(new AutoDrive(95));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new AutoDrive(81));
	    	addSequential(new AutoTurn(110));
	    	addParallel(new AutoDrive(79));
	    	addSequential(new SetElevatorHeight(446.4));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new SetElevatorHeight(0));
	    }
    	
    	else if(Robot.getScaleSide() == 'R') {
    		addSequential(new AutoDrive(95));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(81));
	    	addSequential(new AutoTurn(-110));
	    	addParallel(new AutoDrive(79));
	    	addSequential(new SetElevatorHeight(446.4));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new SetElevatorHeight(0));
	    }    
    }
}