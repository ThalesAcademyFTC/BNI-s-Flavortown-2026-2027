package org.firstinspires.ftc.teamcode.Library;


//we love billy dignam
public class LibraryTeleOp {

    public LibraryMain main;

    public LibraryTeleOp (LibraryMain libMain) {

        this.main = libMain;
    }

    public void robotMovement(double x, double y, double turn){

        double denominator = Math.max(Math.abs(y)+ Math.abs(x) + Math.abs(turn), 1);

        double motorFLPower = (y + x + turn) / denominator;
        double motorBLPower = (y - x + turn) / denominator;
        double motorFRPower = (y - x - turn) / denominator;
        double motorBRPower = (y + x - turn) / denominator;

        main.motorFL.setPower(motorFLPower);
        main.motorBL.setPower(motorBLPower);
        main.motorFR.setPower(motorFRPower);
        main.motorBR.setPower(motorBRPower);
    }
}   //leftDoubleDouble
// robot.explode();


