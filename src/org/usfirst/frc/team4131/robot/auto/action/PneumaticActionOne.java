package org.usfirst.frc.team4131.robot.auto.action;

import org.usfirst.frc.team4131.robot.Robot;
import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.subsystem.ClawSubsystem;

import edu.wpi.first.wpilibj.Timer;

public class PneumaticActionOne implements Action{


	ClawSubsystem claw;
	public PneumaticActionOne(ClawSubsystem claw) {
		this.claw = claw;
	}
	
	@Override
	public void doAction() {
		claw.clamp();
		claw.armDown();
		//Timer.delay(1);
		this.claw.release();
	}
}
