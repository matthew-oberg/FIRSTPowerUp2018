package org.usfirst.frc.team4131.robot.auto.procedure;

import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.auto.Procedure;
import org.usfirst.frc.team4131.robot.auto.Side;
import org.usfirst.frc.team4131.robot.auto.action.DistanceMoveAction;
import org.usfirst.frc.team4131.robot.auto.action.RaiseElevatorAndClimberAction;
import org.usfirst.frc.team4131.robot.auto.action.StartPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.EndPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.TurnAction;
import org.usfirst.frc.team4131.robot.auto.action.WaitAction;
import org.usfirst.frc.team4131.robot.auto.action.TurnHalfSpeedAction;
import org.usfirst.frc.team4131.robot.subsystem.SubsystemProvider;

import edu.wpi.first.wpilibj.Timer;

import java.util.List;

/**
 * Autonomous procedure to run when the robot is placed on
 * to the left.
 */
public class LeftToSwitchOrScale implements Procedure {
			
    @Override
    public int estimateLen() {
        return 5;
    }

    @Override
    public void populate(SubsystemProvider provider, List<Side> data, List<Action> procedure) {
    	//if switch is left
        if (data.get(0) == Side.LEFT) {
            procedure.add(new StartPnuematicAction(provider.getClaw()));    	
            //drive straight 168 inches (until level with center of the switch
            procedure.add(new DistanceMoveAction(provider.getDriveBase(), 130));
            //turn right 90
            procedure.add(new TurnAction(provider.getDriveBase(), 90));
            //.add(new WaitAction(provider.getTimer(), 1));
            // drive straight until against switch
            procedure.add(new DistanceMoveAction(provider.getDriveBase(), 5.9));
            //procedure.add(new WaitAction(provider.getTimer(), 1));
            procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), false, true));
            //procedure.add(new WaitAction(provider.getTimer(), 4));
          //if switch is left
            procedure.add(new EndPnuematicAction(provider.getClaw()));
          //if the switch is right but the scale is left
            procedure.add(new WaitAction(provider.getTimer(), 1));
        } else if (data.get(0) == Side.RIGHT && data.get(1) == Side.LEFT) {
            procedure.add(new WaitAction(provider.getTimer(), 1));
           // procedure.add(new WaitAction(provider.getTimer(), 1));
            //drive until level with the scale
            procedure.add(new DistanceMoveAction(provider.getDriveBase(), 144));
            //procedure.add(new WaitAction(provider.getTimer(), 1));
            //turn right 90
            /*procedure.add(new TurnAction(provider.getDriveBase(), 90));
            //procedure.add(new WaitAction(provider.getTimer(), 1));
            procedure.add(new DistanceMoveAction(provider.getDriveBase(), -10));
           // procedure.add(new WaitAction(provider.getTimer(), 1));
            procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), true, true));
            //procedure.add(new DistanceMoveAction(provider.getDriveBase(), 10));
            //procedure.add(new WaitAction(provider.getTimer(), 1));
            procedure.add(new EndPnuematicAction(provider.getClaw()));
          //if both are right (either drive straight or go to center)
            procedure.add(new WaitAction(provider.getTimer(), 1));
            procedure.add(new StartPnuematicAction(provider.getClaw()));
            procedure.add(new TurnAction(provider.getDriveBase(), -90));*/
        } else if (data.get(0) == Side.RIGHT && data.get(1) == Side.RIGHT) {
            procedure.add(new StartPnuematicAction(provider.getClaw()));
            //Currently runs same as LRBaseline
            procedure.add(new WaitAction(provider.getTimer(), 1));
            procedure.add(new DistanceMoveAction(provider.getDriveBase(), 144));
            procedure.add(new WaitAction(provider.getTimer(), 1));
        } else {
            System.err.println("Bad FMS data!");
        }
    }
}