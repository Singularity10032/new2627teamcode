// Copyright (c) 2026 Singularity (FRC 10032)
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.util;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.HIDType;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import org.littletonrobotics.junction.Logger;

/**
 * Drop-in replacement for {@code CommandXboxController} that detects the controller type from the
 * Driver Station (like our old AutoGamepad) and remaps sticks and buttons so every controller
 * behaves like an Xbox controller.
 *
 * <ul>
 *   <li>Xbox / Logitech in X-mode (DS reports XInput): standard Xbox mapping
 *   <li>PS4 / PS5 (DS reports HID gamepad): Cross = A, Circle = B, Square = X, Triangle = Y
 *   <li>Logitech in D-mode (DS reports HID joystick): 2 = A, 3 = B, 1 = X, 4 = Y
 * </ul>
 *
 * Stick values use the same sign convention as {@code XboxController} (pushing up is negative Y),
 * so code written for the AdvantageKit template works unchanged. The type is re-checked every loop,
 * so unplugging and swapping controllers works without restarting code.
 */
public class AutoController extends CommandGenericHID {
  /** Controller layouts we know how to map. */
  public enum Layout {
    XBOX,
    PS4,
    LOGITECH_DMODE
  }

  private final GenericHID hid;

  public AutoController(int port) {
    super(port);
    hid = getHID();
  }

  /** The detected layout. Unknown or disconnected controllers fall back to Xbox. */
  public Layout getLayout() {
    HIDType type = hid.getType();
    if (type == HIDType.kHIDGamepad) return Layout.PS4;
    if (type == HIDType.kHIDJoystick) return Layout.LOGITECH_DMODE;
    return Layout.XBOX;
  }

  /** Call once per loop so the detected layout shows up in AdvantageScope. */
  public void logLayout(String key) {
    Logger.recordOutput(key, getLayout().name());
  }

  // Sticks (Xbox sign convention: right = +X, up = -Y)

  public double getLeftX() {
    return hid.getRawAxis(0);
  }

  public double getLeftY() {
    return hid.getRawAxis(1);
  }

  public double getRightX() {
    return hid.getRawAxis(getLayout() == Layout.XBOX ? 4 : 2);
  }

  public double getRightY() {
    return switch (getLayout()) {
      case XBOX -> hid.getRawAxis(5);
      case PS4 -> hid.getRawAxis(5);
      case LOGITECH_DMODE -> hid.getRawAxis(3);
    };
  }

  // Face buttons, named by Xbox position

  /** Bottom face button: A (Xbox), Cross (PS4), 2 (Logitech). */
  public Trigger a() {
    return layoutButton(1, 2, 2);
  }

  /** Right face button: B (Xbox), Circle (PS4), 3 (Logitech). */
  public Trigger b() {
    return layoutButton(2, 3, 3);
  }

  /** Left face button: X (Xbox), Square (PS4), 1 (Logitech). */
  public Trigger x() {
    return layoutButton(3, 1, 1);
  }

  /** Top face button: Y (Xbox), Triangle (PS4), 4 (Logitech). */
  public Trigger y() {
    return layoutButton(4, 4, 4);
  }

  /** Left bumper: LB (Xbox), L1 (PS4), 5 (Logitech). */
  public Trigger leftBumper() {
    return layoutButton(5, 5, 5);
  }

  /** Right bumper: RB (Xbox), R1 (PS4), 6 (Logitech). */
  public Trigger rightBumper() {
    return layoutButton(6, 6, 6);
  }

  // D-pad: povUp(), povDown(), etc. are inherited from CommandGenericHID and are the same on
  // every controller type.

  private Trigger layoutButton(int xbox, int ps4, int logitech) {
    return new Trigger(
        () ->
            hid.getRawButton(
                switch (getLayout()) {
                  case XBOX -> xbox;
                  case PS4 -> ps4;
                  case LOGITECH_DMODE -> logitech;
                }));
  }
}
