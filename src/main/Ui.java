package main;

import object.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ui {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public Ui(GamePanel gp)
    {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);

    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState)
        {

        }
        else if (gp.gameState == gp.pauseState)
        {
            drawPauseScreen();
        }
    }
    public void drawPauseScreen()
    {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
