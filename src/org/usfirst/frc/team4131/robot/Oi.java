/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4131.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * Mappings of physical control devices (i.e. joysticks,
 * buttons, etc.) to representations useful for commands to
 * poll their state.
 */
public final class Oi {
    
    // Controllers
    public static final XboxController PRIMARY_XBOX = new XboxController(RobotMap.PRIMARY_XBOX_PORT);
    public static final XboxController SECONDARY_XBOX = new XboxController(RobotMap.SECONDARY_XBOX_PORT);

    private Oi() { // Prevent instantiation
    }

    /**
     * Obtains the direction sign for the left side motors.
     *
     * @return the sign on input/output on the left side
     * motors
     */
    public static int sigl() {
        return -1;
    }

    /**
     * Obtains the direction sign for the right side motors.
     *
     * @return the sign on the input/output on the right
     * side motors
     */
    public static int sigr() {
        return 1;
    }
}