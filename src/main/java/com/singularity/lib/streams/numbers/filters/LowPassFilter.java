/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.numbers.filters;

import com.singularity.lib.util.StopWatch;

/**
 * Implementation of a real time IIR LowPassFilter
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class LowPassFilter implements IFilter {

    // Used to calculate time in between calls
    private final StopWatch mTimer;

    // Used to calculate next value based on previous value and time
    private final Number mRC;
    private double mLastValue;

    /**
     * @param rc Time Constant. The time constant is the amount of time in seconds that it takes to
     *     get 63.2% of the way to the target value. 63.2% is (1 - (1 / e)).
     */
    public LowPassFilter(Number rc) {
        mTimer = new StopWatch();
        mLastValue = 0;
        mRC = rc;
    }

    public double get(double next) {
        // If RC will cause errors, disable filter
        if (mRC.doubleValue() <= 0.0) {
            return mLastValue = next;
        }

        // Get a constant, which is determined based on dt and the mRC constant
        double a = 1.0 - Math.exp(-mTimer.reset() / mRC.doubleValue());

        // Based on the value of a (which is determined by dt), the next value
        // could either change a lot, or not by much. (smaller dt = smaller change)
        return mLastValue += a * (next - mLastValue);
    }
}
