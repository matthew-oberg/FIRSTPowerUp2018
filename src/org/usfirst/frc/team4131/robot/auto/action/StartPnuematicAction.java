package org.usfirst.frc.team4131.robot.auto.action;

import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.subsystem.ClawSubsystem;

import edu.wpi.first.wpilibj.RobotState;

/*
 * Start of auton pneumatics actions
 * Clamp cube
 * Lower arm
 */
public class StartPnuematicAction implements Action{

	ClawSubsystem claw;

	public StartPnuematicAction(ClawSubsystem claw) {
		this.claw = claw;
	}
	
	@Override
	public void doAction() {
		if (RobotState.isOperatorControl()) {
			return;
		} else {
			claw.armUp();
			claw.clamp();
		}
	}
}