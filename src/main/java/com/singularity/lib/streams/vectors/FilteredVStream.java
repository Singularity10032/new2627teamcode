/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors;

import com.singularity.lib.math.Vector2D;
import com.singularity.lib.streams.vectors.filters.VFilter;

/**
 * A FilteredVStream is similar to a FilteredIStream.
 *
 * <p>It works like a VStream, but every time you call .get(), it runs the value through the filters
 * you provided it.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class FilteredVStream implements VStream {

    private VStream mStream; // Stream used
    private VFilter mStreamFilter; // StreamFilter used

    /**
     * Makes filtered stream from stream and stream filter
     *
     * @param stream input stream
     * @param filter stream filter
     */
    public FilteredVStream(VStream stream, VFilter... filter) {
        mStream = stream;
        mStreamFilter = VFilter.create(filter);
    }

    /**
     * Get next value from filtered stream
     *
     * @return next value
     */
    public Vector2D get() {
        return mStreamFilter.get(mStream.get());
    }
}
