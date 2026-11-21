/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.control.feedforward;

import com.singularity.lib.control.Controller;
import com.singularity.lib.streams.numbers.filters.IFilter;

/**
 * A feedforward term to account for gravity for motorized arms.
 *
 * <p>The motor feedforward used in the context of an arm will not account for gravity that is
 * acting on the arm.
 *
 * <p>Can be paired with MotorFeedforward or other controllers with .add
 *
 * @author Myles Pasetsky (myles.pasetsky@gmail.com)
 */
public class ArmFeedforward extends Controller {

    /** voltage to hold arm horizontal */
    private final Number kG;

    /**
     * function to configure units of cosine (or even use sin if angles are measured differently)
     */
    private final IFilter cosine;

    /**
     * Create arm feedforward
     *
     * @param kG term to hold arm vertical against gravity (volts)
     */
    public ArmFeedforward(Number kG) {
        this(kG, x -> Math.cos(Math.toRadians(x)));
    }

    /**
     * Create arm feedforward
     *
     * @param kG term to hold arm vertical against gravity (volts)
     * @param cosine function to calculate cosine of setpoint
     */
    public ArmFeedforward(Number kG, IFilter cosine) {
        this.kG = kG;
        this.cosine = cosine;
    }

    /**
     * Calculates voltage to hold arm at the setpoint angle
     *
     * @param setpoint setpoint
     * @param measurement measurement
     * @return kG * cos(setpoint)
     */
    @Override
    protected double calculate(double setpoint, double measurement) {
        return kG.doubleValue() * cosine.get(setpoint);
    }
}
