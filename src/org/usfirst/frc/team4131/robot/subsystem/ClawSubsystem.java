package org.usfirst.frc.team4131.robot.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4131.robot.RobotMap;
import org.usfirst.frc.team4131.robot.command.ClawCommand;

/**
 * Links control for the claw-related motors.
 */
public class ClawSubsystem extends Subsystem {
	
	// Pneumatic devices
	private final Solenoid clawOne;
	private final Solenoid clawTwo;
	private final Solenoid armOne;
	private final Solenoid armTwo;
	private final Solenoid pusherOne;
	private final Solenoid pusherTwo;
	
	// Wheel motors
	private final Spark wheelOne;
	private final Spark wheelTwo;

	private boolean isArmToggled = false;

	/**
	 * Initializes and caches the motor wrapper for the claw
	 * subsystem
	 */
	public ClawSubsystem() {
		this.clawOne = new Solenoid(RobotMap.PCM, RobotMap.CLAWONE);
		this.clawTwo = new Solenoid(RobotMap.PCM, RobotMap.CLAWTWO);
		this.armOne = new Solenoid(RobotMap.PCM, RobotMap.ARMONE);
		this.armTwo = new Solenoid(RobotMap.PCM, RobotMap.ARMTWO);
		this.pusherOne = new Solenoid(RobotMap.PCM, RobotMap.PUSHERONE);
		this.pusherTwo = new Solenoid(RobotMap.PCM, RobotMap.PUSHERTWO);
		
		this.wheelOne = new Spark(RobotMap.I1);
		this.wheelTwo = new Spark(RobotMap.I2);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ClawCommand(this));
	}

	//Closes the claw
	public void clamp() {
		this.clawOne.set(true);
		this.clawTwo.set(false);
	}
	
	//Opens the claw
	public void release() {
		this.clawOne.set(false);
		this.clawTwo.set(true);
	}

	//Actuates the pusher out
	public void pusherOut() {
		this.pusherOne.set(false);
		this.pusherTwo.set(true);
	}
	
	//Retracts the pusher
	public void pusherIn() {
		this.pusherOne.set(true);
		this.pusherTwo.set(false);
	}

	//Toggles if the arm is up or down
	public void doArm() {
		if (!isArmToggled) {
			armUp();
			isArmToggled = true;
		} else {
			armDown();
			isArmToggled = false;
		}
	}
	
	//Actuates arm out
	public void armUp() {
		this.armOne.set(true);
		this.armTwo.set(false);
	}
	
	//Retracts arm
	public void armDown() {
		this.armOne.set(false);
		this.armTwo.set(true);
	}
	
	public void wheelEject() {
		this.wheelOne.set(0.45);
		this.wheelTwo.set(-0.45);
	}
	
	public void wheelIntake() {
		this.wheelOne.set(-0.45);
		this.wheelTwo.set(0.45);
	}
	
	public void wheelStop() {
		this.wheelOne.stopMotor();
		this.wheelTwo.stopMotor();
	}
}