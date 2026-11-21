/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors.filters;

import com.singularity.lib.streams.numbers.filters.HighPassFilter;

/**
 * A filter that applies a HighPassFilter to a VStream
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class VHighPassFilter extends XYFilter {

    public VHighPassFilter(Number rc) {
        super(new HighPassFilter(rc), new HighPassFilter(rc));
    }
}
