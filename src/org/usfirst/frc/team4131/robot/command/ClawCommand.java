package org.usfirst.frc.team4131.robot.command;

import org.usfirst.frc.team4131.robot.Oi;
import org.usfirst.frc.team4131.robot.subsystem.ClawSubsystem;

/**
 * command for all pnuematics (claw, arm, & pusher)
 */
public class ClawCommand extends SingleSubsystemCmd<ClawSubsystem> {

	private boolean isArmPressed = false;
	//private boolean isPusherToggled = false;
	private boolean clawOpen = false;
	public ClawCommand(ClawSubsystem subsystem) {
		super(subsystem);
	}


	private static boolean buttonClaw() {
		return Oi.CLAW.get();
	}

	private static boolean buttonArm() {
		return Oi.ARM.get();
	}

	private static boolean buttonPusher() {
		return Oi.PUSHER.get();
	}

	@Override
	protected void execute() {

		//CLAW STUFF

		if (buttonClaw()) {
			this.subsystem.release();
		}
		else {this.subsystem.clamp();}

		//hold the button, actuate out, wait until button release, then actuate closed
		
		//PUSHER STUFF
		if (buttonPusher()) {
			this.subsystem.pusherOut();

		}
		else {this.subsystem.pusherIn();}


		//ARM STUFF
		if (buttonArm()) {
			if (!isArmPressed) {
				this.subsystem.doArm();
				isArmPressed = true;
			}
		}
		else {
			isArmPressed = false;
		}
	}

	@Override
	protected void interrupted() {
		this.subsystem.clamp();
		this.subsystem.pusherIn();
	}
}