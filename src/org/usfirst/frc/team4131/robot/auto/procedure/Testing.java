package org.usfirst.frc.team4131.robot.auto.procedure;

import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.auto.Procedure;
import org.usfirst.frc.team4131.robot.auto.Side;
import org.usfirst.frc.team4131.robot.auto.action.DistanceMoveAction;
import org.usfirst.frc.team4131.robot.auto.action.EndPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.RaiseElevatorAndClimberAction;
import org.usfirst.frc.team4131.robot.auto.action.StartPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.TurnAction;
import org.usfirst.frc.team4131.robot.auto.action.WaitAction;
import org.usfirst.frc.team4131.robot.subsystem.SubsystemProvider;

import java.util.List;

/**
 * Autonomous procedure to run when the robot is placed
 * in front of the driver station.
 */
public class Testing implements Procedure {

	@Override
	public void populate(SubsystemProvider provider, List<Side> data, List<Action> procedure) {
		System.err.println("Starting testing!");
		//procedure.add(new StartPnuematicAction(provider.getClaw()));
		//procedure.add(new DistanceMoveAction(provider.getDriveBase(), 180));
		//procedure.add(new DistanceMoveAction(provider.getDriveBase(), -180));
		//procedure.add(new DistanceMoveAction(provider.getDriveBase(), 180));
		//procedure.add(new DistanceMoveAction(provider.getDriveBase(), -180));
		//procedure.add(new DistanceMoveAction(provider.getDriveBase(), 180));
		//procedure.add(new DistanceMoveAction(provider.getDriveBase(), -180));
		//procedure.add(new TurnAction(provider.getDriveBase(), -90));
		//procedure.add(new WaitAction(provider.getTimer(), 3));
		//procedure.add(new TurnAction(provider.getDriveBase(), 90));
		procedure.add(new TurnAction(provider.getDriveBase(), 45));
		procedure.add(new TurnAction(provider.getDriveBase(), -90));
		procedure.add(new TurnAction(provider.getDriveBase(), 45));
		//procedure.add(new TurnAction(provider.getDriveBase(), 90));
		//procedure.add(new TurnAction(provider.getDriveBase(), 90));
		//procedure.add(new TurnAction(provider.getDriveBase(), 90));
		
		//procedure.add(new WaitAction(provider.getTimer(), 1));
		//procedure.add(new TurnAction(provider.getDriveBase(), 90));
		//procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), true, false));
		//procedure.add(new LowerElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), true, true));
		//procedure.add(new EndPnuematicAction(provider.getClaw()));
		//System.err.println("Finished testing!");
		//
		}
	}

