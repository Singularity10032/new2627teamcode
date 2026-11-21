/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.angles.filters;

import com.singularity.lib.math.Angle;

/**
 * A class that lets you combine multiple stream filters into one stream filter
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class AFilterGroup implements AFilter {

    // Array of all the filters
    private final AFilter[] mFilters;

    /**
     * Make AFilterGroup out of an array of Filters
     *
     * @param filters list of filters to combine
     */
    public AFilterGroup(AFilter... filters) {
        mFilters = filters;
    }

    public Angle get(Angle next) {
        // Put next through each of the filters
        for (AFilter filter : mFilters) {
            next = filter.get(next);
        }

        // Return filtered value
        return next;
    }
}
