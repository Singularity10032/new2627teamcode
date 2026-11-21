/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.robot;

import com.singularity.robot.commands.swerve.SwerveAutonInit;
import com.singularity.robot.commands.swerve.SwerveTeleopInit;
import com.singularity.robot.constants.Settings;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.lang.reflect.Field;

import com.ctre.phoenix6.SignalLogger;
import com.pathplanner.lib.commands.FollowPathCommand;
import com.pathplanner.lib.commands.PathfindingCommand;

public class Robot extends TimedRobot {

    public enum RobotMode {
        DISABLED,
        AUTON,
        TELEOP,
        TEST
    }

    private RobotContainer robot;
    private Command auto;
    private static Alliance alliance;
    private static RobotMode mode;

    private static int periodicCounter = 0;

    public static boolean isBlue() {
        return alliance == Alliance.Blue;
    }

    public static RobotMode getMode() {
        return mode;
    }

    public static int getPeriodicCounter() {
        return periodicCounter;
    }

    /*************************/
    /*** ROBOT SCHEDULEING ***/
    /*************************/

    @Override
    public void robotInit() {
        robot = new RobotContainer();
        mode = RobotMode.DISABLED;

        try {
            Field watchdogField = IterativeRobotBase.class.getDeclaredField("m_watchdog");
            watchdogField.setAccessible(true);
            Watchdog watchdog = (Watchdog) watchdogField.get(this);
            watchdog.setTimeout(Settings.LOOP_OVERRUN_WARNING_TIME_SEC);
        } catch (Exception e) {
            DriverStation.reportError("Failed to disable loop overrun warnings.", e.getStackTrace());
        }

        DataLogManager.start();
        SignalLogger.start();
        FollowPathCommand.warmupCommand().schedule();
        PathfindingCommand.warmupCommand().schedule();

        CommandScheduler.getInstance().schedule(new SwerveAutonInit());
    }

    @Override
    public void robotPeriodic() {
        if (periodicCounter % 50 == 0) {
            DataLogManager.getLog().resume();
        }

        periodicCounter++;

        CommandScheduler.getInstance().run();
        if (!Robot.isReal()) {
            SmartDashboard.putData(CommandScheduler.getInstance());
        }

        if (DriverStation.getAlliance().isPresent()) {
            alliance = DriverStation.getAlliance().get();
        }

        SmartDashboard.putNumber("Robot/Match Time", DriverStation.getMatchTime());
        SmartDashboard.putData("Robot/Scheduled Commands", CommandScheduler.getInstance());
        SmartDashboard.putNumber("Robot/Battery Voltage", RobotController.getBatteryVoltage());

        robot.periodic();
    }

    /*********************/
    /*** DISABLED MODE ***/
    /*********************/

    @Override
    public void disabledInit() {
        mode = RobotMode.DISABLED;
    }

    @Override
    public void disabledPeriodic() {}

    /***********************/
    /*** AUTONOMOUS MODE ***/
    /***********************/

    @Override
    public void autonomousInit() {
        mode = RobotMode.AUTON;

        auto = robot.getAutonomousCommand();

        if (auto != null) {
            auto.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void autonomousExit() {
        CommandScheduler.getInstance().schedule(new SwerveTeleopInit());
    }

    /*******************/
    /*** TELEOP MODE ***/
    /*******************/

    @Override
    public void teleopInit() {
        mode = RobotMode.TELEOP;

        if (auto != null) {
            auto.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {}

    @Override
    public void teleopExit() {}

    /*****************/
    /*** TEST MODE ***/
    /*****************/

    @Override
    public void testInit() {
        mode = RobotMode.TEST;
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {}

    @Override
    public void testExit() {}
}
