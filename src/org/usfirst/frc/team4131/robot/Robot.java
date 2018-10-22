/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4131.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4131.robot.auto.Procedure;
import org.usfirst.frc.team4131.robot.auto.Side;
import org.usfirst.frc.team4131.robot.auto.procedure.*;
import org.usfirst.frc.team4131.robot.ctl.TurnCtl;
import org.usfirst.frc.team4131.robot.subsystem.*;

import java.util.function.Supplier;

/**
 * Robot lifecycle handler.
 */
public class Robot extends IterativeRobot {
    
	// Compressor stuff
    private static final Compressor compressor = new Compressor(61);
    
    // Variables for random functions
    public static boolean auton = true;
    public static boolean isClimberTop;
    public static boolean isClimberBottom;
    public static boolean isElevatorTop;
    public static boolean isElevatorBottom;
    private static int round;
    public static double yawzero;
 
    // Auton chooser
    private final SendableChooser<Procedure> chooser = new SendableChooser<>();
    
    public static AHRS dev = new AHRS(SPI.Port.kMXP);
    
    public static final int DISTANCE = (int) (74.9151910531 * 120);
    
    // Limit Switches
    public final static DigitalInput bottomElevatorSwitch = new DigitalInput(0);//true
    public final static DigitalInput topElevatorSwitch = new DigitalInput(1);//true
    public final static DigitalInput topClimberSwitch = new DigitalInput(2);//true
    public final static DigitalInput bottomClimberSwitch = new DigitalInput(3);//false
    
    // Subsystem stuff
    private SubsystemProvider provider;

    public static void debug(Supplier<String> string) {
        if (round++ == 2000000) {
            System.out.println("DEBUG: " + string.get());
            round = 0;
        }
    }

    @Override
    public void robotInit() {
    	TurnCtl.getInstance().reset();
    	//yawzero = TurnCtl.getInstance().getYaw();
        
    	// Init subsystems
        this.provider = new SubsystemProvider(new DriveBaseSubsystem(),
                new ClawSubsystem(), new ClimberSubsystem(), new ElevatorSubsystem(), new TimerSubsystem());

        //test mode stuff
        //final AHRS dev;
        //dev = new AHRS(SPI.Port.kMXP);
        //LiveWindow.addSensor("DriveBase Subsystem", "Navx", dev);
        //LiveWindow.addActuator("DriveSystem", "RotateController", TurnCtl.controller); 
        //SmartDashboard.putNumber("Encoder Ticks", provider.getDriveBase().getDist());
        
        // Init camera
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setVideoMode(new VideoMode(VideoMode.PixelFormat.kMJPEG, 600, 600, 10));

        // Compressor setup
        compressor.setClosedLoopControl(true);
        compressor.clearAllPCMStickyFaults();

        // Display auto procedures on dashboard
        this.chooser.addDefault("Left Right Baseline", new LeftRightBaseLine());
        this.chooser.addObject("Switch from front Right", new SwitchFromFrontRight());
        this.chooser.addObject("Switch from front Left", new SwitchFromFrontLeft());
        this.chooser.addObject("DriverStation 2 to switch", new DriverStation2ToSwitch());
        //this.chooser.addObject("LeftToSwitchOrScale", new LeftToSwitchOrScale());
        //this.chooser.addObject("RightToSwitchOrScale", new RightToSwitchOrScale());
        this.chooser.addObject("testing", new Testing());
        SmartDashboard.putData("Auto Mode", this.chooser);
        
        provider.getClaw().armUp();
        provider.getClaw().clamp();
    }

    @Override
    public void autonomousInit() {

    	provider.getDriveBase().reset();
    	
    	yawzero = TurnCtl.getInstance().getYaw();
    	//provider.getDriveBase().reset();
    	
        String str = "";
        
        while (str.length() != 3) {
            str = DriverStation.getInstance().getGameSpecificMessage();
        }                                                               
        Side[] sides = new Side[str.length()];
        for (int i = 0, s = str.length(); i < s; i++) {
            sides[i] = Side.decode(str.charAt(i));
        }

        // We can now test with or without smart dashboard easily
        //Procedure procedure = this.chooser.getSelected();
        
    }

    @Override
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    	    	
    	if (provider.getDriveBase().getDist() < DISTANCE) {
    		provider.getDriveBase().doThrottle(-0.75, -0.77);
    	}
    	
    	if (provider.getDriveBase().getDist() >= DISTANCE) {
    		provider.getDriveBase().doThrottle(0, 0);
    	}
    	
    	auton = true;
        
    	// Prints SD info
        SmartDashboard.putNumber("Encoder Ticks", provider.getDriveBase().getDist());
        SmartDashboard.putBoolean("Elevator Top", isElevatorTop);
        SmartDashboard.putBoolean("Elevator Bottom", isElevatorBottom);
        SmartDashboard.putBoolean("Climber Top", isClimberTop);
        SmartDashboard.putBoolean("Climber Bottom", isClimberBottom);
        SmartDashboard.putNumber("yaw", dev.getYaw());
        SmartDashboard.putNumber("pitch", dev.getPitch());
        SmartDashboard.putNumber("roll", dev.getRoll());
        SmartDashboard.putNumber("setpoint", TurnCtl.controller.getSetpoint());
        SmartDashboard.putNumber("yawzero", yawzero);
        
        // Limit switches
    	isClimberTop = !this.topClimberSwitch.get();
        isClimberBottom = !this.bottomClimberSwitch.get();
        isElevatorTop = !this.topElevatorSwitch.get();
        isElevatorBottom = !this.bottomElevatorSwitch.get();
    }
    
    @Override
    public void teleopInit() {
    	provider.getDriveBase().doThrottle(0, 0);
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        // Limit switch stuff
        isClimberTop = !this.topClimberSwitch.get();
        isClimberBottom = !this.bottomClimberSwitch.get();
        isElevatorTop = !this.topElevatorSwitch.get();
        isElevatorBottom = !this.bottomElevatorSwitch.get();
        auton = false;
        
        //TODO PUT IN GET YAW, ROLL, & PITCH!!!!!!!!!! <===============
        //final AHRS dev;
        //dev = new AHRS(SPI.Port.kMXP);
        
        // Smart Dashboard Info
        SmartDashboard.putBoolean("Elevator Top", isElevatorTop);
        SmartDashboard.putBoolean("Elevator Bottom", isElevatorBottom);
        SmartDashboard.putBoolean("Climber Top", isClimberTop);
        SmartDashboard.putBoolean("Climber Bottom", isClimberBottom);
        SmartDashboard.putNumber("Encoder Ticks", provider.getDriveBase().getDist());
        SmartDashboard.putNumber("yaw", dev.getYaw());
        SmartDashboard.putNumber("pitch", dev.getPitch());
        SmartDashboard.putNumber("roll", dev.getRoll());
        SmartDashboard.putNumber("setpoint", TurnCtl.controller.getSetpoint());
        SmartDashboard.putNumber("yawzero", yawzero);
    }
}