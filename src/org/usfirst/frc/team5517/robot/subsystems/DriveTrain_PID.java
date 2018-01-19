package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.RobotMap;
import org.usfirst.frc.team5517.robot.commands.Drive;
import org.usfirst.frc.team5517.robot.sensors.ADXRS453Gyro;

//import edu.wpi.first.wpilibj.Spark;
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
	
	//private double targetAngle = 0;
	//private double compensateValue = 0;
	//private double lastUpdatedAngleTime = 0;

    public DriveTrain_PID() {
  
    	super("DriveTrain", 0, 0, 0);
    	
    	driveLeft = new SpeedControllerGroup(Robot.driveTrainSubsystem.driveLeft);
    	driveRight = new SpeedControllerGroup(Robot.driveTrainSubsystem.driveRight);
    	gyro = new ADXRS453Gyro();
    	gyro.startThread();
    	
    	DifferentialDrive drive = new DifferentialDrive(driveLeft, driveRight);
    	
    	setAbsoluteTolerance(0.05);
    	setInputRange(-180, 180);
    	setOutputRange(-0.14, 0.14);
    	setSetpoint(0.0);
    	getPIDController().setContinuous(false);
    	enable();
    	
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
    	
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.0;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
