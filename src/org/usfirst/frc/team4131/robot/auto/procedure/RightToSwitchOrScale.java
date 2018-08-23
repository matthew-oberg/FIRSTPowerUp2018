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
//import org.usfirst.frc.team4131.robot.auto.action.TurnHalfSpeedAction;
import java.util.List;

/**
 * Autonomous procedure to run when the robot is placed to
 * the right.
 */
public class RightToSwitchOrScale implements Procedure {
	
	@Override
	public int estimateLen() {
		return 4;
	}

	@Override
	public void populate(SubsystemProvider provider, List<Side> data, List<Action> procedure) {
		if (data.get(0) == Side.RIGHT) {
            procedure.add(new StartPnuematicAction(provider.getClaw()));
            //procedure.add(new WaitAction(provider.getTimer(), 1));
			//if the switch is right
			//drive straight 168 inches (until level with center of the switch
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 168));
			//procedure.add(new WaitAction(provider.getTimer(), 1));
			//turn left 90
			procedure.add(new TurnAction(provider.getDriveBase(), -90));
			//procedure.add(new WaitAction(provider.getTimer(), 1));
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 19.5));
			//procedure.add(new WaitAction(provider.getTimer(), 1));
			procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), false, true));
			//procedure.add(new WaitAction(provider.getTimer(), 1));
			procedure.add(new EndPnuematicAction(provider.getClaw()));
			procedure.add(new WaitAction(provider.getTimer(), 1));
			
		} else if (data.get(0) == Side.LEFT && data.get(1) == Side.RIGHT) {
            //procedure.add(new WaitAction(provider.getTimer(), 1));
           // procedure.add(new WaitAction(provider.getTimer(), 1));
			//if the switch is left but the scale is right
			//drive until level with the scale
            procedure.add(new StartPnuematicAction(provider.getClaw()));
            procedure.add(new DistanceMoveAction(provider.getDriveBase(), 270));
            
			//procedure.add(new WaitAction(provider.getTimer(), 1));
			//turn left 90
            procedure.add(new TurnAction(provider.getDriveBase(), -45));
			//procedure.add(new WaitAction(provider.getTimer(), 1));
			//procedure.add(new DistanceMoveAction(provider.getDriveBase(), -10));
			procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), true, true));
			//procedure.add(new WaitAction(provider.getTimer(), 1));
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 10));
			procedure.add(new EndPnuematicAction(provider.getClaw()));
			procedure.add(new WaitAction(provider.getTimer(), 1));
            procedure.add(new StartPnuematicAction(provider.getClaw()));
            procedure.add(new TurnAction(provider.getDriveBase(), 90));
		} else if (data.get(0) == Side.LEFT && data.get(1) == Side.LEFT) {
            procedure.add(new StartPnuematicAction(provider.getClaw()));
			//if both are left (either drive straight or go to center)
			//Currently runs same as LRBaseline
            procedure.add(new WaitAction(provider.getTimer(), 1));
			procedure.add(new DistanceMoveAction(provider.getDriveBase(), 144));
			procedure.add(new WaitAction(provider.getTimer(), 1));
		} else {
			DriverStation.reportError("Bad FMS data", true);
		}
	}
}
