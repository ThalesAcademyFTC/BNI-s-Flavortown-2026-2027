package org.firstinspires.ftc.teamcode.Library;

import static org.firstinspires.ftc.teamcode.Library.LibraryMain.allMotors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class LibraryOdometry {

    public LibraryMain libraryMain;

    public LibraryTeleOp libraryTeleOp;

    public LibraryAuton libraryAuton;

    double deltaX;

    double deltaY;

    double phi;

    double x_pos = 0;

    double y_pos = 0;

    double heading = 0;

    double trackwidth = 12; //idk the width of the robot

    double forwardOffset = 0; //idk the offset

    double preLeftEncoderPos = 0;
    double preRightEncoderPos = 0;
    double preCenterEncoderPos = 0;

    public LibraryOdometry(LibraryMain libraryMain, LibraryTeleOp libraryTeleOp, LibraryAuton libraryAuton) {

        this.libraryMain = libraryMain;

        this.libraryTeleOp = libraryTeleOp;

        this.libraryAuton = libraryAuton;

    }

    public void readOdometryWheels() {

        double posLeft  = libraryMain.odometryComputer.readSinglePosition_Caching(libraryMain.LEFT_ODOM);
        double posRight = libraryMain.odometryComputer.readSinglePosition_Caching(libraryMain.RIGHT_ODOM);
        double posCenter  = libraryMain.odometryComputer.readSinglePosition_Caching(libraryMain.CENTER_ODOM);
        double velLeft  = libraryMain.odometryComputer.readSingleVelocity_Caching(libraryMain.LEFT_ODOM);
        double velRight = libraryMain.odometryComputer.readSingleVelocity_Caching(libraryMain.RIGHT_ODOM);
        double velCenter  = libraryMain.odometryComputer.readSingleVelocity_Caching(libraryMain.CENTER_ODOM);

        libraryMain.telemetry.addData("Left Position from start : ", posLeft);
        libraryMain.telemetry.addData("Right Position from start : ", posRight);
        libraryMain.telemetry.addData("Center Position from start : ", posCenter);
        libraryMain.telemetry.addData("Left Velocity: ", velLeft);
        libraryMain.telemetry.addData("Right Velocity: ", velRight);
        libraryMain.telemetry.addData("Center Velocity: ", velCenter);

    }

    public void distanceFromStart() {

        double leftEncoderPose = libraryMain.odometryComputer.readSinglePosition_Caching(libraryMain.LEFT_ODOM);
        double rightEncoderPose = libraryMain.odometryComputer.readSinglePosition_Caching(libraryMain.RIGHT_ODOM);
        double centerEncoderPose = libraryMain.odometryComputer.readSinglePosition_Caching(libraryMain.CENTER_ODOM);

        double deltaLeftEncoderPos = leftEncoderPose - preLeftEncoderPos;
        double deltaRightEncoderPos = rightEncoderPose - preRightEncoderPos;
        double deltaCenterEncoderPos = centerEncoderPose - preCenterEncoderPos;

        phi = (deltaLeftEncoderPos - deltaRightEncoderPos) / trackwidth;
        double delta_middle_pos = (deltaLeftEncoderPos + deltaRightEncoderPos) / 2;
        double delta_perp_pos = deltaCenterEncoderPos - forwardOffset * phi;

        deltaX = delta_middle_pos * Math.cos(heading) - delta_perp_pos * Math.sin(heading);
        deltaY = delta_middle_pos * Math.sin(heading) + delta_perp_pos * Math.cos(heading);

        x_pos += deltaX;
        y_pos += deltaY;
        heading += phi;

        preLeftEncoderPos = leftEncoderPose;
        preRightEncoderPos = rightEncoderPose;
        preCenterEncoderPos = centerEncoderPose;

        libraryMain.telemetry.addData("X position from 0: ", x_pos);
        libraryMain.telemetry.addData("Y position from 0: ", y_pos);
        libraryMain.telemetry.addData("Heading/Angle from 0: ", heading);
    }
}
