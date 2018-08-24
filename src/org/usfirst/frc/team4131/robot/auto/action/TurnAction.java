package org.usfirst.frc.team4131.robot.auto.action;

import org.usfirst.frc.team4131.robot.Oi;
import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.ctl.TurnCtl;
import org.usfirst.frc.team4131.robot.subsystem.DriveBaseSubsystem;

/**
 * A turn action causes the robot to rotate until the given
 * arguments have been reached.
 */
public class TurnAction implements Action {
    /** The drive base */
    private final DriveBaseSubsystem driveBase;
    /** The value to turn */

    /**
     * Creates a new turning action that moves a specified
     * relative delta value.
     *
     * @param driveBase the drive base
     * @param delta the delta angle to turn
     */
    public TurnAction(DriveBaseSubsystem driveBase, float delta) {
        this.driveBase = driveBase;
    }

    @Override
    public void doAction() {
    	this.driveBase.doThrottle(0, 0);
        TurnCtl controller = TurnCtl.getInstance();
        
    }
}