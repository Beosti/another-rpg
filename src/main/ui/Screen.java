package main.ui;

import main.GamePanel;
import main.api.UtilityTool;
import main.entity.npcs.NPCEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Screen {
    public GamePanel gp;
    public Graphics2D g2;
    public Font font;

    boolean screenOpened;
    public Screen(Graphics2D g2, GamePanel gp)
    {
        this.gp = gp;
        this.g2 = g2;
    }
    public BufferedImage setup(String imageName, String packageName)
    {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ui/" + packageName + "/" + imageName + ".png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }

    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void drawSubWindow(int x, int y, int width, int height)
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
