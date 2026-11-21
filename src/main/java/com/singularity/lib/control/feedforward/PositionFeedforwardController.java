/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.control.feedforward;

import com.singularity.lib.control.Controller;
import com.singularity.lib.streams.numbers.filters.Derivative;

/**
 * A positional controller that uses a feedforward model to calculate motor outputs given positional
 * setpoints.
 *
 * <p>The feedforward model takes in velocity setpoints, so the derivative of the positional
 * setpoints must be calculated. This is done implicitly by this controller though.
 *
 * @author Myles Pasetsky (myles.pasetsky@gmail.com)
 */
public class PositionFeedforwardController extends Controller {

    /** Filter to implicitly calculate velocity from position setpoints */
    private final Derivative mVelocity;

    /** Internal feedforward model */
    private final MotorFeedforward mFeedforward;

    /**
     * Create a position controller with a feedforward model
     *
     * @param feedforward feedforward model
     */
    public PositionFeedforwardController(MotorFeedforward feedforward) {
        mVelocity = new Derivative();
        mFeedforward = feedforward;
    }

    /**
     * Calculates a motor output by feeding the derivative of a positional setpoint to a feedforward
     * model
     *
     * @param setpoint positional setpoint
     * @param measurement position measurement, which is not used by the feedforward model
     * @return motor output from feedforward model
     */
    @Override
    protected double calculate(double setpoint, double measurement) {
        double velocity = mVelocity.get(setpoint);
        return mFeedforward.calculate(velocity);
    }
}
