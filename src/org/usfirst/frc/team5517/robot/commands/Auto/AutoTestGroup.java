package org.usfirst.frc.team5517.robot.commands.Auto;



import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.LowerIntake;
import org.usfirst.frc.team5517.robot.commands.SetElevatorHeight;
import org.usfirst.frc.team5517.robot.commands.SpitCubeAfterTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTestGroup extends CommandGroup {

    public AutoTestGroup() {
<<<<<<< HEAD
    	if(Robot.getScaleSide() == 'L') {
    		addSequential(new AutoDrive(308));
    		addSequential(new AutoTurn(90));
    		addSequential(new SetElevatorHeight(610.08));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new SetElevatorHeight(0));
	    	addSequential(new AutoTurn(180));
    	}
    	
    	else if(Robot.getSwitchSide() == 'L') {
    		addSequential(new AutoDrive(149.5));
	    	addSequential(new AutoTurn(90));
	    	addParallel(new SpitCubeAfterTime(3, 2));
	    	addSequential(new AutoDrive(60));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoDrive(-12));
	    	addSequential(new AutoTurn(0));
	    	addSequential(new LowerIntake());
    	}
    	
    	else {
    		addSequential(new AutoDrive(222));
	    	addSequential(new AutoTurn(90));
	    	addSequential(new AutoDrive(120));
    	}
=======
    	addSequential(new AutoDrive(24.25));
    	addSequential(new AutoTurn(45));
    	addSequential(new AutoDrive(60));
    	addSequential(new AutoTurn(0));
    	addParallel(new SpitCubeAfterTime(2, 2));
    	addSequential(new AutoDrive(60));
    	addSequential(new AutoTimedSpinIntakeOut(1));
>>>>>>> 1fdc44adcdcb4ba2d627aec526caec4c30178ac0
    }
}
