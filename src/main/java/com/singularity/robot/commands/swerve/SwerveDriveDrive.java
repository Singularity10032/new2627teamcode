/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.robot.commands.swerve;

import com.singularity.lib.input.Gamepad;
import com.singularity.lib.math.SLMath;
import com.singularity.lib.math.Vector2D;
import com.singularity.lib.streams.numbers.IStream;
import com.singularity.lib.streams.numbers.filters.LowPassFilter;
import com.singularity.lib.streams.vectors.VStream;
import com.singularity.lib.streams.vectors.filters.VDeadZone;
import com.singularity.lib.streams.vectors.filters.VLowPassFilter;
import com.singularity.lib.streams.vectors.filters.VRateLimit;

import com.singularity.robot.constants.DriverConstants.Driver.Drive;
import com.singularity.robot.constants.DriverConstants.Driver.Turn;
import com.singularity.robot.constants.Settings.Swerve;
import com.singularity.robot.subsystems.swerve.CommandSwerveDrivetrain;

import edu.wpi.first.wpilibj2.command.Command;

public class SwerveDriveDrive extends Command {

    private final CommandSwerveDrivetrain swerve;

    private final Gamepad driver;

    private final VStream speed;
    private final IStream turn;

    public SwerveDriveDrive(Gamepad driver) {
        swerve = CommandSwerveDrivetrain.getInstance();

        speed = VStream.create(this::getDriverInputAsVelocity)
        .filtered(
            new VDeadZone(Drive.DEADBAND), 
            x -> x.clamp(1),
            x -> x.pow(Drive.POWER),
            x -> x.mul(Swerve.Constraints.MAX_VELOCITY_M_PER_S),
            new VRateLimit(Swerve.Constraints.MAX_ACCEL_M_PER_S_SQUARED),
            new VLowPassFilter(Drive.RC)
        );

        turn = IStream.create(driver::getRightX)
        .filtered(
            x -> SLMath.deadband(x, Turn.DEADBAND),
            x -> SLMath.spow(x, Turn.POWER),
            x -> x * Swerve.Constraints.MAX_ANGULAR_VEL_RAD_PER_S,
            new LowPassFilter(Turn.RC)
        );

        this.driver = driver;

        addRequirements(swerve);
    }

    private Vector2D getDriverInputAsVelocity() {
        return new Vector2D(driver.getLeftStick().y, -driver.getLeftStick().x);
    }

    @Override
    public void execute() {
        swerve.setControl(swerve.getFieldCentricSwerveRequest()
            .withVelocityX(speed.get().x)
            .withVelocityY(speed.get().y)
            .withRotationalRate(-turn.getAsDouble())
        );
    }
}