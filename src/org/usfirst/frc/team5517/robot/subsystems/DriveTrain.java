package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Drive Train subsystem
 */
public class DriveTrain extends Subsystem {

     Spark driveLeft1 = new Spark(RobotMap.driveTrainLeftMotor1PWM);
	 Spark driveLeft2 = new Spark(RobotMap.driveTrainLeftMotor2PWM);
	 Spark driveRight1 = new Spark(RobotMap.driveTrainRightMotor1PWM);
	 Spark driveRight2 = new Spark(RobotMap.driveTrainRightMotor2PWM);
 
	 public DriveTrain() {
		 
	 }
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

   /**
    * Stop robot movement
    */
	public void stopMotor() {
		arcadeDrive(0,0);
		
	}


	@Override
	public void initSendable(SendableBuilder builder) {
		
		
	}

	
	public String getDescription() {
		return null;
	}

	protected void initDefaultCommand() {
		setDefaultCommand(Drive());
	} 
	
	private Command Drive() {
		// TODO Auto-generated method stub
		return null;
	}
	public void drive(double x, double y) {
		
	}
}

