package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;

import org.usfirst.frc.team5517.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
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

	Spark driveLeft = new Spark(RobotMap.driveTrainLeftMotorPWM);
	Spark driveRight = new Spark(RobotMap.driveTrainRightMotorPWM);
	DifferentialDrive drive = new DifferentialDrive(driveLeft, driveRight);

	PIDController angleController;
	PIDController distanceController;

	ADXRS450_Gyro gyro;
	//ADXRS453Gyro gyro;
	Encoder leftEncoder;
	Encoder rightEncoder;

	
	public DriveTrain() {
		// not sure if this gyro class will work for the 453, we'll see...
		// it implements PIDSource so it can be used with PIDController
		gyro = new ADXRS450_Gyro();
		//gyro = new ADXRS453Gyro();
		//gyro.startThread();
		
		// TODO: use robotmap constants for these ports!
		leftEncoder = new Encoder(0, 1);
		rightEncoder = new Encoder(2, 3);
		
		angleController = new PIDController(angleP, angleI, angleD, gyro, distPIDOutput);
		distanceController = new PIDController(distP, distI, distD, distPIDSource, anglePIDOutput);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	} 
	
	private PIDSource distPIDSource = new PIDSource() {
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public double pidGet() {
			return getEncoderValue();
		}
		
	};
	
	private PIDOutput distPIDOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			drive.arcadeDrive(output, 0);
		}
	};
	
	private PIDOutput anglePIDOutput = new PIDOutput() {
		@Override
		public void pidWrite(double output) {
			drive.arcadeDrive(0, output);
		}
	};

	private double getEncoderValue() {
		// average of both encoders for now
		double output = (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
		return output;
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

