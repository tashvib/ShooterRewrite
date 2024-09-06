// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import frc.robot.utils.TalonFactory;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.InstantCommand;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Shooter extends SubsystemBase {

  public enum ShooterState {
    OFF, SPEEDING, AT_SPEED;
  }

  private ShooterState state;

  private TalonFX leftMotor; // left //rolling motor that keeps running at a certain speed so that we can
                             // shoot the game piece at a fast speed
  private TalonFX rightMotor; // right //rolling motor that keeps running at a certain speed so that we can
                              // shoot the game piece at a fast speed

  private double targetSpeed;

  // Creates a PIDController with gains kP, kI, and kD
  private PIDController pid;
  /** Creates a new Shooter. */
  public Shooter() {
    pid = new PIDController(Constants.ShooterConstants.kP, Constants.ShooterConstants.kI, Constants.ShooterConstants.kD);

    targetSpeed = Constants.ShooterConstants.TargetSpeed;
    state = ShooterState.OFF;
    leftMotor = TalonFactory.createTalonFX(Constants.ShooterConstants.kLeftShooterMotorId, false, "Drivetrain");
    rightMotor = TalonFactory.createTalonFX(Constants.ShooterConstants.kRightShooterMotorId, true, "Drivetrain");

    // Configure the TalonFX for basic use
    // TalonFXConfiguration configs = new TalonFXConfiguration();
    // This TalonFX should be configured with a kP of 1, a kI of 0, a kD of 10, and a kV of 2 on slot 0
    // configs.Slot0.kP = Constants.ShooterConstants.kP;
    // configs.Slot0.kI = Constants.ShooterConstants.kI;
    // configs.Slot0.kD = Constants.ShooterConstants.kD;
    
    // // Write these configs to the TalonFX
    // leftMotor.getConfigurator().apply(configs);
    // rightMotor.getConfigurator().apply(configs);

    leftMotor.setPosition(0);
    rightMotor.setPosition(0);


    // leftMotor.setControl(new VelocityVoltage(Constants.ShooterConstants.TargetSpeed));
    // leftMotor.setControl(new VelocityVoltage(Constants.ShooterConstants.TargetSpeed));
  }

  @Override
  public void periodic() {

    switch (state) {
      case OFF:
        setShooterSpeedBoth(0);
        break;

      case SPEEDING:
        if (reachedTargetSpeed()) {
          state = ShooterState.AT_SPEED;
        } else {
          setShooterSpeedLeft(pid.calculate(getSpeedLeft(), targetSpeed));
          setShooterSpeedRight(pid.calculate(getSpeedRight(), targetSpeed));
        }
        break;

      case AT_SPEED:
        setShooterSpeedBoth(targetSpeed);
        if (!reachedTargetSpeed()) {
          state = ShooterState.SPEEDING;
        }
        break;
    }
  }


 
  public void setShooterSpeedBoth(double speed) {
    leftMotor.set(speed);
    rightMotor.set(speed);
  }


  public void setShooterSpeedLeft(double speed) {
    leftMotor.set(speed);
  }
  public void setShooterSpeedRight(double speed) {
    rightMotor.set(speed);
  }

  public double getSpeedLeft() {
    return leftMotor.getVelocityAsDouble();
  }

  public double getSpeedRight() {
    return rightMotor.getVelocityAsDouble();
  }

  public boolean reachedTargetSpeed() {
    return (getSpeedLeft() <= targetSpeed + Constants.ShooterConstants.TargetSpeedError &&
        getSpeedLeft() >= targetSpeed - Constants.ShooterConstants.TargetSpeedError &&
        getSpeedRight() <= targetSpeed + Constants.ShooterConstants.TargetSpeedError &&
        getSpeedRight() >= targetSpeed - Constants.ShooterConstants.TargetSpeedError);
  }

  public void setTargetSpeed(double kspeakershootingspeed) {
    targetSpeed = kspeakershootingspeed;
  }
}
