package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
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

    public Servo servo1;

    public boolean leftToggleToggle;

    public LibraryMain(OpMode opMode, Drive drive) {

        this.hwMap = opMode.hardwareMap;

        this.drive = drive;

        this.telemetry = opMode.telemetry;

    }

    public LibraryMain(LinearOpMode opMode, Drive type) {

        this.auton = opMode;

        hwMap = opMode.hardwareMap;

        telemetry = opMode.telemetry;

        drive = type;
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

                servo1 = hwMap.servo.get("servo1");
        }

    }

   // public void resetDriveEncoders() {}
}