package org.usfirst.frc.team5517.robot.commands;

import org.usfirst.frc.team5517.robot.Robot;

// import edu.wpi.first.wpilibj.Spark;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Single stick Arcade drive
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrainSubsystem);
        
       /* Spark m_frontLeft = new Spark(1);
    	Spark m_backLeft = new Spark(2);
    	SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_backLeft);
    	
    	Spark m_frontRight = new Spark(3);
    	Spark m_backRight = new Spark(4);
    	SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_backRight);
    	
    	DifferentialDrive drive = new DifferentialDrive(m_left, m_right); */
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSubsystem.arcadeDrive(Robot.oi.getLeftJoystickY(), Robot.oi.getRightJoystickX());
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
    	end();
    }
}
