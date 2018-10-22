package org.usfirst.frc.team4131.robot.auto.procedure;

import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.auto.Procedure;
import org.usfirst.frc.team4131.robot.auto.Side;
import org.usfirst.frc.team4131.robot.auto.action.DistanceMoveAction;
import org.usfirst.frc.team4131.robot.auto.action.RaiseElevatorAndClimberAction;
import org.usfirst.frc.team4131.robot.auto.action.StartPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.EndPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.TurnAction;
import org.usfirst.frc.team4131.robot.auto.action.WaitAction;
import org.usfirst.frc.team4131.robot.subsystem.SubsystemProvider;

import java.util.List;

/**
 * Autonomous procedure to run when the robot is placed
 * in front of the driver station.
 */
public class DriverStation2ToSwitch implements Procedure {
	
	@Override
	public int estimateLen() {
		return 5;
	}

	@Override
	public void populate(SubsystemProvider provider, List<Side> data, List<Action> procedure) {
		procedure.add(new WaitAction(provider.getTimer(), 1));

		//start at 180
		if (data.get(0) == Side.RIGHT) {
			procedure.add(new StartPnuematicAction(provider.getClaw()));
			//procedure.add(new WaitAction(provider.getTimer(), 1));
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 44));
			procedure.add(new TurnAction(provider.getDriveBase(), 90));
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 40));
			procedure.add(new TurnAction(provider.getDriveBase(), -90));
			//procedure.add(new WaitAction(provider.getTimer(), 0.5));
			
			procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), false, true));
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 62));
			procedure.add(new EndPnuematicAction(provider.getClaw()));
			procedure.add(new WaitAction(provider.getTimer(), 1));
		} else if (data.get(0) == Side.LEFT){
			procedure.add(new StartPnuematicAction(provider.getClaw()));
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 44));
			procedure.add(new TurnAction(provider.getDriveBase(), -90));
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 85));
			procedure.add(new TurnAction(provider.getDriveBase(), 90));
			
			procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), false, true));
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 62));
			procedure.add(new EndPnuematicAction(provider.getClaw()));
			procedure.add(new WaitAction(provider.getTimer(), 1));
		} else {
			DriverStation.reportError("Bad FMS data", true);
			System.err.println("Bad FMS data");
		}
	}
}