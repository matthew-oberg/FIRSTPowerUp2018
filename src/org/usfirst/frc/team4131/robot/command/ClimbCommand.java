package org.usfirst.frc.team4131.robot.command;

import org.usfirst.frc.team4131.robot.Oi;
import org.usfirst.frc.team4131.robot.Robot;
import org.usfirst.frc.team4131.robot.subsystem.ClimberSubsystem;



/**
 * A command which will activate the climber and raise the
 * robot using the pull-up bar.
 */
public class ClimbCommand extends SingleSubsystemCmd<ClimberSubsystem> {
    public static boolean isClimberTop;
    public static boolean isClimberBottom;
	//private boolean isTop = true, isBottom = true;
	public ClimbCommand(ClimberSubsystem subsystem) {
		
		
        super(subsystem);
    }

    /**
     * Checks the climb button in order to determine whether
     * the robot should climb.
     *
     * @return {@code true} to signal that climbing should
     * commence
     */
    private static boolean climberDownButton() {
        return Oi.CLIMBERDOWN.get();
    }
    private static boolean climberBottomButton() {
        return Oi.CLIMBERBOTTOM.get();
    }
    
    private static boolean climberTopButton() {
        return Oi.CLIMBERTOP.get();
    }
    /**
     * Checks the lower button in order to determine whether
     * the robot should lower itself.
     *
     * @return {@code true} to signal that the robot should
     * lower itself
     */
    private static boolean climberUpButton() {
        return Oi.CLIMBERUP.get();
    }

    @Override
    protected void execute() {
    	isClimberTop = !Robot.topClimberSwitch.get();
        isClimberBottom = !Robot.bottomClimberSwitch.get();
    	if (climberDownButton() && climberUpButton()) {
            this.subsystem.stop();
        } else if (climberDownButton()) {
            if (isClimberBottom) {
            	//isTop = false;
            	this.subsystem.stop();
            } else {
                this.subsystem.lower();
            }
        } else if (climberUpButton()) {
        	if (isClimberTop) {
        		//isBottom = false;
                this.subsystem.stop();
            } else {
                this.subsystem.raise();
            }
        } else if(climberTopButton()) {
        	this.subsystem.goToTop();
        } else if(climberBottomButton()) {
        	this.subsystem.goToBottom();
        } else {
        this.subsystem.stop();
        }
    }

    @Override
    protected void interrupted() {
        this.subsystem.stop();
    }
}