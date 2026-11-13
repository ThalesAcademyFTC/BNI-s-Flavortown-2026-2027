package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Library.LibraryAuton;
import org.firstinspires.ftc.teamcode.Library.LibraryCamera;
import org.firstinspires.ftc.teamcode.Library.LibraryMain;
import org.firstinspires.ftc.teamcode.Library.LibrarySensor;
import org.firstinspires.ftc.teamcode.Library.LibraryTeleOp;

import java.util.List;


@TeleOp
public class TeleopTest extends OpMode {

    public LibraryMain libraryMain;
    public LibraryTeleOp libraryTeleOp;
    public LibraryCamera libraryCamera;
    public LibrarySensor librarySensor;

    public LibraryAuton libraryAuton;

    double speed = 1;

    Limelight3A limelight;


    public void init() {

        libraryMain = new LibraryMain(this, LibraryMain.Drive.MAIN);

        libraryTeleOp = new LibraryTeleOp(libraryMain);

        libraryAuton = new LibraryAuton(libraryMain, libraryTeleOp);

        librarySensor = new LibrarySensor(libraryMain, libraryTeleOp, libraryAuton);

        libraryCamera = new LibraryCamera(this, libraryMain, librarySensor, libraryAuton, limelight);


        limelight = hardwareMap.get(Limelight3A.class, "Webcam");
        limelight.setPollRateHz(100); // Sets to check for data 100 times every second
        limelight.start(); // Tells Limelight to start looking
    }


    public void loop() {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            double tx = result.getTx(); // How far left or right the target is (degrees)
            double ty = result.getTy(); // How far up or down the target is (degrees)
            double ta = result.getTa(); // How big the target looks (0%-100% of the image)
            telemetry.addData("Target X", tx);
            telemetry.addData("Target Y", ty);
            telemetry.addData("Target Area", ta);
            telemetry.addData("April Tag: ", result);
        } else {
            telemetry.addData("Limelight", "No Targets");
        }
    }
}
