package org.usfirst.frc.team4131.robot.command;

import org.usfirst.frc.team4131.robot.Oi;
import org.usfirst.frc.team4131.robot.Robot;
import org.usfirst.frc.team4131.robot.subsystem.ElevatorSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * A command used to raise or lower the crate held in the
 * claw using the elevator subsystem.
 */
//TODO test when elevator is built
public class ElevatorCommand extends SingleSubsystemCmd<ElevatorSubsystem> {
    public static boolean isElevatorTop;
    public static boolean isElevatorBottom;
    public ElevatorCommand(ElevatorSubsystem subsystem) {
        super(subsystem);
    }

    /**
     * Checks the claw elevator button in order to determine
     * if the claw should be raised.
     *
     * @return {@code true} to signal that the claw should
     * be raised
     */
    private static boolean elevatorUpButton() {
        return Oi.ELEVATORUP.get();
    }

    /**
     * Checks the claw elevator button in order to determine
     * if the claw should be lowered.
     *
     * @return {@code true} to signal that the claw should
     * be lowered
     */
    private static boolean elevatorDownButton() {
        return Oi.ELEVATORDOWN.get();
    }

    @Override
    protected void execute() {
    	isElevatorTop = !Robot.topElevatorSwitch.get();
    	isElevatorBottom = !Robot.bottomElevatorSwitch.get();
    	if (elevatorUpButton() && elevatorDownButton()) {
            this.subsystem.stop();
        } else if (elevatorUpButton()) {
            if (isElevatorTop) {
            	
                this.subsystem.stop();
            } else {
                this.subsystem.raise();
                
            }
        } else if (elevatorDownButton()) {
            if (isElevatorBottom) {
                this.subsystem.noLower();
                
            } else {
                this.subsystem.lower();
                
            }
        } else if(elevatorTopButton()) {
        	this.subsystem.goToTop();
        } else if(elevatorBottomButton()) {
        	this.subsystem.goToBottom();
        } else if(elevatorTopButton() && elevatorBottomButton()) {
        	this.subsystem.noLower();
        
        } else if (!elevatorDownButton() && !elevatorUpButton() ) {
        	this.subsystem.noLower();
        }
    }

    private boolean elevatorBottomButton() {
		return Oi.ELEVATORBOTTOM.get();
	}

	private boolean elevatorTopButton() {
		return Oi.ELEVATORTOP.get();
	}

	@Override
    protected void interrupted() {
        this.subsystem.stop();
    }
}