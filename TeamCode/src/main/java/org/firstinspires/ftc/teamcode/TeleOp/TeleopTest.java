package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Library.LibraryMain;
import org.firstinspires.ftc.teamcode.Library.LibraryTeleOp;


@TeleOp
public class TeleopTest extends OpMode {

    public LibraryMain main;
    public LibraryTeleOp libraryTeleOp;

    double speed = 1;


    public void init() {

        main = new LibraryMain(this, LibraryMain.Drive.MAIN);

        libraryTeleOp = new LibraryTeleOp(main);

    }


    public void loop() {

        double x = (gamepad1.left_stick_x);
        double y = (-gamepad1.left_stick_y);
        double turn = (gamepad1.right_stick_x*.5);

        libraryTeleOp.robotMovement(x,y,turn);
    }
}
