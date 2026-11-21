/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.control.angle.feedforward;

import com.singularity.lib.control.angle.AngleController;
import com.singularity.lib.math.Angle;

/**
 * A feedforward term to account for gravity for motorized arms that can move continuously (if not
 * use `ArmFeedforward`)
 *
 * <p>The motor feedforward used in the context of an arm will not account for gravity that is
 * acting on the arm.
 *
 * <p>Can be paired with MotorFeedforward or other controllers with .add
 *
 * @author Myles Pasetsky (myles.pasetsky@gmail.com)
 */
public class AngleArmFeedforward extends AngleController {

    /** voltage to hold arm horizontal */
    private final Number kG;

    /**
     * Create arm feedforward
     *
     * @param kG term to hold arm vertical against gravity (volts)
     */
    public AngleArmFeedforward(Number kG) {
        this.kG = kG;
    }

    /**
     * Calculates voltage to hold arm at the setpoint angle
     *
     * @param setpoint setpoint
     * @param measurement measurement
     * @return kG * cos(setpoint)
     */
    @Override
    protected double calculate(Angle setpoint, Angle measurement) {
        return kG.doubleValue() * setpoint.cos();
    }
}
