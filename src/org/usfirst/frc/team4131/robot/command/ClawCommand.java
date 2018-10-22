package org.usfirst.frc.team4131.robot.command;

import org.usfirst.frc.team4131.robot.Oi;
import org.usfirst.frc.team4131.robot.subsystem.ClawSubsystem;

/**
 * command for all pnuematics (claw, arm, & pusher)
 */
public class ClawCommand extends SingleSubsystemCmd<ClawSubsystem> {

	private boolean isArmUp = true;
	private boolean isArmToggled = true;
	
	public ClawCommand(ClawSubsystem subsystem) {
		super(subsystem);
	}


	private static boolean buttonClaw() {
		return Oi.SECONDARY_XBOX.getAButton();
	}

	private static boolean buttonArm() {
		return Oi.SECONDARY_XBOX.getXButton();
	}

	private static boolean buttonArm2() {
		return Oi.SECONDARY_XBOX.getBButton();
	}

	private static boolean buttonPusher() {
		return Oi.SECONDARY_XBOX.getYButton();
	}
	
	private static boolean wheelIntake() {
		if (Oi.SECONDARY_XBOX.getRawAxis(5) > 0.2)
			return true;
		return false;
	}
	
	private static boolean wheelEject() {
		if (Oi.SECONDARY_XBOX.getRawAxis(5) < -0.2)
			return true;
		return false;
	}

	@Override
	protected void execute() {
		//CLAW STUFF
		if (buttonClaw()) {
			this.subsystem.release();
		}
		else {this.subsystem.clamp();}

		//ARM STUFF
		if (buttonArm()) {
			if (isArmUp && isArmToggled) {

				for (int i = 0; i < 500; i++)
					this.subsystem.armDown();
				for (int i = 0; i < 200; i++)
					this.subsystem.release();

				this.subsystem.pusherOut();

				isArmUp = false;
				isArmToggled = false;
			}
			else if (!isArmToggled && !isArmUp) {
				for (int i = 0; i < 200; i++) {
					this.subsystem.release();

					this.subsystem.pusherOut();
				}
			}
		}

		if (buttonArm2()) {
			if (isArmUp) {

				for (int i = 0; i < 500; i++)
					this.subsystem.armDown();

				isArmUp = false;
				isArmToggled = false;
			} else {
				for (int i = 0; i < 500; i++)
					this.subsystem.armUp();

				isArmUp = true;
				isArmToggled = true;
			}
			/*else if (!isArmToggled && !isArmUp) {
				for (int i = 0; i < 500; i++)
				this.subsystem.armUp();

				isArmUp = true;
				isArmToggled = true;
			}
		}
		*/
		}

		if (buttonPusher()) {
			this.subsystem.pusherOut();
		}
		else {this.subsystem.pusherIn();}
		
		//Wheels
		if (wheelIntake()) {
			this.subsystem.wheelIntake();
		} else {
			this.subsystem.wheelStop();
		}
		if (wheelEject()) {
			this.subsystem.wheelEject();
		} else {
			this.subsystem.wheelStop();
		}
	}

	@Override
	protected void interrupted() {
		this.subsystem.clamp();
		this.subsystem.pusherIn();
	}
}