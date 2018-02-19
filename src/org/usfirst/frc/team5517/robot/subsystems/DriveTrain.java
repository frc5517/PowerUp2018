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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive Train subsystem
 */

public class DriveTrain extends Subsystem {
	
	private final boolean TUNE_PID = true;
	private final double MAX_PID_DRIVE_SPEED = 0.65;
	
	private double angleP = 0.1,
				   angleI = 0,
				   angleD = 0,
				   distP  = 0.1, 
				   distI  = 0,
				   distD  = 0;

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
	private Debouncer gyroDriftDetector;
	private double curAngle;
	private double lastAngle;
	private boolean gyroCalibrating;
	private boolean lastGyroCalibrating;
	private int gyroReinits;
	
	
	class PIDOutputHandler implements PIDOutput {
		private double output = 0;
		public double getOutput() {
			return output;
		}
		@Override
		public void pidWrite(double output) {
			this.output = output;
		}
	}
	
	// Creating the PIDOutputs coming from distance PID controller and angle PID controller.
	private PIDOutputHandler distPidOutput = new PIDOutputHandler();
	private PIDOutputHandler anglePidOutput = new PIDOutputHandler();
	
	// Initialize drive train objects, variables, and configuration
	public DriveTrain() {
		gyro = new ADXRS453Gyro();
		gyro.setPIDSourceType(PIDSourceType.kRate);
		gyro.startThread();
		
		gyroDriftDetector = new Debouncer(1.0);
		
		driveEncoder = new Encoder(RobotMap.driveEncoderA, RobotMap.driveEncoderB, true, Encoder.EncodingType.k4X);
		driveEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		driveEncoder.setDistancePerPulse(6*Math.PI/360);
		driveEncoder.setReverseDirection(true);
		
		// if in tune PID mode, get the numbers from SmartDashboard
		// defaulting to the variable values
		if(TUNE_PID) {
			angleP = SmartDashboard.getNumber("AngleP", angleP);
			angleI = SmartDashboard.getNumber("AngleI", angleI);
			angleD = SmartDashboard.getNumber("AngleD", angleD);
			distP  = SmartDashboard.getNumber("DistanceP", distP);
			distI  = SmartDashboard.getNumber("DistanceI", distI);
			distD  = SmartDashboard.getNumber("DistanceD", distD);
		}
		anglePid = new PIDController(angleP, angleI, angleD, gyro, anglePidOutput);
		distancePid = new PIDController(distP, distI, distD, driveEncoder, distPidOutput);
		distancePid.setOutputRange(0, MAX_PID_DRIVE_SPEED);
		
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	} 

	@SuppressWarnings("unused")
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
	 * Set the setpoint of the angle PID controller to current angle
	 */
	public void setAngleToCurrent() {
		setAngleSetpoint(gyro.getAngle());
	}

	/**
	 * Drive with both PID controllers' outputs.
	 * @param distance
	 */
	public void drivePidAngleAndDist() {
		double speed = -distPidOutput.getOutput();
		double angle = 0; //anglePidOutput.getOutput();
		SmartDashboard.putNumber("Drive Speed", speed);
		SmartDashboard.putNumber("Drive Angle", angle);
		drive.arcadeDrive(speed, angle);
	}
	
	/**
	 * Send sensor values to SmartDashboard
	 */
	public void sendDataToSmartDashboard() {
		SmartDashboard.putNumber("Drive PID Setpoint", distancePid.getSetpoint());
		SmartDashboard.putNumber("Drive PID Error", distancePid.getError());
		SmartDashboard.putNumber("Drive Encoder Raw Value", driveEncoder.get());
		SmartDashboard.putNumber("Drove Encoder Distance", driveEncoder.getDistance());
		
		SmartDashboard.putNumber("Angle PID Setpoint", anglePid.getSetpoint());
		SmartDashboard.putNumber("Angle PID Error", anglePid.getError());
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
		SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
	}
	
	/**
	 * Check if the robot has reached the distance.
	 * @return has reached distance
	 */
	public boolean hasReachedDistance() {
		boolean hasReached = driveEncoder.getDistance() >= distancePid.getSetpoint();
    	if(hasReached) {
    		System.out.println("Robot has reached distance: " + distancePid.getSetpoint() + "in. with error of " + distancePid.getError());
    	}
    	return hasReached;
	}
	
	/**
	 * Check if the robot has reached the angle.
	 * @return has reached angle
	 */
	public boolean hasReachedAngle() {
		// ensure error is within reasonable tolerance
		return Math.abs(anglePid.getError()) < 2;
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
	
	// Gyro Methods
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

