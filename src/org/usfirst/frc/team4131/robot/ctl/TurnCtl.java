package org.usfirst.frc.team4131.robot.ctl;

import org.usfirst.frc.team4131.robot.Robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * A turn controller used to ensure accurate rotation using
 * the navX onboard controller.
 * <p>See the usage of this class in {@link org.usfirst.frc.team4131.robot.auto.action.TurnAction}</p>.
 *
 * @see #getInstance()
 */
public class TurnCtl implements PIDOutput {
    /** The singleton instance of the turn controller */
    private static final TurnCtl INSTANCE = new TurnCtl();

    // Internal navX and PID control device
    private final AHRS dev;
    public static PIDController controller;
   
    // Exposed methods used for supplying turn actions
    private boolean isTurning;
    private double throttleDelta;

    private TurnCtl() {
        this.dev = new AHRS(SPI.Port.kMXP);
        PIDController controller = new PIDController(.04, 0, 0, 0, this.dev, this);
        this.isTurning = false;
        controller.setInputRange(-180, 180);
        controller.setOutputRange(-.75, .75);
        controller.setAbsoluteTolerance(3);
        controller.setContinuous(true);
        controller.disable();
        TurnCtl.controller = controller;
    }

    /**
     * Obtains the singleton instance of the navX turn
     * controller.
     *
     * @return the turn controller wrapper
     */
    public static TurnCtl getInstance() {
        return INSTANCE;
    }
 
    /**
     * Obtains the yaw read off of the navX.
     *
     * @return the yaw
     */
    public double getYaw() {
        return this.dev.getYaw();
    }
    //pitch & roll
    public double getPitch() {
    	return this.dev.getPitch();
    }
    public double getRoll() {
    	return this.dev.getRoll();
    }

    public void reset() {
    	this.dev.zeroYaw();
        while (this.dev.isCalibrating() ||
        		!this.dev.isConnected());
    }
    
    /**
     * Begins the PID procedure. Must be placed before a
     * polling loop in order to cause the controller to
     * supply the correct throttle deltas.
     *
     * @param delta the angle which to turn towards between
     * {@code -180 <= delta <= 180}
     */
    public void begin(double delta) {
    	
    	if (!this.isTurning) {
            //delta = delta - Robot.yawzero;
            //delta = (Math.abs(delta) % 360) * Math.signum(delta);
            while (delta > 180 || delta < -180) {
            	if (delta > 180) delta -= 360;
            	if (delta < -180) delta += 360;
            }
    		
            this.isTurning = true;
            //dev.zeroYaw();
            TurnCtl.controller.setSetpoint(delta);
            this.throttleDelta = 0;
            TurnCtl.controller.enable();
        }
    }

    /**
     * Obtains the throttle delta value that is produced by
     * running the PID function.
     *
     * @return the throttle
     */
    public double getDelta() {
        return this.throttleDelta;
    }


    /**
     * Determines whether the target has been reached.
     * <p>This method does not detect whether or not the
     * PID operation has completed or not, so be cautious
     * to continue polling until this method returns
     * consistently.</p>
     *
     * @return {@code true} if the turn control comes within
     * bound of the target angle, {@code false} if it is
     * still outside
     */
    public boolean targetReached() {
        return TurnCtl.controller.onTarget();
    }

    /**
     * Completes PID control, should be called at the end
     * of a polling loop in order to release the controller
     * for other turning processes.
     */
    public void finish() {
        if (this.isTurning) {
            this.isTurning = false;
            TurnCtl.controller.disable();
        }
    }

    @Override
    public void pidWrite(double output) {
        this.throttleDelta = output;
    }
}