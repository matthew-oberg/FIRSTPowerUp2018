package org.usfirst.frc.team4131.robot.auto.action;

import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.subsystem.TimerSubsystem;

import edu.wpi.first.wpilibj.RobotState;

public class WaitAction implements Action{

	TimerSubsystem timer;
	double time = 0.0;
	public WaitAction(TimerSubsystem timer, double time) {		
		this.timer = timer;
		this.time = time;
	}
	
	@Override
	public void doAction() {
		if (RobotState.isOperatorControl()) {
			return;
		} else {
			System.err.println("Delaying for " + time + " seconds!");
			this.timer.delay(time);
		}
	}
}