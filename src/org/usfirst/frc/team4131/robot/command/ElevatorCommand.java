package org.usfirst.frc.team4131.robot.command;

import org.usfirst.frc.team4131.robot.Oi;
import org.usfirst.frc.team4131.robot.Robot;
import org.usfirst.frc.team4131.robot.subsystem.ElevatorSubsystem;

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
        if (Oi.SECONDARY_XBOX.getRawAxis(1) < -0.05) {
        	return true;
        } else {
        	return false;
        }
    }

    /**
     * Checks the claw elevator button in order to determine
     * if the claw should be lowered.
     *
     * @return {@code true} to signal that the claw should
     * be lowered
     */
    private static boolean elevatorDownButton() {
    	if (Oi.SECONDARY_XBOX.getRawAxis(1) > 0.05) {
        	return true;
        } else {
        	return false;
        }    
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
        } else {
        	this.subsystem.noLower();
        }
    }

	@Override
    protected void interrupted() {
        this.subsystem.stop();
    }
}