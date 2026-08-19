package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;



import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LibraryMain {

    public HardwareMap hwMap;

    public enum Drivetrain {

        MECHANUM,

        TEST

    }

    public Drivetrain drive;

    public Telemetry telemetry;

    public DcMotorEx motorFL, motorFR, motorBL, motorBR;

    public LibraryMain (OpMode opMode, Drivetrain drive){

        this.hwMap = opMode.hardwareMap;

        this.drive = drive;

        this.telemetry = opMode.telemetry;

        setUpHardware();
    }

    public void setUpHardware() {
        switch (drive) {
            case MECHANUM:
                motorFL = (DcMotorEx) hwMap.dcMotor.get("motorFL");
                motorFR = (DcMotorEx) hwMap.dcMotor.get("motorFR");
                motorBR = (DcMotorEx) hwMap.dcMotor.get("motorBR");
                motorBL = (DcMotorEx) hwMap.dcMotor.get("motorBL");

        }
    }
}
