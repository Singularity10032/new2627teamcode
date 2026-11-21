/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.util;

import com.singularity.lib.math.Angle;

public class AngleVelocity {

    private final StopWatch mTimer;
    private Angle mPreviousAngle;

    public AngleVelocity() {
        mTimer = new StopWatch();
        mPreviousAngle = Angle.kZero;
    }

    public double get(Angle angle) {
        double velocity = angle.velocityRadians(mPreviousAngle, mTimer.reset());
        mPreviousAngle = angle;
        return velocity;
    }
}
