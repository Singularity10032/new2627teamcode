/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors.filters;

import com.singularity.lib.streams.numbers.filters.LowPassFilter;

/**
 * A filter that applies a LowPassFilter to a VStream
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class VLowPassFilter extends XYFilter {

    public VLowPassFilter(Number rc) {
        super(new LowPassFilter(rc), new LowPassFilter(rc));
    }
}
