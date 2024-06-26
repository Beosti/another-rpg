package main.object.item;

import main.GamePanel;
import main.api.DamageCalculation;
import main.api.UtilityTool;
import main.init.ModValues;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item {

    GamePanel gp;
    public boolean hasEquipped = false;

    public String name = "";
    public BufferedImage down;
    public String description = "";
    public Item(GamePanel gp)
    {
        this.gp = gp;
    }


    public BufferedImage getDown()
    {
        return this.down;
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
            image = utilityTool.scaleImage(image, ModValues.TILE_SIZE, ModValues.TILE_SIZE);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(getClass().getResourceAsStream("/items/" + packageName + "/" + imageName + ".png").toString() + " not found");
        }
        return image;
    }

}
