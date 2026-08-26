package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LibraryMain {
    public HardwareMap hwMap;

    public LinearOpMode auton;

    public enum Drive {

        MAIN,

        TEST
    }

    public Drive drive;

    public Telemetry telemetry;

    public DcMotor motorFL, motorFR, motorBL, motorBR;

    public static DcMotor[] allMotors;
    public Servo servo1;

    public boolean leftToggleToggle;

    public LibraryMain(OpMode opMode, Drive drive) {

        this.hwMap = opMode.hardwareMap;

        this.drive = drive;

        this.telemetry = opMode.telemetry;

        setUpHardware();

    }

    public LibraryMain(LinearOpMode opMode, Drive type) {

        this.auton = opMode;

        hwMap = opMode.hardwareMap;

        telemetry = opMode.telemetry;

        drive = type;

        setUpHardware();
    }

    public void resetDriveEncoders() {

        for (DcMotor x : allMotors) {

            x.setPower(0);

        }

    }

    public void setUpHardware() {

        switch (drive) {

            case MAIN:

                //declaring name of motors to hwMap

                motorFL = hwMap.dcMotor.get("motorFL");
                motorFR = hwMap.dcMotor.get("motorFR");
                motorBL = hwMap.dcMotor.get("motorBL");
                motorBR = hwMap.dcMotor.get("motorBR");

                //declaring name of servos to hwMap

                motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
                motorBL.setDirection(DcMotorSimple.Direction.REVERSE);

                servo1 = hwMap.servo.get("servo1");
        }

    }

   // public void resetDriveEncoders() {}
}