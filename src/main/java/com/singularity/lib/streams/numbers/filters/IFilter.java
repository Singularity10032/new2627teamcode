/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.numbers.filters;

/**
 * This is the Filter interface class that gives a definition for how to implement a filter.
 *
 * <p>All that a filter does is take in the next double in a series and gives you the filtered
 * value.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public interface IFilter {

    /**
     * Create an IFilter from a list of IFilters. This will create an IFilter with the least
     * possible overhead for the number of filters provided.
     *
     * @param filters list of IFilters to apply
     * @return an IFilter that uses every filter given
     */
    public static IFilter create(IFilter... filters) {
        switch (filters.length) {
            case 0:
                return x -> x;
            case 1:
                return filters[0];
            case 2:
                return x -> filters[1].get(filters[0].get(x));
            case 3:
                return x -> filters[2].get(filters[1].get(filters[0].get(x)));
            case 4:
                return x -> filters[3].get(filters[2].get(filters[1].get(filters[0].get(x))));
            default:
                return new IFilterGroup(filters);
        }
    }

    /**
     * Get next value in Filter based on the next value given
     *
     * @param next next input value in the stream
     * @return the output value of the filter
     */
    public double get(double next);

    /**
     * Combine an IFilter with another IFilter
     *
     * @param next filter to be evaluated after this one
     * @return the combined filter
     */
    public default IFilter then(IFilter next) {
        return x -> next.get(get(x));
    }

    /**
     * Combine two IFilters by adding their results together
     *
     * @param other other IFilter to add to this one
     * @return the resulting IFilter after the sum
     */
    public default IFilter add(IFilter other) {
        return x -> get(x) + other.get(x);
    }

    /**
     * Combine two IFilters by subtracting their results together
     *
     * @param other other IFilter to subtract from this one
     * @return the resulting IFilter after the subtraction
     */
    public default IFilter sub(IFilter other) {
        return x -> get(x) - other.get(x);
    }

    /**
     * Invert an IFilter by subtracting the input from the result of the IFilter.
     *
     * <p>Inverting a LowPassFilter gives you a HighPassFilter and vise versa.
     *
     * <p>Inverting something twice gives you the original value.
     *
     * @return the inverted filter
     */
    public default IFilter invert() {
        return x -> x - get(x);
    }
}
