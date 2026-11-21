/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.numbers;

import com.singularity.lib.streams.numbers.filters.IFilter;

/**
 * Takes an {@link IStream} and a {@link IFilter} and makes a {@link FilteredIStream}
 *
 * <p>This could be used to automatically filter controller inputs
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class FilteredIStream implements IStream {

    private IStream mStream; // Stream used
    private IFilter mStreamFilter; // StreamFilter used

    /**
     * Makes filtered stream from stream and stream filter
     *
     * @param stream input stream
     * @param filter stream filter
     */
    public FilteredIStream(IStream stream, IFilter... filter) {
        mStream = stream;
        mStreamFilter = IFilter.create(filter);
    }

    /**
     * Get next value from filtered stream
     *
     * @return next value
     */
    public double get() {
        return mStreamFilter.get(mStream.get());
    }
}
