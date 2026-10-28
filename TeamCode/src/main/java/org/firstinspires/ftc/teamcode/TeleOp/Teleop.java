package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Library.LibraryAuton;
import org.firstinspires.ftc.teamcode.Library.LibraryCamera;
import org.firstinspires.ftc.teamcode.Library.LibraryMain;
import org.firstinspires.ftc.teamcode.Library.LibrarySensor;
import org.firstinspires.ftc.teamcode.Library.LibraryTeleOp;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;

@TeleOp
public class Teleop extends OpMode {

    public LibraryMain libraryMain;

    public LibraryTeleOp libraryTeleOp;

     public LibrarySensor librarySensor;

     public LibraryCamera libraryCamera;

     public LibraryAuton libraryAuton;

     public AprilTagPoseFtc pose;

     double speed = .4;

     boolean BOOlean = true;

     public void init() {

         libraryMain = new LibraryMain(this, LibraryMain.Drive.MAIN);

         librarySensor = new LibrarySensor(libraryMain);

         libraryAuton = new LibraryAuton(libraryMain, libraryTeleOp);

         libraryCamera = new LibraryCamera(this, libraryMain,librarySensor,libraryAuton);

         libraryTeleOp = new LibraryTeleOp(libraryMain);

         libraryCamera.initializeAprilTag();
     }


     public void loop() {

         pose = libraryCamera.tagReferencePositionFromBlue();

         //no comment

         double x = (gamepad1.left_stick_x*.75);
         double y = (-gamepad1.left_stick_y*.75);
         double turn = (gamepad1.right_stick_x*.75);

         libraryTeleOp.robotMovement(x,y,turn);

     }
 }
