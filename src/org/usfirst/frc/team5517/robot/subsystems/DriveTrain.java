package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;
import org.usfirst.frc.team5517.robot.commands.ArcadeDrive;
import org.usfirst.frc.team5517.robot.sensors.ADXRS453Gyro;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Drive Train subsystem
 */

public class DriveTrain extends Subsystem {
	
	private final static double angleP = 1.0,
								angleI = 0.0, 
								angleD = 0.0,
								distP  = 1.0, 
								distI  = 0.0, 
								distD  = 0.0;

	// Creating the drivetrain.
	Spark driveLeft = new Spark(RobotMap.driveTrainLeftMotorPWM);
	Spark driveRight = new Spark(RobotMap.driveTrainRightMotorPWM);
	DifferentialDrive drive = new DifferentialDrive(driveLeft, driveRight);

	// Creating the sensors that will be tuned with PID loops.
	PIDController angleController;
	PIDController distanceController;

	ADXRS453Gyro gyro;
	Encoder leftEncoder;
	Encoder rightEncoder;
	
	class BasicPIDOutput implements PIDOutput {
		private double output = 0;
		public double getOutput() {
			return output;
		}
		@Override
		public void pidWrite(double output) {
			this.output = output;
		}
	}
	
	// Creating the PIDOutputs coming from distController and angleController.
	private BasicPIDOutput distPidOutput = new BasicPIDOutput();
	private BasicPIDOutput anglePidOutput = new BasicPIDOutput();
	private PIDSource distPIDSource = new PIDSource() {
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return null;
		}

		@Override
		public double pidGet() {
			return getEncoderValue();
		}
		
	};
	
	// Calling everything into the drivetrain and initializing the sensors.
	public DriveTrain() {
		gyro = new ADXRS453Gyro();
		gyro.startThread();
		
		gyro.setPIDSourceType(PIDSourceType.kRate);
		
		leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB);
		rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB);
		
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		
		angleController = new PIDController(angleP, angleI, angleD, gyro, anglePidOutput);
		distanceController = new PIDController(distP, distI, distD, distPIDSource, distPidOutput);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	} 

	private double getEncoderValue() {
		// average of both encoders for now
		double output = (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
		return output;
	}
	
	/**
	 * Set distance setpoint.
	 * @param dist
	 */
	public void setDistanceSetpoint(double dist) {
		distanceController.setSetpoint(dist);
		distanceController.enable();
	}
	
	/**
	 * Set angle setpoint.
	 * @param angle
	 */
	public void setAngleSetpoint(double angle) {
		angleController.setSetpoint(angle);
		angleController.enable();
	}
	
	/**
	 * Set the setpoint  of the angle pid controller to current angle
	 */
	public void setAngleToCurrent() {
		angleController.setSetpoint(gyro.getAngle());
		angleController.enable();
	}

	/**
	 * Drive with both PID controllers' output.
	 * @param distance
	 */
	public void drivePidAngleAndDist() {
		int speed = 0;
		drive.arcadeDrive(
			speed,  // speed
			anglePidOutput.getOutput()  // 
		);
	}
	
	/**
	 * Check if the robot has reached the distance.
	 * @return has reached distance
	 */
	public boolean hasReachedDistance() {
		return distanceController.getError() == 0;
	}
	
	/**
	 * Check if the robot has reached the angle.
	 * @return has reached angle
	 */
	public boolean hasReachedAngle() {
		return angleController.getError() == 0;	
	}

	// Implementing Tank Drive.
	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	// Implementing Arcade Drive.
	public void arcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(speed, rotation);
		speed = speed*speed;	// Squaring the speed to have a smooth gain in speed rather than an instant jolt.
		rotation = rotation/2;	// Halving the rotation to make it the robot a little easier to control when turning.
	}

	// Stopping the drivetrain.
	public void stop() {
		angleController.disable();
		distanceController.disable();
		drive.stopMotor();
	}
}

