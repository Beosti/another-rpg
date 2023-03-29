package main.entity;

import main.GamePanel;
import main.api.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Object extends Entity{
    public Object(GamePanel gp) {
        super(gp);
    }

    public BufferedImage setupObject(String imageName, String packageName)
    {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/entities/objects/" + packageName + "/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(getClass().getResourceAsStream("/entities/objects/" + packageName + "/" + imageName + ".png").toString() + " not found");
        }
        return image;
    }
}
