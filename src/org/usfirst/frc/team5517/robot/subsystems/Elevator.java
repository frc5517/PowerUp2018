package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Elevator Subsystem
 */

public class Elevator extends Subsystem {
	
	// Setting the speed of the miniCIM motors.
	private final double LIFT_SPEED = .55;
	private final double LOWER_SPEED = .30;
	private final double CLIMB_SPEED = 1;

	// Creating the motors.
	private Talon elevatorLeftMotor;
	private Talon elevatorRightMotor;
	
	private Encoder elevatorEncoder;
	
	
	// Initializing the motors.
	public Elevator() {
		elevatorLeftMotor = new Talon(RobotMap.elevatorLeftMotorPWMPort);
		elevatorRightMotor = new Talon(RobotMap.elevatorRightMotorPWMPort);
		elevatorEncoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB);
		elevatorEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
	}

    public void initDefaultCommand() {
    }
    
    public void printEncoderVal() {
    	System.out.println("ELEVATOR ENCODER: " + elevatorEncoder.getRaw());
    }
    
    // Making the left and right motors go in the same direction to lift the elevator.
    public void raise() {
    	elevatorLeftMotor.set(-LIFT_SPEED);
    	elevatorRightMotor.set(LIFT_SPEED);
    }
    
    // Making the left and right motors go in the same direction to lower the elevator.
    public void lower() {
    	elevatorLeftMotor.set(LOWER_SPEED);
    	elevatorRightMotor.set(-LOWER_SPEED);
    }
    
    // Stopping both motors.
    public void stop() {
    	elevatorLeftMotor.set(0);
    	elevatorRightMotor.set(0);
    }
    
    public void climb(double y) {
    	elevatorLeftMotor.set(CLIMB_SPEED);
    	elevatorRightMotor.set(-CLIMB_SPEED);
    }
}