package org.firstinspires.ftc.teamcode.Library;

import static org.firstinspires.ftc.teamcode.Library.LibraryMain.allMotors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class LibraryAuton {

    public LibraryMain LibraryMain;

    double ticksPerInch = 50;

    public LibraryAuton(LibraryMain LibraryMain) {

        this.LibraryMain = LibraryMain;

    }
    public void moveForward(double inches, double speed){

        int TickTarget = (int) Math.round(inches * ticksPerInch);

        LibraryMain.resetDriveEncoders();

        LibraryMain.motorBL.setTargetPosition(TickTarget);
        LibraryMain.motorBR.setTargetPosition(TickTarget);
        LibraryMain.motorFL.setTargetPosition(TickTarget);
        LibraryMain.motorFR.setTargetPosition(TickTarget);

        for (DcMotor x : allMotors){
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }


/*        driverCentricMovement.driverMovement(0, speed, 0);     */

        for (DcMotor x : allMotors) {
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        LibraryMain.waitForMotors();

        LibraryMain.resetDriveEncoders();
    }

    public void moveBackward(double inches, double speed){
        moveForward(-inches, speed);
    }

    public void moveRight(double inches, double speed){

        int TickTarget = (int) Math.round(inches * ticksPerInch);

        LibraryMain.resetDriveEncoders();

        LibraryMain.motorBL.setTargetPosition(-TickTarget);
        LibraryMain.motorBR.setTargetPosition(TickTarget);
        LibraryMain.motorFL.setTargetPosition(TickTarget);
        LibraryMain.motorFR.setTargetPosition(-TickTarget);

        for (DcMotor x : allMotors){
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }


        /*        driverCentricMovement.driverMovement(0, speed, 0);     */

        for (DcMotor x : allMotors) {
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        LibraryMain.waitForMotors();

        LibraryMain.resetDriveEncoders();

    }

    public void moveLeft(double inches, double speed){
        moveRight(-inches, speed);
    }


}
