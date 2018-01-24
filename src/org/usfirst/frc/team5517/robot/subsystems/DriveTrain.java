package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;
import org.usfirst.frc.team5517.robot.commands.TankDrive;
import org.usfirst.frc.team5517.robot.sensors.ADXRS453Gyro;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Drive Train subsystem
 */
public class DriveTrain extends Subsystem {

	private final double JOYSTICK_TOLERANCE = 0.1;
	private final double ERROR_TOLERANCE = 3;

	ADXRS453Gyro gyro;


	/**
	 *  The current gyro angle
	 */
	private double currentAngle = 0;


	/**
	 * Target angle of the robot
	 * (what angle we want the robot to close in on)
	 */ 
	private double targetAngle = 0;


	/**
	 * The difference (error) of the target angle and current angle
	 */
	private double diff = 0;

	/**
	 * Value added to the motor to compensate error
	 */
	private double compensation = 0;


	/**
	 * Value added to the motor's to compensate the error
	 */
	private long lastUpdatedTargetAngleTime = 0;


	Spark driveLeft = new Spark(RobotMap.driveTrainLeftMotorPWM);
	Spark driveRight = new Spark(RobotMap.driveTrainRightMotorPWM);
	DifferentialDrive drive = new DifferentialDrive(driveLeft, driveRight);

	public DriveTrain() {
		gyro = new ADXRS453Gyro();
		gyro.startThread();
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	} 

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void arcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(speed, rotation);
		currentAngle = this.getHeading();
		diff = Math.IEEEremainder(targetAngle, currentAngle);
		compensation = 0;

		speed = speed*speed*speed;
		rotation = rotation*rotation;

		if(rotation != 0) {
			setTargetAngle(currentAngle);
			lastUpdatedTargetAngleTime = System.nanoTime();
		}

		/*else if(500 < nanoToMilli(System.nanoTime()-lastUpdatedTargetAngleTime)) {
			System.out.print("gyro angle: " + currentAngle);
			System.out.print(", target angle: " + targetAngle);
		}*/
	}



	public void setTargetAngle(double angle) {
		targetAngle = angle;
	}

	public void turnToAngle(double angle) {
		setTargetAngle(angle);
	}

	public boolean isAngleOnTarget() {
		return Math.abs(diff) <= ERROR_TOLERANCE;
	}

	public double getHeading() {
		return gyro.getAngle();
	}

	public double getCorrectedHeading() {
		return correctAngle(gyro.getAngle());
	}

	private double correctAngle(double angle) {
		return angle + 360*Math.floor(0.5-angle/360);
	}

	public boolean isGyroCalibrating() {
		return gyro.isCalibrating();
	}

	public void calibrateGyro() {
		gyro.calibrate();
	}

	public void stopCalibrating() {
		gyro.stopCalibrating();
	}

	/**
	 * Stop robot drivetrain
	 */
	public void stop() {
		drive.stopMotor();
	}
}

