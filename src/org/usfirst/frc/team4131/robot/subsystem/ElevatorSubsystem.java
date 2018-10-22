package org.usfirst.frc.team4131.robot.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4131.robot.Robot;
import org.usfirst.frc.team4131.robot.RobotMap;
import org.usfirst.frc.team4131.robot.command.ElevatorCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Links control of the elevator, used to raise the claw.
 */
public class ElevatorSubsystem extends Subsystem {

	private final TalonSRX motor;

	public static boolean isElevatorTop = Robot.isElevatorTop;
	public static boolean isElevatorBottom = Robot.isElevatorBottom;

	/**
	 * Initializes and caches the climbing mechanism motor.
	 */
	public ElevatorSubsystem() {
		this.motor = new TalonSRX(RobotMap.E);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ElevatorCommand(this));
	}

	/**
	 * Raises the claw.
	 */
	public void raise() {
		this.motor.set(ControlMode.PercentOutput, -0.85);

	}

	/**
	 * Lowers the claw.
	 */
	public void lower() {
		this.motor.set(ControlMode.PercentOutput, 0.45);
	}

	public void noLower() {
		//this.motor.set(ControlMode.PercentOutput, -0.15);
		this.motor.set(ControlMode.PercentOutput, 0);
	}

	/**
	 * Halts claw movement, but the claw may or may not move
	 * depending on its position.
	 */
	public void stop() {
		this.motor.set(ControlMode.PercentOutput, 0);
	}

	public void goToBottom() {
		while(shouldLower()) {
			isElevatorBottom = !Robot.bottomElevatorSwitch.get();
			if(!shouldLower()) {
				this.stop();
				break;
			}
			this.lower();
		}
	}

	public void goToTop() {
		while(shouldRaise()) {
			isElevatorTop = !Robot.topElevatorSwitch.get();
			if(!shouldRaise()) {
				this.noLower();
				break;
			}
			this.raise();
		}

	}

	private static boolean shouldRaise() {
		return !isElevatorTop;
	}

	private static boolean shouldLower() {
		return !isElevatorBottom;
	}
}