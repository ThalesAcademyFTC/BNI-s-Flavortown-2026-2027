package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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

    public RevColorSensorV3 colorSensor;

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

    public void setUpHardware() {
        switch (drive) {

            case MAIN:

                //declaring name of motors to hwMap

                motorFL = (DcMotor) hwMap.dcMotor.get("motorFL");
                motorFR = (DcMotor) hwMap.dcMotor.get("motorFR");
                motorBL = (DcMotor) hwMap.dcMotor.get("motorBL");
                motorBR = (DcMotor) hwMap.dcMotor.get("motorBR");

                allMotors =  new DcMotor[] {motorBL, motorBR, motorFL, motorFR};



                //declaring name of servos to hwMap

                motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
                motorBL.setDirection(DcMotorSimple.Direction.REVERSE);

                servo1 = hwMap.servo.get("servo1");

                colorSensor = (RevColorSensorV3) hwMap.colorSensor.get("colorSensor");
        }


    }

    public void waitForMotors() {
        boolean finished = false;
        while (auton.opModeIsActive() && !finished && !auton.isStopRequested()) {
            if (motorFL.isBusy() || motorFR.isBusy() || motorBL.isBusy() || motorBR.isBusy()) {
                telemetry.addData("front left encoder:", "%7d / % 7d", motorFL.getCurrentPosition(), motorFL.getTargetPosition());
                telemetry.addData("back left encoder:", "%7d / % 7d", motorBL.getCurrentPosition(), motorBL.getTargetPosition());
                telemetry.addData("front right encoder:", "%7d / % 7d", motorFR.getCurrentPosition(), motorFR.getTargetPosition());
                telemetry.addData("back right encoder:", "%7d / % 7d", motorBR.getCurrentPosition(), motorBR.getTargetPosition());
                telemetry.update();
            } else {
                finished = true;
            }
        }
    }

    public void resetDriveEncoders() {

        for (DcMotor x : allMotors) {

            x.setPower(0);
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            x.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

}