/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.numbers.filters;

/**
 * A class that lets you combine multiple stream filters into one stream filter
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class IFilterGroup implements IFilter {

    // Array of all the filters
    private final IFilter[] mFilters;

    /**
     * Make IFilterGroup out of an array of Filters
     *
     * @param filters list of filters to combine
     */
    public IFilterGroup(IFilter... filters) {
        mFilters = filters;
    }

    public double get(double next) {
        // Put next through each of the filters
        for (IFilter filter : mFilters) {
            next = filter.get(next);
        }

        // Return filtered value
        return next;
    }
}
