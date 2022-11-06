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
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    //Every sprite needed for a whole entity image in the game
    public BufferedImage idle_front, front_walking1, front_walking2,
                         idle_back, back_walking1, back_walking2,
                         idle_left, left_walking1, left_walking2,
                         idle_right, right_walking1, right_walking2;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

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
    boolean attacking = false;
    int invincibleCounter = 0;
    public boolean alive = true;
    public boolean dying = false;
    public int dyingCounter = 0;
    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public abstract void getImage();

    public void setAction() {};
    public void update() {
        setAction();
        collisionOn = false;
        Graphics2D Graphics2D = null;
        Graphics2D g2 = null;

        gp.Checker.checkTile(this);
        gp.Checker.checkObject(this, false);
        gp.Checker.checkEntity(this, gp.NPC);
        gp.Checker.checkEntity(this, gp.Hostile);
        boolean checkPlayer = gp.Checker.checkPlayer(this);

        if (invincible)
        {
            invincibleCounter++;
            if(invincibleCounter > 40)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (this.type == 2 && checkPlayer)
        {
            if (!gp.player.invincible)
            {
                gp.player.health -= 1;
                gp.player.invincible = true;
            }
        }

        if (!collisionOn)
        {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
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
    public void dyingAnimation(Graphics2D g2)
    {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        if (dyingCounter > i && dyingCounter <= i *2) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if (dyingCounter > i*2 && dyingCounter <= i * 3) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        if (dyingCounter > i*3 && dyingCounter <= i * 4) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if (dyingCounter > i*4)
        {
            System.out.println("Died");
            dying = false;
            alive = false;
        }
    }
    public void speak() {
        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
    public void draw(Graphics2D g2)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;
        if (dying == true)
        {
            dyingAnimation(g2);
        }
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
        // MONSTER HP BAR
        if (type == 2)
        {
            double oneScale = (double) gp.tileSize/maxHealth;
            double healthBarValue = oneScale*health;

            if (health < maxHealth && !dying)
            {
                g2.setColor(new Color(150, 255, 255));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) healthBarValue, 10);
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
