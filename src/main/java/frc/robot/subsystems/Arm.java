// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private final WPI_TalonSRX MOTORONE;
  private final WPI_TalonSRX MOTORTWO;

  public Arm() {
    MOTORONE = new WPI_TalonSRX(15);
    MOTORTWO = new WPI_TalonSRX(13);
    configureMotors();
    MOTORONE.setInverted(true);
    MOTORTWO.setInverted(true);
  }

  public void armUp(double speed){
    MOTORONE.set(speed);
    MOTORTWO.set(speed);
  }

  private void configureMotors(){
    MOTORONE.configFactoryDefault();
    MOTORTWO.configFactoryDefault();
    MOTORONE.setNeutralMode(NeutralMode.Brake);
    MOTORTWO.setNeutralMode(NeutralMode.Brake);
  }

  public void armDown(double speed){
    MOTORONE.set(-speed);
    MOTORTWO.set(-speed);
  }

  public void stopMotors(){
    MOTORONE.stopMotor();
    MOTORTWO.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
