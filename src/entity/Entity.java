package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// Entity class for all the entities in the game
public abstract class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public String direction = "down";
    public int speed; // TODO should be changed to float
    public String name;

    //Every sprite needed for a whole entity image in the game
    public BufferedImage idle_front, front_walking1, front_walking2,
                         idle_back, back_walking1, back_walking2,
                         idle_left, left_walking1, left_walking2,
                         idle_right, right_walking1, right_walking2;

    //Variables for the sprite changing
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter=0;
    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;
    public BufferedImage image;
    public boolean collision = false;
    public int maxHealth;
    public int health;
    public boolean invincible = false;
    public int type; // 0 = player, 1 = npc, 2 monster;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public abstract void getImage();

    public void setAction() {};
    public void update() {
        setAction();
        collisionOn = false;
        gp.Checker.checkTile(this);
        gp.Checker.checkObject(this, false);
        gp.Checker.checkEntity(this, gp.NPC);
        gp.Checker.checkEntity(this, gp.Hostile);
        boolean checkPlayer = gp.Checker.checkPlayer(this);

        if (this.type == 2 && checkPlayer)
        {
            if (gp.player.invincible == false)
            {
                gp.player.health -= 1;
                gp.player.invincible = true;
            }
        }

        if (collisionOn == false)
        {
            switch (direction)
            {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 13)
        {
            if (spriteNumber == 1)
                spriteNumber = 2;
            else if (spriteNumber == 2)
                spriteNumber = 1;
            else if (spriteNumber == 3)
                spriteNumber = 1;
            spriteCounter = 0;
        }
    }
    public void speak() {
        switch (gp.player.direction)
        {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void draw(Graphics2D g2)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        //How does the player look in what direction
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1)
                    image = back_walking1;
                if (spriteNumber == 2)
                    image = back_walking2;
                if (spriteNumber == 3)
                    image = idle_back;
            }
            case "down" -> {
                if (spriteNumber == 1)
                    image = front_walking1;
                if (spriteNumber == 2)
                    image = front_walking2;
                if (spriteNumber == 3)
                    image = idle_front;
            }
            case "left" -> {
                if (spriteNumber == 1)
                    image = left_walking1;
                if (spriteNumber == 2)
                    image = left_walking2;
                if (spriteNumber == 3)
                    image = idle_left;
            }
            case "right" -> {
                if (spriteNumber == 1)
                    image = right_walking1;
                if (spriteNumber == 2)
                    image = right_walking2;
                if (spriteNumber == 3)
                    image = idle_right;
            }
        }
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
}
