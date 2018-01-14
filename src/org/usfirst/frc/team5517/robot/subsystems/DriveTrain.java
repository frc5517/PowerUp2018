package org.usfirst.frc.team5517.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5517.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;

/**
 * Drive Train subsystem
 */
public abstract class DriveTrain extends Subsystem {

     Spark driveLeft1 = new Spark(RobotMap.driveTrainLeftMotor1PWM);
	 Spark driveLeft2 = new Spark(RobotMap.driveTrainLeftMotor2PWM);
	 Spark driveRight1 = new Spark(RobotMap.driveTrainRightMotor1PWM);
	 Spark driveRight2 = new Spark(RobotMap.driveTrainRightMotor2PWM);
 
    /**
     * Arcade drive
     * @param moveValue
     * @param rotateValue
     */
    public void arcadeDrive(double moveValue, double rotateValue) {
    	arcadeDrive(moveValue, rotateValue);
    }
    
    /**
     * Set max motor speed
     */
    public void DifferentialDrive(double speed) {
    	DifferentialDrive(speed);
    }
    
    /*
     * Stop robot movement
     */
    public void stop() {
    	arcadeDrive(0,0);
    }
}

