/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors.filters;

import com.singularity.lib.math.Vector2D;
import com.singularity.lib.util.StopWatch;

/**
 * A filter that limits the amount that a Vector2D can change by per second.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class VRateLimit implements VFilter {

    private final StopWatch mTimer;
    private final Number mRateLimit;

    private Vector2D mLastValue;

    public VRateLimit(Number rateLimit) {
        mTimer = new StopWatch();
        mRateLimit = rateLimit;

        mLastValue = Vector2D.kOrigin;
    }

    public Vector2D get(Vector2D next) {
        final double dt = mTimer.reset();
        return mLastValue =
                mLastValue.add(next.sub(mLastValue).clamp(dt * mRateLimit.doubleValue()));
    }
}
