package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	
	private final double LIFT_SPEED = .5;
	private final double LOWER_SPEED = .5;

	private Talon elevatorLeftMotor;
	private Talon elevatorRightMotor;
	
	
	public Elevator() {
		elevatorLeftMotor = new Talon(RobotMap.elevatorLeftMotorPWMPort);
		elevatorLeftMotor = new Talon(RobotMap.elevatorRightMotorPWMPort);
	}

    public void initDefaultCommand() {
    }
    
    public void raise() {
    	elevatorLeftMotor.set(-LIFT_SPEED);
    	elevatorRightMotor.set(LIFT_SPEED);
    }
    
    public void lower() {
    	elevatorLeftMotor.set(LOWER_SPEED);
    	elevatorRightMotor.set(-LOWER_SPEED);
    }
    
    public void stop() {
    	elevatorLeftMotor.set(0);
    	elevatorRightMotor.set(0);
    }
}