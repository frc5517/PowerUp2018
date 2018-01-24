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
		else if(500 < nanoToMilli(System.nanoTime()-lastUpdatedTargetAngleTime)) {
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
			
			/**
			 * Min/max compensation values
			 */
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
	
	/**
	 * Adds a "deadzone" to the joystick inputs to remove jitter
	 * @param joystick value
	 * @return joystick value with deadzone added
	 */
	private double joystickDz(double value) {
		if(value > -JOYSTICK_TOLERANCE && value < JOYSTICK_TOLERANCE) {
			return 0;
		}
		return value;
	}
}

