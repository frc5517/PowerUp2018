
package org.usfirst.frc.team5517.robot.commands.Auto;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.SetElevatorHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the scale from the left
 * side of the field in autonomous.
 */

public class AutoScaleLeft extends CommandGroup {

    public AutoScaleLeft() {
    	if(Robot.getScaleSide() == 'L') {
    		addSequential(new AutoDrive(303));
    		addSequential(new AutoTurn(90));
    		addSequential(new SetElevatorHeight(610.08));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new SetElevatorHeight(0));
	    	addSequential(new AutoTurn(180));
    	}
    	
    	else if(Robot.getScaleSide() == 'R') {
	    	addSequential(new AutoDrive(222));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(240));
	    	addSequential(new AutoTurn(0));
	    	addSequential(new AutoDrive(102));
	    	addSequential(new AutoTurn(-90));
	    	addSequential(new SetElevatorHeight(610.08));
	    	//addSequential(new AutoTimedSpinIntakeOut(1));
	    	//addSequential(new SetElevatorHeight(0)); 
    	}
    }
}
