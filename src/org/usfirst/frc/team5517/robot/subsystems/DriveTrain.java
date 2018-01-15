package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;
import org.usfirst.frc.team5517.robot.commands.Drive;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Drive Train subsystem
 */
public class DriveTrain extends Subsystem {

     Spark driveLeft1 = new Spark(RobotMap.driveTrainLeftMotor1PWM);
	 Spark driveLeft2 = new Spark(RobotMap.driveTrainLeftMotor2PWM);
	 Spark driveRight1 = new Spark(RobotMap.driveTrainRightMotor1PWM);
	 Spark driveRight2 = new Spark(RobotMap.driveTrainRightMotor2PWM);
 
	 SpeedControllerGroup driveLeft = new SpeedControllerGroup(driveLeft1, driveLeft2);
	 SpeedControllerGroup driveRight = new SpeedControllerGroup(driveRight1, driveRight2);
		 
	 DifferentialDrive drive = new DifferentialDrive(driveLeft, driveRight);


	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	} 

	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}
	
	public void arcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(speed, rotation);
	}
}

