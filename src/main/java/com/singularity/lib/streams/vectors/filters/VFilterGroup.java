/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors.filters;

import com.singularity.lib.math.Vector2D;

/**
 * A class that lets you combine multiple vector filters into one vector filter.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class VFilterGroup implements VFilter {

    // Array of all the filters
    private final VFilter[] mFilters;

    /**
     * Make VFilterGroup out of an array of Filters
     *
     * @param filters list of filters to combine
     */
    public VFilterGroup(VFilter... filters) {
        mFilters = filters;
    }

    public Vector2D get(Vector2D next) {
        // Put next through each of the filters
        for (VFilter filter : mFilters) {
            next = filter.get(next);
        }

        // Return filtered value
        return next;
    }
}
