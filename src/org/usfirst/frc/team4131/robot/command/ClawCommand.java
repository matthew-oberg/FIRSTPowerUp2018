package org.usfirst.frc.team4131.robot.command;

import org.usfirst.frc.team4131.robot.Oi;
import org.usfirst.frc.team4131.robot.auto.action.EndPnuematicAction;
import org.usfirst.frc.team4131.robot.subsystem.ClawSubsystem;

import edu.wpi.first.wpilibj.Timer;

/**
 * command for all pnuematics (claw, arm, & pusher)
 */
public class ClawCommand extends SingleSubsystemCmd<ClawSubsystem> {

	private boolean isArmUp = true;
	private boolean isArmToggled = true;
	//private boolean clawOpen = false;
	public ClawCommand(ClawSubsystem subsystem) {
		super(subsystem);
	}


	private static boolean buttonClaw() {
		return Oi.CLAW.get();
	}

	private static boolean buttonArm() {
		return Oi.ARM.get();
	}

	private static boolean buttonArm2() {
		return Oi.ARM2.get();
	}
	
	private static boolean buttonPusher() {
		return Oi.PUSHER.get();
	}

	@Override
	protected void execute() {
		
		
		//
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
				//Timer.delay(seconds);
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
	}

	@Override
	protected void interrupted() {
		this.subsystem.clamp();
		this.subsystem.pusherIn();
	}
}