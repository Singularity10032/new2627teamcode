/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.robot.commands.swerve;

import com.singularity.robot.subsystems.swerve.CommandSwerveDrivetrain;

import edu.wpi.first.wpilibj2.command.Command;

import com.ctre.phoenix6.swerve.SwerveRequest;

public class SwerveXMode extends Command {
    private CommandSwerveDrivetrain swerve;

    public SwerveXMode() {
        swerve = CommandSwerveDrivetrain.getInstance();
        addRequirements(swerve);
    }

    @Override
    public void initialize() {
        SwerveRequest request = new SwerveRequest.SwerveDriveBrake();
        CommandSwerveDrivetrain.getInstance().setControl(request);
    }
}