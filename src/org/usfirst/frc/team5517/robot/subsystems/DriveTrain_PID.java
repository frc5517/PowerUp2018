package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.commands.ArcadeDrive;
import org.usfirst.frc.team5517.robot.sensors.ADXRS453Gyro;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain_PID extends PIDSubsystem {

	private final static double JOYSTICK_TOLERANCE = 0.1;
	private final static double ERROR_TOLERANCE = 3;
	private final static double kP = 0;
	private final static double kI = 0;
	private final static double kD = 0;
	
	private double targetAngle = 0;
	private double diff = 0;
	private double compensation = 0;
	private long lastUpdatedTargetAngleTime;

	SpeedControllerGroup driveLeft;
	SpeedControllerGroup driveRight;
	ADXRS453Gyro gyro;

	DifferentialDrive drive;


	public DriveTrain_PID() {

		super("DriveTrain", kP, kI, kD); // kP, kI, kD

		driveLeft = new SpeedControllerGroup(Robot.driveTrain.driveLeft);
		driveRight = new SpeedControllerGroup(Robot.driveTrain.driveRight);
		drive = new DifferentialDrive(driveLeft, driveRight);


		gyro = new ADXRS453Gyro();
		gyro.startThread();

		setAbsoluteTolerance(0.05);
		setInputRange(-180, 180);
		setOutputRange(-1, 1);
		setSetpoint(0.0);
		getPIDController().setContinuous(false);
		enable();
	}


	/**
	 * Adds a joystick "deadzone" to remove jitter
	 * @param The joystick value
	 * @return The joystick value after adding deadzone
	 */
	private double joystickDz(double value) {
		if(value > -JOYSTICK_TOLERANCE && value < JOYSTICK_TOLERANCE) {
			return 0;
		}
		return value;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		System.out.println("OUTPUT: "+output);
		driveLeft.set(output);
		driveRight.set(output);
	}
	
	/**
	 * Prints gyro values to console
	 */
	private void printGyroValues() {
		System.out.print("Gyro Angle: " + gyro.getAngle());
		System.out.println(", Gyro Pos: " + gyro.getPos());
		System.out.println(", Rate: " + gyro.getRate());
	}
	
	public void turnLeft(double rate) {
		drive.arcadeDrive(0, -rate);
	}
	
	public void turnRight(double rate) {
		drive.arcadeDrive(0, rate);
	}
	
	public void driveStraight(double rate) {
		drive.arcadeDrive(rate, 0);
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void arcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(speed,rotation);
		printGyroValues();
		
		speed = joystickDz(speed);
		rotation = joystickDz(rotation);
		
		drive.arcadeDrive(speed, rotation);
		double currentAngle = getHeading();
		diff = Math.IEEEremainder(targetAngle, currentAngle);
		compensation = 0;
		
		
		/**
		 * Remove jitter by adding a joystick 'deadzone'
		 */
		speed = joystickDz(speed);
		rotation = joystickDz(rotation);

		
		/**
		 *  Exponential speed and rotation, makes each value start less quickly
		 */
		speed = speed*speed*speed;
		rotation = rotation*rotation;

		
		/**
		 * If there is rotation input, update the current angle
		 */
		if(rotation != 0) {
			setTargetAngle(currentAngle);
			lastUpdatedTargetAngleTime = System.nanoTime();
		}

		
		/**
		 * If it has been some time since the angle was updated by the driver,
		 * we can compensate
		 */
		else if(500 < nanoToMilli(System.nanoTime() - lastUpdatedTargetAngleTime)) {
			System.out.print("gyro angle: " + currentAngle);
			System.out.print(", target angle: " + targetAngle);
		}
		
		
		/**
		 * Compensate if the diff is large enough
		 */
		if(diff > 3) {
			final double multiplier = 0.015;
			
			if(targetAngle > currentAngle) {
				System.out.println("compensate right");
				compensation = diff * multiplier;
			}
			else if(targetAngle < currentAngle) {
				System.out.println("compensate left");
				compensation = diff * -multiplier;
			}
			
			compensation = minAndMax(compensation, 0.1, 0.3);
		}
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
		
		/**
		 * Converts nanoseconds to milliseconds
		 */
		private long nanoToMilli(long nano) {
			return nano/1000000;
		}
		
		/**
		 * Min and max of a value 
		 */
		private double minAndMax(double value, double min, double max) {
			// positive max
			if(value > max) {
				value = max;
			}
			// negative max
			else if(value < 0 && value < -max) {
				value = -max;
			}
			// positive min
			else if(value > 0 && value < min) {
				value = min;
			}
			// negative min
			else if(value < 0 && value > -min) {
				value = min;
			}
			
			return value;
		}

}