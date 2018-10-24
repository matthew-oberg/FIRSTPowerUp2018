package org.usfirst.frc.team4131.robot.command;

import org.usfirst.frc.team4131.robot.Oi;
import org.usfirst.frc.team4131.robot.subsystem.DriveBaseSubsystem;

/**
 * A command that allows joystick control of the drive base
 */
public class MoveCommand extends SingleSubsystemCmd<DriveBaseSubsystem> {

	/**
     * Creates a new movement command for the given drive
     * base subsystem.
     *
     * @param subsystem the subsystem for which the drive
     * commands will be passed
     */
    public MoveCommand(DriveBaseSubsystem subsystem) {
        super(subsystem);
    }

    /**
     * Obtains the left joystick throttle position
     *
     * @return a number between -1 and 1 depending on if
     * the controls have been inverted and on the position
     * of the joystick
     */
    private static double getLeft() {
        if (Oi.PRIMARY_XBOX.getRawAxis(1) < 0.1 && Oi.PRIMARY_XBOX.getRawAxis(1) > -0.1) {
        	return 0;
        } else {
        	return Oi.PRIMARY_XBOX.getRawAxis(1);
        }
    }

    /**
     * Obtains the right joystick throttle position
     *
     * @return a number between -1 and 1 depending on if
     * the controls have been inverted and on the position
     * of the joystick
     */
    private static double getRight() {
    	if (Oi.PRIMARY_XBOX.getRawAxis(4) < 0.1 && Oi.PRIMARY_XBOX.getRawAxis(4) > -0.1) {
        	return 0;
        } else {
        	return -Oi.PRIMARY_XBOX.getRawAxis(4);
        }
    }

    @Override
    protected void execute() {
        this.subsystem.doThrottle(getLeft() + getRight(), -(getRight() - getLeft()));
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        this.subsystem.doThrottle(0, 0);
    }
}