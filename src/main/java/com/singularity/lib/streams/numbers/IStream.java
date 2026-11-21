/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.numbers;

import com.singularity.lib.streams.angles.AStream;
import com.singularity.lib.streams.booleans.BStream;
import com.singularity.lib.streams.numbers.filters.IFilter;

import java.util.function.DoubleSupplier;

/**
 * A stream of doubles that is accessed with the {@link IStream#get()} function
 *
 * <p>Can be created with lambdas to represent a stream of inputs.
 *
 * <p>It can be filitered with the available filtering options.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public interface IStream extends DoubleSupplier {

    /**
     * Create an IStream from another IStream. This is helpful if you want to use some of the
     * decorator functions with a lambda.
     *
     * @param stream stream to create IStream from
     * @return the resulting IStream
     */
    public static IStream create(IStream stream) {
        return stream;
    }

    /**
     * Create an IStream from another AStream. This will convert the angle to a double in radians.
     *
     * @param stream stream to create IStream from
     * @return the resulting IStream
     */
    public static IStream create(AStream stream) {
        return () -> stream.get().toRadians();
    }

    /**
     * Create a IStream from another BStream. This will check if the amplitude is above a certain
     * threshold.
     *
     * @param stream stream to create IStream from
     * @return the resulting IStream
     */
    public static IStream create(BStream stream) {
        return () -> stream.get() ? 1.0 : 0.0;
    }

    /**
     * @return next value in the stream
     */
    public double get();

    /**
     * @return get IStream as a double
     */
    public default double getAsDouble() {
        return get();
    }

    /**
     * Create a new FilteredIStream from the current stream
     *
     * @param filters the filters you want to apply to the IStream
     * @return The FilteredIStream
     */
    public default FilteredIStream filtered(IFilter... filters) {
        return new FilteredIStream(this, filters);
    }

    /**
     * Create a new PollingIStream from the current stream
     *
     * @param dt the time inbetween each poll of the IStream
     * @return The PollingIStream
     */
    public default PollingIStream polling(double dt) {
        return new PollingIStream(this, dt);
    }

    /**
     * Combine two IStreams by adding their results together
     *
     * @param other other IStream to add to this one
     * @return the resulting IStream after the sum
     */
    public default IStream add(IStream other) {
        return () -> get() + other.get();
    }

    /**
     * Combine two IStreams by subtracting their results together
     *
     * @param other other IStream to subtract from this one
     * @return the resulting IStream after the subtraction
     */
    public default IStream sub(IStream other) {
        return () -> get() - other.get();
    }

    /**
     * Casts an IStream to a Number
     *
     * @return a Number that reads from this stream
     */
    public default NumberStream number() {
        return new NumberStream(this);
    }
}
