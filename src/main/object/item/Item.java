package main.object.item;

import main.GamePanel;
import main.api.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item {

    GamePanel gp;

    public int attackValue;
    public int defenseValue;
    public String name = "";
    public BufferedImage down;
    public String description = "";
    public Item(GamePanel gp)
    {
        this.gp = gp;


    }

    public String getDescription()
    {
        return "[" + name + "]\n" + description;
    }
    public BufferedImage setup(String imageName, String packageName)
    {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/" + packageName + "/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(getClass().getResourceAsStream("/items/" + packageName + "/" + imageName + ".png").toString() + " not found");
        }
        return image;
    }
}
