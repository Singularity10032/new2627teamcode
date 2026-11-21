/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.booleans;

import com.singularity.lib.streams.booleans.filters.BFilter;

/**
 * A FilteredBStream is similar to a FilteredIStream.
 *
 * <p>It works like a BStream, but every time you call .get(), it runs the value through the filters
 * you provided it.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class FilteredBStream implements BStream {

    private BStream mStream; // Stream used
    private BFilter mStreamFilter; // StreamFilter used

    /**
     * Makes filtered stream from stream and stream filter
     *
     * @param stream input stream
     * @param filter stream filter
     */
    public FilteredBStream(BStream stream, BFilter... filter) {
        mStream = stream;
        mStreamFilter = BFilter.create(filter);
    }

    /**
     * Get next value from filtered stream
     *
     * @return next value
     */
    public boolean get() {
        return mStreamFilter.get(mStream.get());
    }
}
