package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    private final double INTAKE_IN_SPEED = 0.5;
    private final double INTAKE_OUT_SPEED = 0.5;
    
    private Talon intakeLeftMotor;
    private Talon intakeRightMotor;
    private DoubleSolenoid solenoid;
    
    public Intake() {
    	intakeLeftMotor = new Talon(RobotMap.intakeLeftMotorPWMPort);
    	intakeRightMotor = new Talon(RobotMap.intakeRightMotorPWMPort);
    	solenoid = new DoubleSolenoid(1, 2);
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void intakeIn() {
    	intakeLeftMotor.set(INTAKE_IN_SPEED);
    	intakeRightMotor.set(-INTAKE_IN_SPEED);
    }
    
    public void intakeOut() {
    	intakeLeftMotor.set(-INTAKE_OUT_SPEED);
    	intakeRightMotor.set(INTAKE_OUT_SPEED);
    }
    
    public void stopIntake() {
    	intakeLeftMotor.set(0);
    	intakeRightMotor.set(0);
    }
    
    public void liftIntake() {
    	solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void lowerIntake() {
    	solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void stopIntakeLift() {
    	solenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    public void intakeRelease() {
    	solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void intakePinch() {
    	solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void stopIntakePinch() {
    	solenoid.set(DoubleSolenoid.Value.kOff);
    }
}