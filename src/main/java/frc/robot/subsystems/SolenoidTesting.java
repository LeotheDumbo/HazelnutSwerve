package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SolenoidTesting extends SubsystemBase {

    private final PneumaticsModuleType type = PneumaticsModuleType.CTREPCM;

    public DoubleSolenoid solenoidOne;
    public DoubleSolenoid solenoidTwo;




    public SolenoidTesting(){
        this.solenoidOne = new DoubleSolenoid(type, 0, 1);
        this.solenoidTwo = new DoubleSolenoid(type, 2, 3);
    }

    public void toggleSolenoid() {
        if (this.solenoidOne.get().equals(DoubleSolenoid.Value.kForward)) {
            retractSolenoid();
        } else {
            extendSolenoid();
        }
      }
    
      public void extendSolenoid() {
        this.solenoidOne.set(DoubleSolenoid.Value.kForward);
        this.solenoidTwo.set(DoubleSolenoid.Value.kForward);
      }
    
      public void retractSolenoid() {
        this.solenoidOne.set(DoubleSolenoid.Value.kReverse);
        this.solenoidTwo.set(DoubleSolenoid.Value.kReverse);
      }

      public void toggleArm(){ //arm
        if(this.solenoidOne.get().equals(DoubleSolenoid.Value.kForward)){
          this.solenoidOne.set(DoubleSolenoid.Value.kReverse);
        }
        else{
          this.solenoidOne.set(DoubleSolenoid.Value.kForward);
        }
      }

      public void toggleClaw(){ //claw
        if(this.solenoidTwo.get().equals(DoubleSolenoid.Value.kForward)){
          this.solenoidTwo.set(DoubleSolenoid.Value.kReverse);
        }
        else{
          this.solenoidTwo.set(DoubleSolenoid.Value.kForward);
        }
      }

}