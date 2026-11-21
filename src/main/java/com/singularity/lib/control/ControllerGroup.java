/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.control;

/**
 * Controllers can be grouped together in a "controller group" if they have the same setpoint and
 * measurement.
 *
 * <p>This allows different controller implementations that are controlling the same variable to be
 * concisely composed together.
 *
 * @author Myles Pasetsky (myles.pasetsky@gmail.com)
 */
public class ControllerGroup extends Controller {

    /** Controller part of the group */
    private final Controller mController;

    /** Controllers part of the group */
    private final Controller[] mControllers;

    /** Create a controller group */
    public ControllerGroup(Controller controller, Controller... controllers) {
        mController = controller;
        mControllers = controllers;
    }

    /**
     * Updates the internal controllers with the setpoint and measurement and returns their combined
     * output.
     *
     * @param setpoint setpoint
     * @param measurement measurement
     * @return summed output of the interal controllers
     */
    @Override
    protected double calculate(double setpoint, double measurement) {
        double output = mController.update(setpoint, measurement);

        for (Controller controller : mControllers) {
            output += controller.update(setpoint, measurement);
        }

        return output;
    }
}
