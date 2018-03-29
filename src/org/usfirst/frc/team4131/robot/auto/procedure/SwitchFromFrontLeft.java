package org.usfirst.frc.team4131.robot.auto.procedure;

import org.usfirst.frc.team4131.robot.auto.Action;
import org.usfirst.frc.team4131.robot.auto.Procedure;
import org.usfirst.frc.team4131.robot.auto.Side;
import org.usfirst.frc.team4131.robot.auto.action.DistanceMoveAction;
import org.usfirst.frc.team4131.robot.auto.action.StartPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.WaitAction;
import org.usfirst.frc.team4131.robot.auto.action.EndPnuematicAction;
import org.usfirst.frc.team4131.robot.auto.action.RaiseElevatorAndClimberAction;
import org.usfirst.frc.team4131.robot.subsystem.SubsystemProvider;

import edu.wpi.first.wpilibj.Timer;

import java.util.List;

/**
 * Autonomous procedure to run when the robot is placed
 * in front of the driver station.
 */
public class SwitchFromFrontLeft implements Procedure {

    @Override
    public void populate(SubsystemProvider provider, List<Side> data, List<Action> procedure) {
    		procedure.add(new StartPnuematicAction(provider.getClaw()));
            procedure.add(new DistanceMoveAction(provider.getDriveBase(), (98)));//104
            //if(data.get(0) == Side.LEFT) {
            	 procedure.add(new WaitAction(provider.getTimer(), 0.5));
                 procedure.add(new RaiseElevatorAndClimberAction(provider.getClimber(), provider.getElevator(), false, true));
                 procedure.add(new EndPnuematicAction(provider.getClaw()));
            //}
    }
}

           
    
