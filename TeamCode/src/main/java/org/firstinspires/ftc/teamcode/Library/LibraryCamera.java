package org.firstinspires.ftc.teamcode.Library;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Library.LibraryMain;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;


public class LibraryCamera {

    public LibraryMain libraryMain;

    public HardwareMap hwMap;

    public AprilTagProcessor aprilTag;

    public OpMode opMode;

    public LinearOpMode linearOpMode;

    public LibrarySensor librarySensor;

    public LibraryAuton libraryAuton;

    public Telemetry telemetry;

    final static double armLength = 0;

    double widthObject = 2.8;

    public LibraryCamera (OpMode opMode, LibraryMain libraryMain, LibrarySensor librarySensor, LibraryAuton libraryAuton) {

        this.hwMap = opMode.hardwareMap;

        this.libraryMain = libraryMain;

        this.librarySensor = librarySensor;

        this.libraryAuton = libraryAuton;

        this.opMode = opMode;

    }

    public LibraryCamera (LinearOpMode linearOpMode, LibraryMain libraryMain, LibrarySensor librarySensor, LibraryAuton libraryAuton) {

        this.hwMap = linearOpMode.hardwareMap;

        this.libraryMain = libraryMain;

        this.librarySensor = librarySensor;

        this.libraryAuton = libraryAuton;

        this.linearOpMode = linearOpMode;

    }

    public enum detectedId {

        id_30,

        id_31,

        id_32,

        id_33,

        id_34,

        id_35,

        id_36,

        id_37,

        id_38,

        id_39,

        id_40,

        id_41,

        id_42,

        id_43,

        id_44,

        id_45,

        UNKNOWN

    }

    public static final boolean useWebcam = true;

    VisionPortal.Builder builder = new VisionPortal.Builder();

    private Position cameraPosition = new Position(DistanceUnit.INCH,
            5, 2, 3.75, 0);
    private YawPitchRollAngles cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES,
            0, -40, 0, 0);


    public void initializeAprilTag() {

        aprilTag = new AprilTagProcessor.Builder().setCameraPose(cameraPosition, cameraOrientation).build();

        if (useWebcam) {
            builder.setCamera(hwMap.get(WebcamName.class, "Webcam 1"));
            //visionPortal = VisionPortal.easyCreateWithDefaults(hwMap.get(WebcamName.class, "Logitech Webcam"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        Size size = new Size(1280, 720);

        builder.addProcessor(aprilTag).setCameraResolution(size);

        //builder.addProcessor(aprilTag);

        VisionPortal visionPortal = builder.build();


    }

    public detectedId detectID() {
        detectedId idDetected = detectedId.UNKNOWN;
        ArrayList<org.firstinspires.ftc.vision.apriltag.AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            if (detection.id == 30) {
                idDetected = detectedId.id_30;
            } else if (detection.id == 31) {
                idDetected = detectedId.id_31;
            } else if (detection.id == 32) {
                idDetected = detectedId.id_32;
            } else if (detection.id == 33) {
                idDetected = detectedId.id_33;
            } else if (detection.id == 34) {
                idDetected = detectedId.id_34;
            } else if (detection.id == 35) {
                idDetected = detectedId.id_35;
            } else if (detection.id == 36) {
                idDetected = detectedId.id_36;
            } else if (detection.id == 37) {
                idDetected = detectedId.id_37;
            } else if (detection.id == 38) {
                idDetected = detectedId.id_38;
            } else if (detection.id == 39) {
                idDetected = detectedId.id_39;
            } else if (detection.id == 40) {
                idDetected = detectedId.id_40;
            } else if (detection.id == 41) {
                idDetected = detectedId.id_41;
            } else if (detection.id == 42) {
                idDetected = detectedId.id_42;
            } else if (detection.id == 43) {
                idDetected = detectedId.id_43;
            } else if (detection.id == 44) {
                idDetected = detectedId.id_44;
            } else if (detection.id == 45) {
                idDetected = detectedId.id_45;
            } else {
                libraryMain.telemetry.addData("Could not detect the motif pattern due to problem scanning april tag", detection.id);
            }
        }
        return idDetected;
    }

    public Pose3D tagReferencePosition() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        Pose3D tagOrientation = null;
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                libraryMain.telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                // Only use tags that don't have Obelisk in them
                if (!detection.metadata.name.contains("Obelisk")) {
                    tagOrientation = detection.robotPose;
                    libraryMain.telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)",
                            detection.robotPose.getPosition().x,
                            detection.robotPose.getPosition().y,
                            detection.robotPose.getPosition().z));
                    libraryMain.telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)",
                            detection.robotPose.getOrientation().getPitch(AngleUnit.DEGREES),
                            detection.robotPose.getOrientation().getRoll(AngleUnit.DEGREES),
                            detection.robotPose.getOrientation().getYaw(AngleUnit.DEGREES)));
                }
            }
            else {
                libraryMain.telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                libraryMain.telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }
        return tagOrientation;
    }

    public AprilTagPoseFtc tagReferencePositionFromBlue() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagPoseFtc tagOrientation = null;
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                libraryMain.telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                // Only use tags that don't have Obelisk in them
                if (detection.metadata.name.contains("Blue")) {
                    tagOrientation = detection.ftcPose;
                    libraryMain.telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)",
                            detection.ftcPose.x,
                            detection.ftcPose.y,
                            detection.ftcPose.z));
                    libraryMain.telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)",
                            detection.ftcPose.pitch,
                            detection.ftcPose.roll,
                            detection.ftcPose.yaw));
                }
            }
            else {
                libraryMain.telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                libraryMain.telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }
        return tagOrientation;
    }

    public AprilTagPoseFtc tagReferencePositionFromRed() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        AprilTagPoseFtc tagOrientation = null;
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                libraryMain.telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                // Only use tags that don't have Obelisk in them
                if (detection.metadata.name.contains("Red")) {
                    tagOrientation = detection.ftcPose;
                    libraryMain.telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)",
                            detection.ftcPose.x,
                            detection.ftcPose.y,
                            detection.ftcPose.z));
                    libraryMain.telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)",
                            detection.ftcPose.pitch,
                            detection.ftcPose.roll,
                            detection.ftcPose.yaw));
                }
            }
            else {
                libraryMain.telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                libraryMain.telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }
        return tagOrientation;
    }



    public void triangulatePosition(double speed) {
        AprilTagPoseFtc pose1;

        //possibly include diagonal movement if ya nasty


        //formula: (d*tan((90-pose.bearing) - pose.bearing) - (Width + Arm Length)

        while (!linearOpMode.isStopRequested()) {

            pose1 = tagReferencePositionFromBlue();
            // double turnDistance = (90 - pose1.bearing) + pose1.bearing;

            if (librarySensor.isColorRGB() == (LibrarySensor.colorValues.BLUE) || (librarySensor.isColorRGB() == LibrarySensor.colorValues.RED)) {

                double nectarDistance = pose1.x * Math.tan((90 - pose1.bearing) - pose1.bearing) - (3.6 + 0);

                /*

                libraryAuton.turn(turnDistance);

                Need to determine which way to turn in the function

                 */

                libraryAuton.moveForward(nectarDistance, speed);

                //moving towards nectar

                telemetry.addData("Distance from nectar: ", nectarDistance);

            } else {

                double pollenDistance = pose1.x * Math.tan((90 - pose1.bearing) - pose1.bearing) - (2.8 + 0);

                /*

                libraryAuton.turn(turnDistance);

                Need to determine which way to turn in the function

                 */

                libraryAuton.moveForward(pollenDistance, speed);

                //moving towards pollen

                telemetry.addData("Distance from pollen: ", pollenDistance);

            }
        }
    }
}
