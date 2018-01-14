package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 *
 */
public class DifferentialDrive extends DriveTrain {

    public DifferentialDrive() {
        // Use requires() here to declare subsystem dependencies
    	Spark m_frontLeft = new Spark(1);
    	Spark m_midLeft = new Spark(2);
    	Spark m_rearLeft = new Spark(3);
    	SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_midLeft, m_rearLeft);
    	
    	Spark m_frontRight = new Spark(4);
    	Spark m_midRight = new Spark(5);
    	Spark m_rearRight = new Spark(6);
    	SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_midRight, m_rearRight);
    	
    	DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
