/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors.filters;

import com.singularity.lib.streams.numbers.filters.TimedMovingAverage;

/**
 * A filter that takes a timed moving average of a VStream
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class VTimedMovingAverage extends XYFilter {

    public VTimedMovingAverage(Number time) {
        super(new TimedMovingAverage(time), new TimedMovingAverage(time));
    }
}
