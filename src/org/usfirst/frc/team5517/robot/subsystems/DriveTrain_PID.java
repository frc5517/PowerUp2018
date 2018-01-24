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

	private final static double JOYSTICK_TOLERANCE = 0.2;

	SpeedControllerGroup driveLeft;
	SpeedControllerGroup driveRight;
	ADXRS453Gyro gyro;

	DifferentialDrive drive;

	//private double targetAngle = 0;
	//private double compensateValue = 0;
	//private double lastUpdatedAngleTime = 0;

	public DriveTrain_PID() {

		super("DriveTrain", 0, 0, 0);

		driveLeft = new SpeedControllerGroup(Robot.driveTrain.driveLeft);
		driveRight = new SpeedControllerGroup(Robot.driveTrain.driveRight);
		drive = new DifferentialDrive(driveLeft, driveRight);


		gyro = new ADXRS453Gyro();
		gyro.startThread();

		setAbsoluteTolerance(0.05);
		setInputRange(-180, 180);
		setOutputRange(-0.14, 0.14);
		setSetpoint(0.0);
		getPIDController().setContinuous(false);
		enable();

	}



	/**
	 * Adds a joystick "deadzone" to remove jitter
	 * @param The joystick value
	 * @return The joystick value after adding deadzone
	 */
	/*private double joystickDz(double value) {
		if(value > -JOYSTICK_TOLERANCE && value < JOYSTICK_TOLERANCE) {
			return 0;
		}
		return value;
	}*/

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

	protected double returnPIDInput() {
		return gyro.getAngle();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
	}
	
	/**
	 * Prints gyro values to console
	 */
	private void printGyroValues() {
		System.out.print("Gyro Angle: " + gyro.getAngle());
		System.out.println(", Gyro Pos: " + gyro.getPos());
		System.out.println(", Rate: " + gyro.getRate());
	}

	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void arcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(speed,rotation);
		printGyroValues();
	}
}