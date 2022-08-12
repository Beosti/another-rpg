package main;

import object.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ui {

    GamePanel gp;
    Font arial_40, arial_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public Ui(GamePanel gp)
    {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);
        KeyObject keyObject = new KeyObject();
        keyImage = keyObject.image;
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D graphics2D)
    {
        if (gameFinished)
        {
            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize * 3);
            graphics2D.drawString(text, x, y);

            gp.gameThread = null;
        }
        else
        {
            graphics2D.setFont(arial_40);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            graphics2D.drawString("x " + gp.player.hasKey, 74, 65);

            // MESSAGE
            if (messageOn == true)
            {
                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
                graphics2D.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;
                if (messageCounter > 120)
                {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }


    }
}
