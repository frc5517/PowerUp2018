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
	    	addSequential(new AutoDrive(240));
	    	addSequential(new AutoTurn(0));
	    	addSequential(new AutoDrive(102));
	    	addSequential(new AutoSetElevatorHeight(72));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoSetElevatorHeight(0)); 
    	}
    	
    	else if(Robot.getScaleSide() == 'R') {
    		addSequential(new AutoDrive(303));
    		addSequential(new AutoTurn(-90));
    		addSequential(new AutoSetElevatorHeight(72));
	    	addSequential(new AutoTimedSpinIntakeOut(2));
	    	addSequential(new AutoSetElevatorHeight(0));
	    	addSequential(new AutoTurn(-180));
    	}
    }
}
