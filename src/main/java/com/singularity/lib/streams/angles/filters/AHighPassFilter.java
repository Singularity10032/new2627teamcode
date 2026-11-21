/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.angles.filters;

import com.singularity.lib.math.Angle;

/**
 * Implementation for of a real time IIR HighPassFilter
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class AHighPassFilter implements AFilter {

    private ALowPassFilter mInverse;

    /**
     * @param rc time constant for high pass filter
     */
    public AHighPassFilter(Number rc) {
        mInverse = new ALowPassFilter(rc);
    }

    public Angle get(Angle next) {
        return next.sub(mInverse.get(next));
    }
}
