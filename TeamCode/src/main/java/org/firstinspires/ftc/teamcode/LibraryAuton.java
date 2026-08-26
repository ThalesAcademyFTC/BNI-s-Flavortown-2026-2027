package org.firstinspires.ftc.teamcode;

public class LibraryAuton {

    public LibraryMain LibraryMain;

    double ticksPerInch = 50;

    public LibraryAuton(LibraryMain LibraryMain) {

        this.LibraryMain = LibraryMain;

    }
    public void moveForward(double inches, double speed){

        int TickTarget = (int) Math.round(inches * ticksPerInch);
    }


}
