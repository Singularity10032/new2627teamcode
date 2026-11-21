/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors.filters;

import com.singularity.lib.math.Vector2D;

/**
 * A filter that clamps the magnitude of a vector to a certain size
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class VClamp implements VFilter {

    private Number mMaxMagnitude;

    public VClamp(Number maxMagnitude) {
        mMaxMagnitude = maxMagnitude;
    }

    public Vector2D get(Vector2D target) {
        return target.clamp(mMaxMagnitude.doubleValue());
    }
}
