package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.RobotMap;
import org.usfirst.frc.team5517.robot.commands.ArcadeDrive;
import org.usfirst.frc.team5517.robot.sensors.ADXRS453Gyro;
import org.usfirst.frc.team5517.robot.utils.Debouncer;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Drive Train subsystem
 */

public class DriveTrain extends Subsystem {
	
	private final static double MAX_PID_DRIVE_SPEED = 0.65;
	private final static double angleP = 1.0,
								angleI = 0.0, 
								angleD = 0.0,
								distP  = 1.0, 
								distI  = 0.0, 
								distD  = 0.0;

	// Drive train motors
	private Spark driveLeft = new Spark(RobotMap.driveTrainLeftMotorPWM);
	private Spark driveRight = new Spark(RobotMap.driveTrainRightMotorPWM);
	private DifferentialDrive drive = new DifferentialDrive(driveLeft, driveRight);

	// PID
	private PIDController anglePid;
	private PIDController distancePid;
	private Encoder driveEncoder;
	
	// Gyro variables
	private ADXRS453Gyro gyro;
	private double curAngle;
	private double lastAngle;
	private boolean gyroCalibrating;
	private boolean lastGyroCalibrating;
	private int gyroReinits;
	private Debouncer gyroDriftDetector;
	
	
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
	
	// Calling everything into the drivetrain and initializing the sensors.
	public DriveTrain() {
		gyro = new ADXRS453Gyro();
		gyro.startThread();
		
		gyroDriftDetector = new Debouncer(1.0);
		
		gyro.setPIDSourceType(PIDSourceType.kRate);
		
		driveEncoder = new Encoder(RobotMap.driveEncoderA, RobotMap.driveEncoderB, false, Encoder.EncodingType.k4X);
		
		driveEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		driveEncoder.setDistancePerPulse(6*Math.PI/360);
		driveEncoder.setReverseDirection(true);
		
		anglePid = new PIDController(angleP, angleI, angleD, gyro, anglePidOutput);
		distancePid = new PIDController(distP, distI, distD, driveEncoder, distPidOutput);
		anglePid.disable();
		distancePid.disable();
		distancePid.setOutputRange(0, MAX_PID_DRIVE_SPEED);
		
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	} 

	private double getEncoderValue() {
		return driveEncoder.getDistance();
	}
	
	/**
	 * Set distance setpoint.
	 * @param dist
	 * 
	 */
	public void setDistanceSetpoint(double dist) {
		distancePid.setSetpoint(dist);
		System.out.println("Setting setpoint to " + dist);
		distancePid.enable();
	}
	
	/**
	 * Set angle setpoint.
	 * @param angle
	 */
	public void setAngleSetpoint(double angle) {
		anglePid.setSetpoint(angle);
		//angleController.enable();
	}
	
	/**
	 * Set the setpoint  of the angle pid controller to current angle
	 */
	public void setAngleToCurrent() {
		anglePid.setSetpoint(gyro.getAngle());
		anglePid.enable();
	}

	/**
	 * Drive with both PID controllers' output.
	 * @param distance
	 */
	public void drivePidAngleAndDist() {
		System.out.println("SPEED: " + -distPidOutput.getOutput());
		System.out.println("ENCODER: " + driveEncoder.getDistance());
		drive.arcadeDrive(
			-distPidOutput.getOutput(),
			0 //anglePidOutput.getOutput()
		);
	}
	
	/**
	 * Check if the robot has reached the distance.
	 * @return has reached distance
	 */
	public boolean hasReachedDistance() {
		System.out.println("ERROR: " + distancePid.getError());
		boolean hasReached = driveEncoder.getDistance() >= distancePid.getSetpoint();

    	if(hasReached)
    		System.out.println("Robot has reached distance");
    	
    	return hasReached;
	}
	
	/**
	 * Check if the robot has reached the angle.
	 * @return has reached angle
	 */
	public boolean hasReachedAngle() {
		return anglePid.getError() == 0;	
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
		anglePid.disable();
		distancePid.disable();
		drive.stopMotor();
	}
	
	public void printEncoderAndGyroVals() {
		System.out.println("DRIVETRAIN ENCODER: " + driveEncoder.getDistance());
		System.out.println("GYRO: " + gyro.getAngle());
	}
	
	public double getAngle() {
		return gyro.getAngle();
	}
	
	public boolean isGyroCalibrating() {
		return gyro.isCalibrating();
	}
	
	public void calibrateGyro() {
		gyro.calibrate();
	}
	
	public void reinitGyro() {
		curAngle = getAngle();
		gyroCalibrating = isGyroCalibrating();

		if (lastGyroCalibrating && !gyroCalibrating) {
			// if we've just finished calibrating the gyro, reset
			gyroDriftDetector.reset();
			curAngle = getAngle();
			// reset target angle so when we enable it doesn't try to correct
			// to the target angle before gyro calibrated
			anglePid.disable();
			System.out.println("Finished auto-reinit gyro");
		}
		else if (gyroDriftDetector.update(Math.abs(curAngle - lastAngle) > (0.75 / 50.0))
				&& !Robot.matchStarted && !gyroCalibrating) {
			// start calibrating gyro
			gyroReinits++;
			System.out.println("!!! Sensed drift, about to auto-reinit gyro (#"+ gyroReinits + ")");
			gyro.calibrate();
		}

		lastAngle = curAngle;
		lastGyroCalibrating = gyroCalibrating;
		
	}
}

