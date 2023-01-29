package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick operator = new Joystick(1);

    /* Drive Controls */
    // private final int translationAxis = XboxController.Axis.kLeftY.value;
    // private final int strafeAxis = XboxController.Axis.kLeftX.value;
    // private final int rotationAxis = XboxController.Axis.kRightX.value;
    private final int translationAxis = 1;
    private final int strafeAxis = 0;
    private final int rotationAxis = 2;

    /* Driver Buttons */
    private final JoystickButton zeroCumulativeGyros = new JoystickButton(driver, 2); // A
    private final JoystickButton zeroGyro = new JoystickButton(driver, 3); // B
    private final JoystickButton robotCentric = new JoystickButton(driver, 5); // Left Bumper
    private final JoystickButton autoBalance = new JoystickButton(driver, 1); // X

    private final JoystickButton limiter = new JoystickButton(driver, 4); // Y


    // private final JoystickButton toggleArm = new JoystickButton(driver, 6);
    // private final JoystickButton toggleClaw = new JoystickButton(driver, 7);
    // private final JoystickButton armUpButton = new JoystickButton(driver, 4);
    // private final JoystickButton armDownButton = new JoystickButton(driver, 8);

    /*operator buttons */
    private final JoystickButton armUpButton = new JoystickButton(operator, 5); //left bumper (extend arm forward)
    private final JoystickButton armDownButton = new JoystickButton(operator, 6); //right bumper (extend arm backward)
    private final JoystickButton toggleArm = new JoystickButton(operator, 7); //left trigger (arm piston)
    private final JoystickButton toggleClaw = new JoystickButton(operator, 8); //right trigger (claw piston)

    /* Subsystems */
    public final Swerve s_Swerve = new Swerve();
    public final static SolenoidTesting solenoidTesting = new SolenoidTesting();
    public static final Arm arm = new Arm();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        CommandScheduler.getInstance().registerSubsystem(arm);
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    public void configureMoreButtonBindings(int num) {
        zeroCumulativeGyros.onTrue(new InstantCommand(() -> s_Swerve.resetCumulativeModules(num)));
        // toggleSolenoids.onTrue(new ToggleSolenoid());
        // toggleSolenoids.onTrue(new InstantCommand(() -> solenoidTesting.toggleSolenoid()));
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        autoBalance.onTrue(new InstantCommand(() -> s_Swerve.autoBalance()));
        toggleArm.onTrue(new InstantCommand(() -> solenoidTesting.toggleArm()));
        toggleClaw.onTrue(new InstantCommand(() -> solenoidTesting.toggleClaw()));
        limiter.onTrue(new InstantCommand(() -> s_Swerve.limit()));
        armUpButton.whileTrue(new armUp());
        armDownButton.whileTrue(new armDown());
        
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new exampleAuto(s_Swerve);
    }
}
