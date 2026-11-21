/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.numbers.filters;

/**
 * Implementation for of a real time IIR HighPassFilter
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class HighPassFilter implements IFilter {

    private LowPassFilter mInverse;

    /**
     * @param rc time constant for high pass filter
     */
    public HighPassFilter(Number rc) {
        mInverse = new LowPassFilter(rc);
    }

    public double get(double next) {
        return next - mInverse.get(next);
    }
}
