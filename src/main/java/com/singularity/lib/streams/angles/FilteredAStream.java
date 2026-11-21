/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.angles;

import com.singularity.lib.math.Angle;
import com.singularity.lib.streams.angles.filters.AFilter;

/**
 * Takes an {@link AStream} and a {@link AFilter} and makes a {@link FilteredAStream}
 *
 * <p>This could be used to automatically filter controller inputs
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class FilteredAStream implements AStream {

    private AStream mStream; // Stream used
    private AFilter mStreamFilter; // StreamFilter used

    /**
     * Makes filtered stream from stream and stream filter
     *
     * @param stream input stream
     * @param filter stream filter
     */
    public FilteredAStream(AStream stream, AFilter... filter) {
        mStream = stream;
        mStreamFilter = AFilter.create(filter);
    }

    /**
     * Get next value from filtered stream
     *
     * @return next value
     */
    public Angle get() {
        return mStreamFilter.get(mStream.get());
    }
}
