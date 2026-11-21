/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.robot.constants;

import com.pathplanner.lib.path.PathConstraints;
import com.singularity.lib.network.SmartBoolean;

import edu.wpi.first.math.util.Units;

/*-
 * File containing constants and tunable settings for every subsystem on the robot.
 *
 * We use SingularityLib's SmartNumber / SmartBoolean in order to have tunable
 * values that we can edit on Shuffleboard.
 */
public interface Settings {
    public final double DT = 0.020;
    public final int LOGGING_FREQUENCY = 2;

    public final SmartBoolean DEBUG_MODE = new SmartBoolean("Robot/DebugMode", false);

    public final double LOOP_OVERRUN_WARNING_TIME_SEC = 1;

    public interface Swerve {
        public final double MODULE_VELOCITY_DEADBAND_M_PER_S = 0.1;
        public final double ROTATIONAL_DEADBAND_RAD_PER_S = 0.1;

        public interface Constraints {
            public final double MAX_VELOCITY_M_PER_S = 4.93;
            public final double MAX_ANGULAR_VEL_RAD_PER_S = Units.degreesToRadians(300.0);
            public final double MAX_ACCEL_M_PER_S_SQUARED = 15.0;
            public final double MAX_ANGULAR_ACCEL_RAD_PER_S_SQUARED = Units.degreesToRadians(900.0);

            public final PathConstraints DEFAULT_CONSTRAINTS =
                new PathConstraints(
                    MAX_VELOCITY_M_PER_S,
                    MAX_ACCEL_M_PER_S_SQUARED,
                    MAX_ANGULAR_VEL_RAD_PER_S,
                    MAX_ANGULAR_ACCEL_RAD_PER_S_SQUARED);
        }
    }
}
