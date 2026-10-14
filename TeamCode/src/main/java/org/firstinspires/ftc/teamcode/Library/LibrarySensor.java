package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;

public class LibrarySensor {

    public LibraryMain LibraryMain;

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

    public LibrarySensor(LibraryMain LibraryMain) {

        this.LibraryMain = LibraryMain;

    }

    public colorValues isColorRGB() {

        NormalizedRGBA rgba = LibraryMain.colorSensor.getNormalizedColors();

        int red = LibraryMain.colorSensor.red();
        int blue = LibraryMain.colorSensor.blue();
        int green = LibraryMain.colorSensor.green();
        int alpha = LibraryMain.colorSensor.alpha();

        colorValues color = colorValues.UNKNOWN;

        if ((red) > 200) {

            color = colorValues.RED;

        } else if (((blue) > 100) && ((red > 70) && alpha > 50)) {

            color = colorValues.PURPLE;

        }

        return color;
    }



}
