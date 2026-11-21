/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.vectors;

import com.singularity.lib.math.Vector2D;

import edu.wpi.first.wpilibj.Notifier;

/**
 * A PollingVStream is a VStream but its .get() method is called for you at a certain rate. This
 * contains race conditions.
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public class PollingVStream implements VStream {

    private Notifier mPoller;
    private volatile Vector2D mResult;

    /**
     * Creates a PollingVStream from an VStream and a time value
     *
     * @param stream VStream to poll from
     * @param dt time inbetween each poll
     */
    public PollingVStream(VStream stream, double dt) {
        if (dt <= 0) {
            throw new IllegalArgumentException("dt must be greater than 0");
        }

        mResult = Vector2D.kOrigin;
        mPoller = new Notifier(() -> mResult = stream.get());
        mPoller.startPeriodic(dt);
    }

    public Vector2D get() {
        return mResult;
    }

    protected void finalize() {
        close();
    }

    public void close() {
        mPoller.close();
        mResult = Vector2D.kOrigin;
    }
}
