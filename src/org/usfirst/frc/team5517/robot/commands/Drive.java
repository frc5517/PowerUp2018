package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.OI;
import org.usfirst.frc.team5517.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	private OI oi = Robot.oi;

	public Drive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrainSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrainSubsystem.arcadeDrive(
			oi.getLeftJoystickY(), // Linear Motion
			oi.getRightJoystickX() // Rotation
		);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
