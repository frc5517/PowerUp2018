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
    
    private Talon intakeMotor;
    private DoubleSolenoid solenoid;
    
    public Intake() {
    	intakeMotor = new Talon(RobotMap.intakeMotorPWMPort);
    	solenoid = new DoubleSolenoid(1, 2);
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void intakeIn() {
    	intakeMotor.set(INTAKE_IN_SPEED);
    }
    
    public void intakeOut() {
    	intakeMotor.set(-INTAKE_OUT_SPEED);
    }
    
    public void stopIntake() {
    	intakeMotor.set(0);
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
}