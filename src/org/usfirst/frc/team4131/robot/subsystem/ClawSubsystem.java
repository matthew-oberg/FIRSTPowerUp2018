package org.usfirst.frc.team4131.robot.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
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

	private boolean isArmToggled = false;
	private boolean isPusherToggled = false;


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
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ClawCommand(this));
	}

	//closes the claw
	public void clamp() {
		this.clawOne.set(true);
		this.clawTwo.set(false);
	}
	//opens the claw
	public void release() {
		this.clawOne.set(false);
		this.clawTwo.set(true);
	}


	//actuates the pusher out
	public void pusherOut() {
		this.pusherOne.set(false);
		this.pusherTwo.set(true);
	}
	//retracts the pusher
	public void pusherIn() {
		this.pusherOne.set(true);
		this.pusherTwo.set(false);
	}


	//toggles if the arm is up or down
	public void doArm() {
		if (!isArmToggled) {
			armUp();
			isArmToggled = true;
		} else {
			armDown();
			isArmToggled = false;
		}
	}
	//actuates arm out
	public void armUp() {
		this.armOne.set(true);
		this.armTwo.set(false);
	}
	//retracts arm
	public void armDown() {
		this.armOne.set(false);
		this.armTwo.set(true);
	}

	public void autonRelease() {
		for (int i = 0; i < 1500; ++i) {
			this.clawOne.set(false);
			this.clawTwo.set(true);
		}
	}

	public void autonPusherOut() {
		for (int i = 0; i < 500; ++i) {
			this.pusherOne.set(false);
			this.pusherTwo.set(true);
		}
	}

}