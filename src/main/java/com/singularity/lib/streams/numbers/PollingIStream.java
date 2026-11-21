/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.numbers;

import edu.wpi.first.wpilibj.Notifier;

/**
 * A PollingIStream calls {@link IStream#get()} every x milliseconds instead of when the user calls
 * get
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class PollingIStream implements IStream, AutoCloseable {

    private Notifier mPoller;
    private volatile double mResult;

    /**
     * Creates a PollingIStream from an IStream and a time value
     *
     * @param stream istream to poll from
     * @param dt Number of calls per second
     */
    public PollingIStream(IStream stream, double dt) {
        if (dt <= 0) {
            throw new IllegalArgumentException("dt must be greater than 0");
        }

        mResult = 0.0;
        mPoller = new Notifier(() -> mResult = stream.get());
        mPoller.startPeriodic(dt);
    }

    public double get() {
        return mResult;
    }

    protected void finalize() {
        close();
    }

    public void close() {
        mPoller.close();
        mResult = 0.0;
    }
}
