package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;
import org.usfirst.frc.team5517.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Drive Train subsystem
 */
public class DriveTrain extends Subsystem {

	Spark driveLeft = new Spark(RobotMap.driveTrainLeftMotorPWM);
	Spark driveRight = new Spark(RobotMap.driveTrainRightMotorPWM);
	DifferentialDrive drive = new DifferentialDrive(driveLeft, driveRight);

	public DriveTrain() {
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	} 

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void arcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(speed, rotation);
	}


	/**
	 * Stop robot drivetrain
	 */
	public void stop() {
		drive.stopMotor();
	}
}

