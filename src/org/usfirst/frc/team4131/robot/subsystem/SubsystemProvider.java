package org.usfirst.frc.team4131.robot.subsystem;

/**
 * Provides subsystem wrapper singletons
 */
public class SubsystemProvider {
    private final DriveBaseSubsystem driveBase;
    private final ClawSubsystem claw;
    private final ClimberSubsystem climber;
    private final ElevatorSubsystem elevator;
    private final TimerSubsystem timer;

    public SubsystemProvider(DriveBaseSubsystem driveBase,
                             ClawSubsystem claw,
                             ClimberSubsystem climber,
                             ElevatorSubsystem elevator, TimerSubsystem timer) {
        this.driveBase = driveBase;
        this.claw = claw;
        this.climber = climber;
        this.elevator = elevator;
        this.timer = timer;
    }

    public DriveBaseSubsystem getDriveBase() {
        return this.driveBase;
    }

    public ClawSubsystem getClaw() {
        return this.claw;
    }

    public ClimberSubsystem getClimber() {
        return this.climber;
    }

    public ElevatorSubsystem getElevator() {
        return this.elevator;
    }
    public TimerSubsystem getTimer() {
    	return this.timer;
    }
}