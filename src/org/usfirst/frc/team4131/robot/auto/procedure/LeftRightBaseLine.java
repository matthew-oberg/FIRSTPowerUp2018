package org.usfirst.frc.team4131.robot.auto.procedure;

import org.usfirst.frc.team4131.robot.Robot;
import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.auto.Procedure;
import org.usfirst.frc.team4131.robot.auto.Side;
import org.usfirst.frc.team4131.robot.auto.action.DistanceMoveAction;
import org.usfirst.frc.team4131.robot.auto.action.StartPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.WaitAction;
import org.usfirst.frc.team4131.robot.subsystem.SubsystemProvider;

import edu.wpi.first.wpilibj.Timer;

import java.util.List;

/**
 * Autonomous procedure to run when the robot is placed
 * in front of the driver station.
 */
public class LeftRightBaseLine implements Procedure {

	private int bearMetal = 0;
	
    @Override
    public void populate(SubsystemProvider provider, List<Side> data, List<Action> procedure) {
    	if (Robot.isBearMetal) {
    		bearMetal = 9;
    	}
    		procedure.add(new WaitAction(provider.getTimer(), bearMetal));
    		procedure.add(new StartPnuematicAction(provider.getClaw()));
            procedure.add(new DistanceMoveAction(provider.getDriveBase(), 144));   
    }
}
