package org.usfirst.frc.team4131.robot.auto.action;

import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.subsystem.ClawSubsystem;
import org.usfirst.frc.team4131.robot.subsystem.ClimberSubsystem;
import org.usfirst.frc.team4131.robot.subsystem.ElevatorSubsystem;

public class ElevatorAndClimberAction implements Action{

	ClimberSubsystem climber = new ClimberSubsystem();
	ElevatorSubsystem elevator = new ElevatorSubsystem();
	ClawSubsystem claw = new ClawSubsystem();
	
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}

	public void elevator() {
		
		elevator.goToTop();
	}
	
	public void climber() {
		
		climber.goToTop();
	}
	
	public void release(int time) {
		for (int i = 0; i < time; i++) {
			claw.release();
		}
	}
	
	public void raise(int time) {
		for (int i = 0; i < time; i++) {
			claw.raise();
		}
	}
}
