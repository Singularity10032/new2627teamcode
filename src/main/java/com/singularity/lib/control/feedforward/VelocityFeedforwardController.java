/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.control.feedforward;

import com.singularity.lib.control.Controller;

/**
 * A velocity controller that uses a feedforward model to calculate motor outputs given velocity
 * setpoints.
 *
 * @author Myles Pasetsky (myles.pasetsky@gmail.com)
 */
public class VelocityFeedforwardController extends Controller {

    /** Internal feedforward model */
    private final MotorFeedforward mFeedforward;

    /**
     * Create a velocity controller with a feedforward model
     *
     * @param feedforward the model
     */
    public VelocityFeedforwardController(MotorFeedforward feedforward) {
        mFeedforward = feedforward;
    }

    /**
     * Calculates a motor output by feeding a velocity setpoint to a feedforward model.
     *
     * @param setpoint velocity setpoint of the controller
     * @param measurement measurement, which is not used in calculating an output
     * @return motor output from feedforward model
     */
    @Override
    protected double calculate(double setpoint, double measurement) {
        return mFeedforward.calculate(setpoint);
    }
}
