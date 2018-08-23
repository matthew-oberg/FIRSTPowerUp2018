package org.usfirst.frc.team4131.robot.auto.action;

import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.subsystem.ClawSubsystem;

import edu.wpi.first.wpilibj.Timer;

/*
 * Cube despense actions, end of auton
 * Release cube
 * Eject Cube
 */
public class EndPnuematicAction implements Action{

	ClawSubsystem claw;

	public EndPnuematicAction(ClawSubsystem claw) {
		this.claw = claw;
	}
	
	@Override
	public void doAction() {
		for(int i=0;i <= 250; i++) {
		claw.armDown();
		}
		for(int i=0;i <= 300; i++)
		claw.release();
		
		claw.pusherOut();
		Timer.delay(1);
		claw.armUp();
		
	}
}