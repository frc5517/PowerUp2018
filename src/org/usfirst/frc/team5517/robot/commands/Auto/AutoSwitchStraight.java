package org.usfirst.frc.team5517.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is for scoring in the switch from the left
 * or the right side of the field without a turn
 * in autonomous.
 */

public class AutoSwitchStraight extends CommandGroup {

    public AutoSwitchStraight() {
		addParallel(new AutoDrive(140));
    	addSequential(new AutoTimedSpinIntakeOut(2));
    }
}
