package main.api.screen;

import java.awt.*;

public class ScreenHelper {



    public static int getXforCenteredText(Graphics2D g2, int screenWidth, String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = screenWidth/2 - length/2;
        return x;
    }

    public static void drawSubWindow(Graphics2D g2, int x, int y, int width, int height)
    {
        Color c = new Color(0, 0, 0, 220);
        g2.setColor(c);
        g2.fillRoundRect(x , y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
}
