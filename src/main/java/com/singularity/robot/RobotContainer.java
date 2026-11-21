/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.robot;

import com.singularity.robot.commands.auton.DoNothingAuton;
import com.singularity.robot.commands.swerve.SwerveDriveDrive;
import com.singularity.robot.commands.swerve.SwerveResetHeading;
import com.singularity.robot.commands.swerve.SwerveXMode;
import com.singularity.robot.constants.Ports;
import com.singularity.robot.subsystems.swerve.CommandSwerveDrivetrain;
import com.singularity.lib.input.Gamepad;
import com.singularity.lib.input.gamepads.AutoGamepad;
import com.singularity.lib.network.SmartBoolean;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
    public interface EnabledSubsystems {
        SmartBoolean SWERVE = new SmartBoolean("Enabled Subsystems/Swerve Is Enabled", true);
    }

    // Gamepads
    public static final Gamepad driver = new AutoGamepad(Ports.Gamepad.DRIVER);

    // Subsystems
    private final CommandSwerveDrivetrain swerve = CommandSwerveDrivetrain.getInstance();

    // Autons
    private static SendableChooser<Command> autonChooser = new SendableChooser<>();

    // Robot container
    public RobotContainer() {
        swerve.configureAutoBuilder();
        configureDefaultCommands();
        configureButtonBindings();
        configureAutons();

        SmartDashboard.putData("Field", swerve.getField());
    }

    /****************/
    /*** DEFAULTS ***/
    /****************/

    private void configureDefaultCommands() {
        swerve.setDefaultCommand(new SwerveDriveDrive(driver));
    }

    /***************/
    /*** BUTTONS ***/
    /***************/

    private void configureButtonBindings() {
        // Reset Heading: point the robot's front away from the driver, then press.
        // From then on, stick forward = away from the driver, whichever way the robot faces.
        driver.getDPadUp()
            .onTrue(new SwerveResetHeading());

        // X wheels (lock in place)
        driver.getDPadDown()
            .whileTrue(new SwerveXMode());
    }

    /**************/
    /*** AUTONS ***/
    /**************/

    public void configureAutons() {
        autonChooser.setDefaultOption("Do Nothing", new DoNothingAuton());

        SmartDashboard.putData("Autonomous", autonChooser);
    }

    public Command getAutonomousCommand() {
        if (autonChooser.getSelected() == null) {
            return new DoNothingAuton();
        }
        return autonChooser.getSelected();
    }

    public void periodic() {
        SmartDashboard.putNumber("Robot/Total Current Draw",
            swerve.getTotalDriveSupplyCurrent() + swerve.getTotalSteerSupplyCurrent());
    }
}
