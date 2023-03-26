package main.ui;

import main.GamePanel;
import main.api.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Screen {
    GamePanel gp;
    public BufferedImage state1, state2, state3;

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
}
