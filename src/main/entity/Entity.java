package main.entity;

import main.GamePanel;
import main.api.GameValues;
import main.api.UtilityTool;
import main.data.quest.Quest;
import main.object.item.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Entity class for all the entities in the game
public abstract class Entity {
    GamePanel gp;
    public List<Quest> quests;
    // STATS
    public String name;

    public BufferedImage down;


    // COLLISION
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public Rectangle solidArea = new Rectangle(0, 0, 32, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean collision = false;

    public int worldX, worldY;
    public String direction = "down";
    public Entity(GamePanel gp)
    {
        this.gp = gp;
        quests = new ArrayList<>();
    }
    public List<Quest> getQuests() {
        return quests;
    }

    public void addQuest(Quest quest)
    {
        quests.add(quest);
    }

    public void removeQuest(Quest quest)
    {
        quests.remove(quest);
    }
    public void getImage() {};

    public void setAction() {};


    public void draw(Graphics2D g2)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = down;
        // Draws the tiles specifically around the player, so spare on efficiency
        if (worldX  + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX  - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY  + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY  - gp.tileSize < gp.player.worldY + gp.player.screenY)
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);


    }
    public BufferedImage setup(String imageName, String packageName)
    {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/entities/" + packageName + "/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
    public BufferedImage setupCustom(String imageName, String packageName, int width, int height)
    {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/entities/" + packageName + "/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, width, height);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
}
