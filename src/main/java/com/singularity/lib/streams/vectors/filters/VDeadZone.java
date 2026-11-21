/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors.filters;

import com.singularity.lib.math.Vector2D;

/**
 * A filter that will zero out a vector if its magnitude is too small
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class VDeadZone implements VFilter {

    private Number mDeadZone;

    public VDeadZone(Number deadzone) {
        mDeadZone = deadzone;
    }

    public Vector2D get(Vector2D target) {
        if (target.magnitude() < mDeadZone.doubleValue()) {
            return Vector2D.kOrigin;
        }

        return target;
    }
}
