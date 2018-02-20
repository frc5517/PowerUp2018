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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive Train subsystem
 */

public class DriveTrain extends Subsystem {
	
	/**
	 * Set this to true to read values from SmartDashboard
	 * false to read from the below PID variables
	 */
	private final boolean TUNE_PID = true;
	
	/**
	 * Speed for PID controlled driving and turning
	 */
	private final double MAX_PID_DRIVE_SPEED = 0.75;
	private final double MAX_PID_TURN_SPEED = 0.75;
	
	/**
	 * PID constants for angle and distance 
	 * 
	 * DO NOT CHANGE THESE UNLESS TUNING CONTROL
	 * TEST VALUES IN SMARTDASHBOARD FIRST 
	 */
	/***********************************************/
	private double angleP = 0.50775,
				   angleI = 0.2,
				   angleD = 0.77505,
				   distP  = 0.6, 
				   distI  = 0,
				   distD  = 0.8525;
	/***********************************************/

	// Drive train motors
	private Spark driveLeft = new Spark(RobotMap.driveTrainLeftMotorPWM);
	private Spark driveRight = new Spark(RobotMap.driveTrainRightMotorPWM);
	private DifferentialDrive drive = new DifferentialDrive(driveLeft, driveRight);

	// PID
	private PIDController anglePid;
	private PIDController distancePid;
	
	private Encoder driveEncoder;
	private boolean driveTimerStarted = false;
	private Timer driveTimer = new Timer();
	
	// Gyro variables
	private ADXRS453Gyro gyro;
	private Debouncer gyroDriftDetector;
	private double curAngle;
	private double lastAngle;
	private boolean gyroCalibrating;
	private boolean lastGyroCalibrating;
	private int gyroReinits;
	private boolean angleTimerStarted = false;
	private Timer angleTimer = new Timer();
	
	/**
	 * "Fake" PIDOutput class for the PIDControllers to write to <br>
	 * So we can apply the outputs to the motors ourselves, simultaneously
	 */
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
	
	/**
	 * Initialize drive train objects, variables, and configuration
	 */
	public DriveTrain() {
		gyro = new ADXRS453Gyro();
		gyro.setPIDSourceType(PIDSourceType.kDisplacement);
		gyro.startThread();
		gyroDriftDetector = new Debouncer(1.0);
		
		driveEncoder = new Encoder(RobotMap.driveEncoderA, RobotMap.driveEncoderB, true, Encoder.EncodingType.k4X);
		driveEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		driveEncoder.setDistancePerPulse(6*Math.PI/360);
		driveEncoder.setReverseDirection(true);
		
		anglePid = new PIDController(angleP, angleI, angleD, gyro, anglePidOutput);
		anglePid.setOutputRange(-MAX_PID_TURN_SPEED, MAX_PID_TURN_SPEED);
		
		distancePid = new PIDController(distP, distI, distD, driveEncoder, distPidOutput);
		distancePid.setOutputRange(-MAX_PID_DRIVE_SPEED, MAX_PID_DRIVE_SPEED);
		
		// send values to SmartDashboard to generate the input boxes
		/*SmartDashboard.putNumber("AngleP",angleP);
		SmartDashboard.putNumber("AngleI", angleI);
		SmartDashboard.putNumber("AngleD", angleD);
		SmartDashboard.putNumber("DistanceP", distP);
		SmartDashboard.putNumber("DistanceI", distI);
		SmartDashboard.putNumber("DistanceD", distD);*/
		
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}
	
	/**
	 * Set distance setpoint.
	 * @param dist
	 */
	public void setDistanceSetpoint(double dist) {
		Robot.logDebug("Setting dist setpoint to " + dist);
		driveEncoder.reset();
		distancePid.setSetpoint(dist);
		distancePid.enable();
		driveTimer.stop();
		driveTimer.reset();
		driveTimerStarted = false;
	}
	
	/**
	 * Set angle setpoint.
	 * @param angle
	 */
	public void setAngleSetpoint(double angle) {
		Robot.logDebug("Setting angle setpoint to " + angle);
		anglePid.setSetpoint(angle);
		anglePid.enable();
	}
	
	/**
	 * Set the setpoint of the angle PID controller to current angle
	 */
	public void setAngleToCurrent() {
		setAngleSetpoint(gyro.getAngle());
	}

	/**
	 * Drive with both PID controllers' outputs.
	 */
	public void drivePidAngleAndDist() {
		double speed = -distPidOutput.getOutput();
		double rotation = anglePidOutput.getOutput();
		SmartDashboard.putNumber("Drive Speed", speed);
		SmartDashboard.putNumber("Drive Rotation Magnitude", rotation);
		drive.arcadeDrive(speed, rotation);
	}
	
	/**
	 * Read PID values from SmartDashboard into the PID variables
	 */
	private void readPIDFromSmartDashboard() {
		if(!TUNE_PID) {
			return;
		}
		// get PID values from SmartDashboard
		// default to the defined variable values
		angleP = SmartDashboard.getNumber("AngleP", angleP);
		angleI = SmartDashboard.getNumber("AngleI", angleI);
		angleD = SmartDashboard.getNumber("AngleD", angleD);
		distP  = SmartDashboard.getNumber("DistanceP", distP);
		distI  = SmartDashboard.getNumber("DistanceI", distI);
		distD  = SmartDashboard.getNumber("DistanceD", distD);
		anglePid.setPID(angleP, angleI, angleD);
		distancePid.setPID(distP, distI, distD);
	}
	
	/**
	 * Send sensor values to SmartDashboard
	 * and retrieve PID values if in tuning mode
	 */
	public void sendDataToSmartDashboard() {
		readPIDFromSmartDashboard();
		
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
		// ensure error is within reasonable tolerance
    	boolean errorWithinTolerance = Math.abs(distancePid.getError()) < 1;
    	if(errorWithinTolerance) {
    		if(!driveTimerStarted) {
    			driveTimerStarted = true;
    			driveTimer.start();
    			Robot.logDebug("Enabling drive timer");
    		}
    		SmartDashboard.putNumber("Drive timer val", driveTimer.get());
    		if(driveTimer.get() >= 0.2) {
    			Robot.logDebug("DRIVE GOOD. Reached distance, err = " + distancePid.getError());
    			stop();
    			return true;
    		}
    		Robot.logDebug("Trying to reach distance during timer -- error is " + distancePid.getError());
    	}
    	else if(driveTimerStarted) {
    		driveTimerStarted = false;
			driveTimer.reset();
			Robot.logDebug("Disabling drive timer");
    	}
    	return false;
	}
	
	/**
	 * Check if the robot has reached the angle.
	 * @return has reached angle
	 */
	public boolean hasReachedAngle() {
		// ensure error is within reasonable tolerance
		boolean errorWithinTolerance = Math.abs(anglePid.getError()) < 0.5;
		if(errorWithinTolerance) {
			if(!angleTimerStarted) {
    			angleTimerStarted = true;
    			angleTimer.start();
    			Robot.logDebug("Enabling angle timer");
    		}
    		SmartDashboard.putNumber("Angle timer val", driveTimer.get());
    		if(angleTimer.get() >= 0.2) {
    			Robot.logDebug("ANGLE GOOD. Reached angle, err = " + anglePid.getError());
    			stop();
    			return true;
    		}
    		Robot.logDebug("Trying to reach angle during timer -- error is " + anglePid.getError());
		}
    	else if(angleTimerStarted) {
    		angleTimerStarted = false;
			angleTimer.reset();
			Robot.logDebug("Disabling angle timer");
    	}
		return false;
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
	
	// Gyro Methods
	
	/**
	 * Returns gyro's current angle
	 * @return double current angle
	 */
	public double getAngle() {
		return gyro.getAngle();
	}
	
	/**
	 * Calibrates the gyro
	 */
	public void calibrateGyro() {
		gyro.calibrate();
	}
	
	/**
	 * Auto re-inits gyro if significant drift is detected <br>
	 * Should only be called while robot is disabled
	 */
	public void autoReinitGyro() {
		curAngle = getAngle();
		gyroCalibrating = gyro.isCalibrating();

		// check if we've just finished calibrating the gyro
		if (lastGyroCalibrating && !gyroCalibrating) {
			gyroDriftDetector.reset();
			curAngle = getAngle();
			anglePid.disable(); // disables PID and resets angle setpoint
			Robot.log("Finished auto-reinit gyro");
			SmartDashboard.putString("Gyro Reinit Status", "Finished (#"+ gyroReinits + ")");
		}
		// check if gryo has drifted and is not currently calibrating
		else if (gyroDriftDetector.update(Math.abs(curAngle - lastAngle) > (0.75 / 50.0))
				&& !Robot.matchStarted && !gyroCalibrating) {
			// start calibrating gyro
			gyroReinits++;
			Robot.log("!!! Sensed gyro drift, about to auto-reinit (#"+ gyroReinits + ")");
			SmartDashboard.putString("Gyro Reinit Status", "Reinitializing (#"+ gyroReinits + ")");
			gyro.calibrate();
		}

		lastAngle = curAngle;
		lastGyroCalibrating = gyroCalibrating;
		
	}
}

