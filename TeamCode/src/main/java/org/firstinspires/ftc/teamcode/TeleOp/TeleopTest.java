package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Library.LibraryAuton;
import org.firstinspires.ftc.teamcode.Library.LibraryCamera;
import org.firstinspires.ftc.teamcode.Library.LibraryMain;
import org.firstinspires.ftc.teamcode.Library.LibrarySensor;
import org.firstinspires.ftc.teamcode.Library.LibraryTeleOp;


@TeleOp
public class TeleopTest extends OpMode {

    public LibraryMain libraryMain;
    public LibraryTeleOp libraryTeleOp;
    public LibraryCamera libraryCamera;
    public LibrarySensor librarySensor;

    public LibraryAuton libraryAuton;

    double speed = 1;


    public void init() {

        libraryMain = new LibraryMain(this, LibraryMain.Drive.MAIN);

        libraryTeleOp = new LibraryTeleOp(libraryMain);

        libraryAuton = new LibraryAuton(libraryMain, libraryTeleOp);

        librarySensor = new LibrarySensor(libraryMain, libraryTeleOp, libraryAuton);

        libraryCamera = new LibraryCamera(this, libraryMain, librarySensor, libraryAuton);

        libraryCamera.initializeAprilTag();
    }


    public void loop() {

        libraryCamera.detectID();

    }
}
