/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/*-
 * This auton does nothing... it is used as a placeholder
 *
 * @author Sam Belliveau
 */
public class DoNothingAuton extends SequentialCommandGroup {

    public DoNothingAuton() {
        addCommands(
                /** Do a whole lot of nothing */
                );
    }
}