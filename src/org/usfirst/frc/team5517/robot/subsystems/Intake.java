package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	// Setting the speed of the Bag motors.
    private final double INTAKE_IN_SPEED = 0.5;
    private final double INTAKE_OUT_SPEED = 0.5;
    
    // Creating the intake motors and solenoid.
    private Talon intakeLeftMotor;
    private Talon intakeRightMotor;
    private DoubleSolenoid solenoid;
    
    // Initializing the motors and solenoid.
    public Intake() {
    	intakeLeftMotor = new Talon(RobotMap.intakeLeftMotorPWMPort);
    	intakeRightMotor = new Talon(RobotMap.intakeRightMotorPWMPort);
    	solenoid = new DoubleSolenoid(1, 2);
    }
    

    public void initDefaultCommand() {
    }
    
    // Making the left and right motors spin in opposite directions to pull the Power Cube in.
    public void intakeIn() {
    	intakeLeftMotor.set(INTAKE_IN_SPEED);
    	intakeRightMotor.set(-INTAKE_IN_SPEED);
    }
    
    // Making the left and right motors spin in opposite directions to push the Power Cube out.
    public void intakeOut() {
    	intakeLeftMotor.set(-INTAKE_OUT_SPEED);
    	intakeRightMotor.set(INTAKE_OUT_SPEED);
    }
    
    // Stopping both intake motors.
    public void stopIntake() {
    	intakeLeftMotor.set(0);
    	intakeRightMotor.set(0);
    }
    
    // Making the solenoid lift the intake arms.
    public void liftIntake() {
    	solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    // Making the solenoid lower the intake arms.
    public void lowerIntake() {
    	solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    // Stopping the solenoid after lifting/lowering the intake arms.
    public void stopIntakeLift() {
    	solenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    // Making the solenoid release the Power Cube.
    public void intakeRelease() {
    	solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    // Making the solenoid lift the Power Cube to ensure that we don't lose it.
    public void intakePinch() {
    	solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    // Stopping the solenoid after pinching/releasing the Power Cube.
    public void stopIntakePinch() {
    	solenoid.set(DoubleSolenoid.Value.kOff);
    }
}