# Singularity FRC 10032 — 2026-27 Robot Code

Swerve-only base for the 2026-27 season, built on the StuyPulse code base (`com.singularity.lib`).

## What's here
- `lib/` — the StuyPulse library (streams, filters, gamepads, math, network helpers).
- `robot/subsystems/swerve/` — CTRE Phoenix 6 swerve on the `CANIVORE` bus.
  - `TunerConstants.java` holds CAN IDs, wheel offsets and the Pigeon config.
  - The Pigeon 2 (ID 56) is mounted upside down, so it is given a 180° mount-pose roll.
- `robot/commands/swerve/` — field-centric drive, heading reset, X-mode, init commands.

## Driving
Field-centric: stick forward always drives away from the driver, whichever way the robot faces.
- **D-pad Up** — reset heading. Point the robot's front away from you first.
- **D-pad Down** (hold) — X-mode (lock wheels).

## Deploy
Open this folder in WPILib VS Code, connect to the robot (team 10032), and run **WPILib: Deploy Robot Code** (Shift+F5).
