package org.firstinspires.ftc.teamcode.Auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Library.LibraryAuton;
import org.firstinspires.ftc.teamcode.Library.LibraryCamera;
import org.firstinspires.ftc.teamcode.Library.LibraryMain;
import org.firstinspires.ftc.teamcode.Library.LibrarySensor;
import org.firstinspires.ftc.teamcode.Library.LibraryTeleOp;


@Autonomous
public class AutonTest extends LinearOpMode {

    public LibraryMain libraryMain;

    public LibraryAuton libraryAuton;

    public LibraryCamera libraryCamera;

    public LibrarySensor librarySensor;

    public LibraryTeleOp libraryTeleOp;

    double speed = .5;

    public void runOpMode() {

        libraryMain = new LibraryMain(this, LibraryMain.Drive.MAIN);

        libraryAuton = new LibraryAuton(libraryMain, libraryTeleOp);

        libraryCamera = new LibraryCamera(this ,libraryMain, librarySensor, libraryAuton);

        librarySensor = new LibrarySensor(libraryMain);

        libraryTeleOp = new LibraryTeleOp(libraryMain);

        waitForStart();

        libraryAuton.moveForward(10, .5);

        sleep(500);

        libraryAuton.move(LibraryAuton.Direction.FORWARD, 10, .5);

        sleep(500);
//twas the night of halloween eve
        libraryAuton.moveDiagonal(LibraryAuton.diagonalMove.FL, 10, .5);
    }
}
