package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	
	private final double LIFT_SPEED = 1;
	private final double LOWER_SPEED = 1;

	private Talon armMotor;
	
	
	public Arm() {
		armMotor = new Talon(RobotMap.armMotorPWMPort);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void raise() {
    	armMotor.set(LIFT_SPEED);
    }
    
    public void lower() {
    	armMotor.set(LOWER_SPEED);
    }
    
    public void stop() {
    	armMotor.set(0);
    }
}