package org.usfirst.frc.team4131.robot.auto.procedure;

import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.auto.Procedure;
import org.usfirst.frc.team4131.robot.auto.Side;
import org.usfirst.frc.team4131.robot.auto.action.EndPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.RaiseElevatorAndClimberAction;
import org.usfirst.frc.team4131.robot.auto.action.StartPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.WaitAction;
import org.usfirst.frc.team4131.robot.subsystem.SubsystemProvider;

import edu.wpi.first.wpilibj.Timer;

import java.util.List;

/**
 * Autonomous procedure to run when the robot is placed
 * in front of the driver station.
 */
public class Testing implements Procedure {

    @Override
    public void populate(SubsystemProvider provider, List<Side> data, List<Action> procedure) {
    	System.err.println("Starting testing!");
    	procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), true, true));
    	//procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), true, true));
    	//System.err.println("Finished lowering!");
    	procedure.add(new WaitAction(provider.getTimer(), 4));
    	//procedure.add(new StartPnuematicAction(provider.getClaw()));
    	//procedure.add(new EndPnuematicAction(provider.getClaw()));
    	System.err.println("Finished testing!");
    }
}
