package org.firstinspires.ftc.teamcode;

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

}
