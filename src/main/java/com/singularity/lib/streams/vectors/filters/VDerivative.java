/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors.filters;

import com.singularity.lib.streams.numbers.filters.Derivative;

/**
 * Filter that takes the derivative of a VStream with respect to time.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class VDerivative extends XYFilter {

    public VDerivative() {
        super(new Derivative(), new Derivative());
    }
}
