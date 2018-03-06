package org.usfirst.frc.team5517.robot.subsystems;

import org.usfirst.frc.team5517.robot.Robot;
import org.usfirst.frc.team5517.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Elevator Subsystem
 */

public class Elevator extends Subsystem {
	
	// Setting the speed of the miniCIM motors.
	private final double LIFT_SPEED = .55;
	private final double LOWER_SPEED = .30;
	private final double CLIMB_SPEED = 1;
	private final double MAX_PID_ELEVATOR_SPEED = 1;
	
	private final double maxHeight = 86;
	
	/***********************************************/
    private double elevatorP  = 0.1, 
                   elevatorI  = 0,
                   elevatorD  = 0.032066348925;
	/***********************************************/

	// Creating the motors.
	private Talon elevatorLeftMotor;
	private Talon elevatorRightMotor;
	
	private PIDController elevatorPid;
	
	private Encoder elevatorEncoder;
	private boolean elevatorTimerStarted = false;
	private Timer elevatorTimer = new Timer();
	
	/**
	 * "Fake" PIDOutput class for the PIDControllers to write to <br>
	 * So we can apply the outputs to the motors ourselves, simultaneously
	 */
	class PIDOutputHandler implements PIDOutput {
		private double output = 0;
		public double getOutput() {
			return output;
		}
		@Override
		public void pidWrite(double output) {
			this.output = output;
		}
	}
	
	private PIDOutputHandler elevatorPidOutput = new PIDOutputHandler();
	
	// Initializing the motors.
	public Elevator() {
		elevatorLeftMotor = new Talon(RobotMap.elevatorLeftMotorPWMPort);
		elevatorRightMotor = new Talon(RobotMap.elevatorRightMotorPWMPort);
		
		elevatorEncoder = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB);
		elevatorEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		elevatorEncoder.setDistancePerPulse(0.1659529);
		elevatorEncoder.setReverseDirection(true);
		
		elevatorPid = new PIDController(elevatorP, elevatorI, elevatorD, elevatorEncoder, elevatorPidOutput);
		elevatorPid.setOutputRange(-.6, MAX_PID_ELEVATOR_SPEED);
	}
	
	public void initDefaultCommand() {
	}

	public void setElevatorSetpoint(double dist) {
		if(dist > maxHeight) {
			dist = maxHeight;
		}
		Robot.logDebug("Setting elevator setpoint to " + dist);
		elevatorPid.setSetpoint(dist);
		elevatorPid.enable();
		elevatorTimer.stop();
		elevatorTimer.reset();
		elevatorTimerStarted = false;
	}
	
	public boolean hasReachedDistance() {
		// ensure error is within reasonable tolerance
		boolean errorWithinTolerance = Math.abs(elevatorPid.getError()) < 15;
		if(errorWithinTolerance) {
			if(!elevatorTimerStarted) {
				elevatorTimerStarted = true;
				elevatorTimer.start();
				Robot.logDebug("Enabling elevator timer");
			}
			SmartDashboard.putNumber("Elevator timer val", elevatorTimer.get());
			if(elevatorTimer.get() >= 0.0225) {
				Robot.logDebug("ELEVATOR GOOD. Reached distance, err = " + elevatorPid.getError());
				stop();
				return true;
			}
			Robot.logDebug("Trying to reach distance during timer -- error is " + elevatorPid.getError());
		}
		else if(elevatorTimerStarted) {
			elevatorTimerStarted = false;
			elevatorTimer.reset();
			Robot.logDebug("Disabling elevator timer");
		}
		return false;
	}
	

	public void pidSetElevatorHeight() {
		double speed = elevatorPidOutput.getOutput();
		elevatorLeftMotor.set(-speed);
		elevatorRightMotor.set(speed);
		System.out.println("e encoder" + elevatorEncoder.getDistance());
		System.out.println("pid out: " + speed);
		System.out.println("error " + elevatorPid.getError());
	}
	
	public double getEncoderDistance() {
		return elevatorEncoder.getDistance();
	}
    
    public void printEncoderVal() {
    	System.out.println("ELEVATOR ENCODER: " + elevatorEncoder.getRaw());
    }
    
    // Making the left and right motors go in the same direction to lift the elevator.
    public void raise() {
    	elevatorLeftMotor.set(-LIFT_SPEED);
    	elevatorRightMotor.set(LIFT_SPEED);
    }
    
    // Making the left and right motors go in the same direction to lower the elevator.
    public void lower() {
    	elevatorLeftMotor.set(LOWER_SPEED);
    	elevatorRightMotor.set(-LOWER_SPEED);
    }
    
    // Stopping both motors.
    public void stop() {
    	elevatorPid.disable();
    	elevatorLeftMotor.set(0);
    	elevatorRightMotor.set(0);
    }
    
    // Same as lower command but faster.
    public void climb() {
    	elevatorLeftMotor.set(CLIMB_SPEED);
    	elevatorRightMotor.set(-CLIMB_SPEED);
    }
}
