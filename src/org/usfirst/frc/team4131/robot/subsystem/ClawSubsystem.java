package org.usfirst.frc.team4131.robot.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4131.robot.command.ClawCommand;

public class ClawSubsystem extends Subsystem {
    public ClawSubsystem() {
    }

    @Override
    protected void initDefaultCommand() {
        this.setDefaultCommand(new ClawCommand(this));
    }

    public void doClamp() {
    }
}