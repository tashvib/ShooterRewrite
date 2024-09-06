// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static class ShooterConstants {
    public static final int kLeftShooterMotorId = 31;
    public static final int kRightShooterMotorId = 4;
    public static final double TargetSpeed = 0.5;
    public static final double ShooterSpeed = 0.5;
    public static final double kSpeakerShootingSpeed = 0.5; //0.7;
    public static final double TargetSpeedError = 0.1;
    public static final double kP = 0.02;
    public static final double kI = 0;
    public static final double kD = 0;
  }
  public static class IntakeConstants {
    public static final double TargetIntakeSpeed = 0;
    public static final double IntakeSpeedError = 0;
    public static final double kP = 0.0;
    public static final double kI = 0.0;
    public static final double kD = 0.0;
  }
  public static class TalonConstants {
    public static final int talonFXTicks = 2048;
    public static final int talonSRXTicks = 4096;

    public static final double MAX_VOLTAGE = 10.0;

    public static final int kPIDIdx = 0;
    public static final int kTimeoutMs = 10;
    public static final boolean kIsPracticeBot = false;
    public static final double kVoltageComp = 10.0;
    public static final double kCurrentLimit = 35;
    public static final double kDriveCurrentLimit = 40;
    public static final double kSteerCurrentLimit = 60;
}
}
