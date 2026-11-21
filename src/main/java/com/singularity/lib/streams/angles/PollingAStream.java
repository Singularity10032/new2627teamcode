/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.angles;

import com.singularity.lib.math.Angle;

import edu.wpi.first.wpilibj.Notifier;

/**
 * A PollingAStream calls {@link AStream#get()} every x milliseconds instead of when the user calls
 * get
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class PollingAStream implements AStream, AutoCloseable {

    private Notifier mPoller;
    private volatile Angle mResult;

    /**
     * Creates a PollingAStream from an AStream and a time value
     *
     * @param stream astream to poll from
     * @param dt Number of calls per second
     */
    public PollingAStream(AStream stream, double dt) {
        if (dt <= 0) {
            throw new IllegalArgumentException("dt must be greater than 0");
        }

        mResult = Angle.kNull;
        mPoller = new Notifier(() -> mResult = stream.get());
        mPoller.startPeriodic(dt);
    }

    public Angle get() {
        return mResult;
    }

    protected void finalize() {
        close();
    }

    public void close() {
        mPoller.close();
        mResult = Angle.kNull;
    }
}
