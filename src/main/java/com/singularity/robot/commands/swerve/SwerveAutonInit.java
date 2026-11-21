/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.robot.commands.swerve;

import com.singularity.robot.subsystems.swerve.CommandSwerveDrivetrain;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SwerveAutonInit extends InstantCommand {
    private final CommandSwerveDrivetrain swerve;

    public SwerveAutonInit() {
        swerve = CommandSwerveDrivetrain.getInstance();        
    }

    @Override
    public void initialize() {
        swerve.autonInit();
    }
}
