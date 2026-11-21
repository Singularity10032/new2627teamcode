/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.control.feedforward;

import com.singularity.lib.control.Controller;

/**
 * A feedforward term to account for gravity for motorized lifts.
 *
 * <p>The motor feedforward used in the context of a lift will not account for gravity that is
 * acting on the lift.
 *
 * <p>Can be paired with MotorFeedforward or other controllers with .add
 *
 * @author Myles Pasetsky (myles.pasetsky@gmail.com)
 */
public class ElevatorFeedforward extends Controller {

    /** voltage to hold arm in place against gravity */
    private final Number kG;

    /**
     * Create ElevatorFeedforward
     *
     * @param kG voltage to hold lift
     */
    public ElevatorFeedforward(Number kG) {
        this.kG = kG;
    }

    /**
     * Calculate voltage to hold elevator at setpoint height
     *
     * @param setpoint setpoint
     * @param measurement measurement
     * @return kG
     */
    @Override
    protected double calculate(double setpoint, double measurement) {
        return kG.doubleValue();
    }
}
