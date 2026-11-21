/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.control.angle.feedforward;

import com.singularity.lib.control.angle.AngleController;
import com.singularity.lib.control.feedforward.MotorFeedforward;
import com.singularity.lib.math.Angle;
import com.singularity.lib.util.AngleVelocity;

/**
 * A positional feedforward controller for angular systems.
 *
 * @see com.singularity.lib.control.feedforward.PositionFeedforwardController
 * @author Myles Pasetsky (myles.pasetsky@gmail.com)
 */
public class AnglePositionFeedforwardController extends AngleController {

    /** the feedforward model */
    private final MotorFeedforward mFeedforward;

    /** find the derivative of angular setpoints */
    private final AngleVelocity mDerivative;

    /**
     * Create an angle position feedforward controller
     *
     * @param feedforward model
     */
    public AnglePositionFeedforwardController(MotorFeedforward feedforward) {
        mFeedforward = feedforward;
        mDerivative = new AngleVelocity();
    }

    /**
     * Calculates a motor output by feeding the derivative of a positional setpoint to a feedforward
     * model
     *
     * @param setpoint angular positional setpoint
     * @param measurement angular position measurement, which is not used by the feedforward model
     * @return motor output from feedforward model
     */
    @Override
    protected double calculate(Angle setpoint, Angle measurement) {
        return mFeedforward.calculate(mDerivative.get(setpoint));
    }
}
