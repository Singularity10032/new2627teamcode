/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.booleans.filters;

/**
 * A class that lets you combine multiple boolean filters into one boolean filter.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class BFilterGroup implements BFilter {

    // Array of all the filters
    private final BFilter[] mFilters;

    /**
     * Make BFilterGroup out of an array of Filters
     *
     * @param filters list of filters to combine
     */
    public BFilterGroup(BFilter... filters) {
        mFilters = filters;
    }

    public boolean get(boolean next) {
        // Put next through each of the filters
        for (BFilter filter : mFilters) {
            next = filter.get(next);
        }

        // Return filtered value
        return next;
    }
}
