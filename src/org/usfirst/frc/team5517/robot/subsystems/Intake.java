package org.usfirst.frc.team5517.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    private final double INTAKE_IN_SPEED = 0.5;
    private final double INTAKE_OUT_SPEED = 0.5;
    private final double LIFT_SPEED = 0.75;
    private final double LOWER_SPEED = 0.75;
    
    private Talon intakeMotor;
    private Talon intakeLiftMotor;
    private Talon intakeLowerMotor;
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void intakeIn() {
    	intakeMotor.set(INTAKE_IN_SPEED);
    }
    
    public void intakeOut() {
    	intakeMotor.set(INTAKE_OUT_SPEED);
    }
    
    public void liftIntake() {
    	intakeLiftMotor.set(LIFT_SPEED);
    }
    
    public void lowerIntake() {
    	intakeLowerMotor.set(-LOWER_SPEED);
    }
    
    public void stopIntakeLift() {
    	intakeLiftMotor.set(0);
    }
    
    public void stopIntake() {
    	intakeMotor.set(0);
    }
}