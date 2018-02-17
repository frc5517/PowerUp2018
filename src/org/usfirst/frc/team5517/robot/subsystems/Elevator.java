package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Elevator Subsystem
 */

public class Elevator extends Subsystem {
	
	// Setting the speed of the miniCIM motors.
	private final double LIFT_SPEED = .25;
	private final double LOWER_SPEED = .25;

	// Creating the motors.
	private Talon elevatorLeftMotor;
	private Talon elevatorRightMotor;
	
	
	// Initializing the motors.
	public Elevator() {
		elevatorLeftMotor = new Talon(RobotMap.elevatorLeftMotorPWMPort);
		elevatorRightMotor = new Talon(RobotMap.elevatorRightMotorPWMPort);
	}

    public void initDefaultCommand() {
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
}