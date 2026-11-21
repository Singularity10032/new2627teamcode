/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.numbers.filters;

import com.singularity.lib.util.StopWatch;

/**
 * This class takes an IStream and gives you the derivative with respect to time.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class Derivative implements IFilter {

    private final StopWatch mTimer;
    private double mLastValue;

    public Derivative() {
        mTimer = new StopWatch();
        mLastValue = 0.0;
    }

    public double get(double next) {
        double diff = next - mLastValue;
        mLastValue = next;
        return diff / mTimer.reset();
    }
}
