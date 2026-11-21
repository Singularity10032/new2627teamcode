/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.angles;

import com.singularity.lib.math.Angle;
import com.singularity.lib.math.Vector2D;
import com.singularity.lib.streams.vectors.VStream;

/**
 * When controlling angle with a joystick that has a deadzone filter, the angle will snap to 0
 * degrees while in the deadzone. This class prevents the angle snapping and will return the most
 * recent angle from outside of the deadzone.
 *
 * @author Benjamin Goldfisher
 */
public class AStick implements AStream {

    private final VStream mStick;
    private final Number mDeadzone;

    private Angle mPrevious;

    public AStick(VStream stick, Number deadzone) {
        mStick = stick;
        mDeadzone = deadzone;

        mPrevious = Angle.kNull;
    }

    @Override
    public Angle get() {
        final Vector2D stick = mStick.get();

        if (mDeadzone.doubleValue() < stick.magnitude()) {
            mPrevious = stick.getAngle();
        }

        return mPrevious;
    }
}
