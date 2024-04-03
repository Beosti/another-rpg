package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Fonts {

    public Font maruMonica;

    public Fonts() throws IOException, FontFormatException {
        InputStream is = getClass().getResourceAsStream("/font/MaruMonica.ttf");
        maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
    }
}
