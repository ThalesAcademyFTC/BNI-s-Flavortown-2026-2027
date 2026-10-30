package org.firstinspires.ftc.teamcode.Library;

import static org.firstinspires.ftc.teamcode.Library.LibraryMain.allMotors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class LibraryAuton {

    public LibraryMain libraryMain;

    public LibraryTeleOp libraryTeleOp;

    double ticksPerInch = 50;

    double ticksPerDegree = (double) 500 /45;

    public LibraryAuton(LibraryMain LibraryMain, LibraryTeleOp libraryTeleOp) {

        this.libraryMain = LibraryMain;

        this.libraryTeleOp = libraryTeleOp;

    }
    public enum Direction {

        FORWARD,

        BACKWARD,

        LEFT,

        RIGHT;

    }

    public enum diagonalMove {

        FR,

        FL,

        BL,

        BR;

    }
    public void moveForward(double inches, double speed){

        int TickTarget = (int) Math.round(inches * ticksPerInch);

        libraryMain.resetDriveEncoders();

        libraryMain.motorBL.setTargetPosition(TickTarget);
        libraryMain.motorBR.setTargetPosition(TickTarget);
        libraryMain.motorFL.setTargetPosition(TickTarget);
        libraryMain.motorFR.setTargetPosition(TickTarget);

        for (DcMotor x : allMotors){
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }


        libraryTeleOp.robotMovement(0, speed, 0);

        for (DcMotor x : allMotors) {
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        libraryMain.waitForMotors();

        libraryMain.resetDriveEncoders();
    }

    public void moveBackward(double inches, double speed){
        moveForward(-inches, speed);
    }

    public void moveRight(double inches, double speed){

        int TickTarget = (int) Math.round(inches * ticksPerInch);

        libraryMain.resetDriveEncoders();

        libraryMain.motorBL.setTargetPosition(-TickTarget);
        libraryMain.motorBR.setTargetPosition(TickTarget);
        libraryMain.motorFL.setTargetPosition(TickTarget);
        libraryMain.motorFR.setTargetPosition(-TickTarget);

        for (DcMotor x : allMotors){
            x.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }


        libraryTeleOp.robotMovement(speed, 0, 0);

        for (DcMotor x : allMotors) {
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        libraryMain.waitForMotors();

        libraryMain.resetDriveEncoders();

    }

    public void moveLeft(double inches, double speed){
        moveRight(-inches, speed);
    }

    public void turnRight(double degrees, double speed){

        int TickTarget = (int) Math.round(degrees * ticksPerDegree);

        libraryMain.resetDriveEncoders();

        libraryMain.motorFL.setTargetPosition(TickTarget);
        libraryMain.motorFR.setTargetPosition(-TickTarget);
        libraryMain.motorBL.setTargetPosition(TickTarget);
        libraryMain.motorBR.setTargetPosition(-TickTarget);

        libraryTeleOp.robotMovement(0,0, speed);

        for (DcMotor x : allMotors) {

            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }


        libraryMain.waitForMotors();

        libraryMain.resetDriveEncoders();


    }

    public void turnLeft (double degrees, double speed) {turnRight(-degrees, speed); }

    public void moveDiagonal(diagonalMove direction, double inches, double speed) {

        if (direction == diagonalMove.FR) {

            move(Direction.FORWARD, inches, speed);
            move(Direction.RIGHT, inches, speed);

        } else if (direction == diagonalMove.FL) {

            move(Direction.FORWARD, inches, speed);
            move(Direction.LEFT, inches,speed);

        } else if (direction == diagonalMove.BL) {

            move(Direction.BACKWARD, inches, speed);
            move(Direction.LEFT, inches, speed);
        } else {

            move(Direction.BACKWARD, inches, speed);
            move(Direction.RIGHT, inches, speed);

        }

    }

    public void move(Direction direction, double inches, double speed) {

        if (direction == Direction.FORWARD) {

            moveForward(inches, speed);

        } else if (direction == Direction.BACKWARD) {

            moveBackward(inches, speed);

        } else if (direction == Direction.RIGHT) {

            moveRight(inches, speed);

        } else {

            moveLeft(inches, speed);

        }

    }

    public void turn(Direction direction, double degrees, double speed) {

        if (direction == Direction.RIGHT) {

            turnRight(degrees, speed);

        } else {

            turnLeft(degrees, speed);

        }

    }

    /*
    -launcher
    -color sensor (specifically important colors like blue)
    -april tag function (finding the location of the hive?)
    -Liner upper 2.0 ^
    -direction functions
    -odometry
     */
}
