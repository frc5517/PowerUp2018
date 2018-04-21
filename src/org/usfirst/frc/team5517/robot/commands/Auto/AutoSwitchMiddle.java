package org.usfirst.frc.team5517.robot.commands.Auto;



import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.IntakeRelease;
import org.usfirst.frc.team5517.robot.commands.LowerIntake;
import org.usfirst.frc.team5517.robot.commands.SpitCubeAfterTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchMiddle extends CommandGroup {

    public AutoSwitchMiddle() {
    	if(Robot.getSwitchSide() == 'L') {
    		addSequential(new AutoDrive(24.25));
	    	addSequential(new AutoTurn(-45));
	    	addSequential(new AutoDrive(72));
	    	addSequential(new AutoTurn(0));
	    	addParallel(new SpitCubeAfterTime(2, 1));
	    	addSequential(new AutoDrive(80));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoDrive(-36));
	    	addParallel(new AutoTurn(45));
	    	addSequential(new LowerIntake());
	    	addSequential(new IntakeRelease());
    	}
    	
    	if(Robot.getSwitchSide() == 'R') {
    		addSequential(new AutoDrive(24.25));
	    	addSequential(new AutoTurn(45));
	    	addSequential(new AutoDrive(72));
	    	addSequential(new AutoTurn(0));
	    	addParallel(new SpitCubeAfterTime(2, 1));
	    	addSequential(new AutoDrive(80));
	    	addSequential(new AutoTimedSpinIntakeOut(1));
	    	addSequential(new AutoDrive(36));
	    	addParallel(new AutoTurn(-45));
	    	addSequential(new LowerIntake());
	    	addSequential(new IntakeRelease());
    	}
    }
 
}