package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class LibrarySensor {

    public LibraryMain libraryMain;

    public LibraryTeleOp libraryTeleOp;

    public LibraryAuton libraryAuton;

    public enum colorValues {

        PURPLE,

        YELLOW,

        BLUE,

        RED,

        GREEN,

        PINK,

        CANNOT_DETECT_COLOR,

        UNKNOWN

    }

    public colorValues colors;

    public LibrarySensor(LibraryMain libraryMain, LibraryTeleOp libraryTeleOp, LibraryAuton libraryAuton) {

        this.libraryMain = libraryMain;

        this.libraryAuton = libraryAuton;

        this.libraryTeleOp = libraryTeleOp;

    }

    public double distanceFrom() {

        return libraryMain.distanceSensor.getDistance(DistanceUnit.CM);

    }

    public void turnUntilPollen(double turnDistance, double distance) {

         if (isColorRGB() != colorValues.YELLOW) {

             if (((turnDistance <= distanceFrom() + 2) && (turnDistance >= distanceFrom() - 2))) {

                 libraryAuton.moveForward(distance,.5);

             } else if (turnDistance <= distanceFrom() + 2) {

                 libraryAuton.turn(LibraryAuton.Direction.LEFT, 1, .3);

             } else {

                 libraryAuton.turn(LibraryAuton.Direction.RIGHT, 1, .3);

             }
         }
    }
    //Liam, Sophia [NO MIDDLE NAME] Foster, and Ryan
    public colorValues isColorRGB() {

        NormalizedRGBA rgba = libraryMain.colorSensor.getNormalizedColors();

        int red = libraryMain.colorSensor.red();
        int blue = libraryMain.colorSensor.blue();
        int green = libraryMain.colorSensor.green();
        int alpha = libraryMain.colorSensor.alpha();

        colorValues color = colorValues.UNKNOWN;

        if ((red) > 200) {

            color = colorValues.RED;

        } else if (((blue) > 100) && ((red > 70) && alpha > 50)) {

            color = colorValues.PURPLE;

        } else if (blue > 200) {

            color = colorValues.BLUE;

        } else if ((red > blue) && (green < 100)) {

            color = colorValues.YELLOW;

        }

        return color;
    }



}
