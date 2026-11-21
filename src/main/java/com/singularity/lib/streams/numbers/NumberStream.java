/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.numbers;

/**
 * A number which reads from a streams.
 *
 * <p>Useful for filtering streams (including SmartNumbers) and converting them back into Numbers.
 *
 * @author Myles Pasetsky (myles.pasetsky@gmail.com)
 */
public class NumberStream extends Number implements IStream {

    /** the underlying stream */
    private final IStream mStream;

    /**
     * Create a number stream given a stream
     *
     * @param stream IStream to return through number classes
     */
    public NumberStream(IStream stream) {
        mStream = stream;
    }

    /**
     * @return value from stream
     */
    @Override
    public double get() {
        return mStream.get();
    }

    /**
     * @return value from stream
     */
    @Override
    public double doubleValue() {
        return get();
    }

    /**
     * @return value from stream as float
     */
    @Override
    public float floatValue() {
        return (float) get();
    }

    /**
     * @return value from stream as integer
     */
    @Override
    public int intValue() {
        return (int) get();
    }

    /**
     * @return value from stream as long
     */
    @Override
    public long longValue() {
        return (long) get();
    }
}
