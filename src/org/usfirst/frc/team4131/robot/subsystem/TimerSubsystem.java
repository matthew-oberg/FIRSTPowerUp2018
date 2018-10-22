package org.usfirst.frc.team4131.robot.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TimerSubsystem extends Subsystem{
	
	public void delay(double time) {
		Timer.delay(time);
	}

	@Override
	protected void initDefaultCommand() {}
}