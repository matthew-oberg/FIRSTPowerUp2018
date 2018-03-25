package org.usfirst.frc.team4131.robot.subsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4131.robot.Robot;
import org.usfirst.frc.team4131.robot.RobotMap;
import org.usfirst.frc.team4131.robot.command.ElevatorCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Links control of the elevator, used to raise the claw.
 */
public class ElevatorSubsystem extends Subsystem {
    private final TalonSRX motor;
    private int loopcount = 0;
    private boolean noLower = false;
    /**
     * Initializes and caches the climbing mechanism motor.
     */
    public ElevatorSubsystem() {
        this.motor = new TalonSRX(RobotMap.E);
    }

    @Override
    protected void initDefaultCommand() {
        this.setDefaultCommand(new ElevatorCommand(this));
    }

    /**
     * Raises the claw.
     */
    public void raise() {
        this.motor.set(ControlMode.PercentOutput, -0.85);
    }

    /**
     * Lowers the claw.
     */
    public void lower() {
        this.motor.set(ControlMode.PercentOutput, 0.45);
    }
    
    public void noLower() {
    	this.motor.set(ControlMode.PercentOutput, -0.2);
    }

    /**
     * Halts claw movement, but the claw may or may not move
     * depending on its position.
     */
    public void stop() {
        this.motor.set(ControlMode.PercentOutput, 0);
    }
    
    public void goToBottom() {
    	
    	while (Robot.isElevatorBottom) {
    		this.lower();
    	}
    	this.stop();
    }
    
    public void goToTop() {
    	loopcount = 0;
    	System.err.println("Elevator going to top!");
    	while (Robot.isElevatorTop) {
    		System.err.println("Elevator still in loop! loopcount = " + loopcount);
    		this.motor.set(ControlMode.PercentOutput, -1);// -1
    		loopcount++;
    		if (!Robot.isElevatorTop) {System.err.println("loop stopped by limit switch!");break;}
    		else if (loopcount > 4450) {break;}
    	}
    	this.motor.set(ControlMode.PercentOutput, 0);
    	System.out.println("DONE WITH ELEVATOR LOOP");
    }
}