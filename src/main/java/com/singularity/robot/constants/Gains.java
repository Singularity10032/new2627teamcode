/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.robot.constants;

import com.pathplanner.lib.config.PIDConstants;

public class Gains {

    public interface Swerve {
        public interface Drive {
            double kP = 0.10224;
            double kI = 0.0;
            double kD = 0.0;

            double kS = 0.19896;
            double kV = 0.12528;
            double kA = 0.011662;
        }

        public interface Turn {
            double kP = 100.0;
            double kI = 0.0;
            double kD = 0.5;

            double kS = 0.1;
            double kV = 2.66;
            double kA = 0.0;
        }

        public interface Alignment {
            PIDConstants XY = new PIDConstants(2.2, 0, 0.0);
            PIDConstants THETA = new PIDConstants(3, 0, 0.0);
        }
    }
}
