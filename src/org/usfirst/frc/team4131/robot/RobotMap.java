/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4131.robot;

/**
 * Constant mapping for the ports and channels used on the
 * robot to identify gears and controllers.
 */
public final class RobotMap {
    public static final int L_JOY_PORT = 1;
    public static final int R_JOY_PORT = 0;

    // Left gears
    public static final int L1 = 1;
    public static final int L2 = 2;

    // Right gears
    public static final int R1 = 3;
    public static final int R2 = 4;

    private RobotMap() { // Prevent instantiation
    }
}