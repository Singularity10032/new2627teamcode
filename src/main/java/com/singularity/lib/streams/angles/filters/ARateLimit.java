/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.angles.filters;

import com.singularity.lib.math.Angle;
import com.singularity.lib.math.SLMath;
import com.singularity.lib.util.StopWatch;

/**
 * This class lets you rate limit a stream of inputs
 *
 * <p>Instead of being based on the rate that update is called, the value you give it is based on
 * how much it should be able to change in one second.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class ARateLimit implements AFilter {

    // Used to get the time since the last get call
    private StopWatch mTimer;

    // Used to limit the change from the last value
    private Angle mLastValue;
    private Number mRateLimit;

    /**
     * @param rateLimit The max speed in rad/s.
     */
    public ARateLimit(Number rateLimit) {
        if (rateLimit.doubleValue() <= 0) {
            throw new IllegalArgumentException("rateLimit must be a positive number");
        }

        mTimer = new StopWatch();
        mRateLimit = rateLimit;
        mLastValue = Angle.kZero;
    }

    public Angle get(Angle next) {
        final double limit = mRateLimit.doubleValue() * mTimer.reset();
        return mLastValue =
                mLastValue.addRadians(SLMath.clamp(next.sub(mLastValue).toRadians(), limit));
    }
}
