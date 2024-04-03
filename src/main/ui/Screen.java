package main.ui;

import main.GamePanel;
import main.api.UtilityTool;
import main.entity.npcs.NPCEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Screen {
    public GamePanel gp;
    public Font font;

    boolean screenOpened;
    public Screen()
    {

    }
    public abstract void draw(Graphics2D g2);
    public BufferedImage setup(String imageName, String packageName)
    {
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
}
