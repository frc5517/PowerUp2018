package org.usfirst.frc.team5517.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTestGroup extends CommandGroup {

    public AutoTestGroup() {
        addSequential(new AutoDrive(true));
        addSequential(new AutoTurn(true));
        addSequential(new AutoTimedSpinIntakeOut(2));
    }
 
}
