package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.OI;
import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Dual stick Tank Drive
 * Left side of the chassis is controlled by
 * the left stick while the Right side of the
 * chassis is controlled by the right stick.
 */

public class TankDrive extends Command {

	private OI oi = Robot.oi;

	public TankDrive() {
		requires(Robot.driveTrain);
	}


	protected void initialize() {
	}

/**
 * Getting the Y-values of both sticks.
 */
	protected void execute() {
		Robot.driveTrain.tankDrive(
			oi.getMainController().getLeftY(),
			oi.getMainController().getRightY()
		);
	}


	protected boolean isFinished() {
		return false;
	}


	protected void end() {
		Robot.driveTrain.stop();
	}


	protected void interrupted() {
		end();
	}
}