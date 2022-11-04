package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


//Class for the player
public class Player extends Entity{

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    int invincibleCounter = 0;
    //Constructor
    public Player(GamePanel gp, KeyHandler keyHandler)
    {
        super(gp);
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 33;
        solidArea.height = 34;

        setDefaultValues();
        getImage();

    }

    public void setDefaultValues()
    {
        name = "Player";
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 45;
        speed = 2;
        direction = "down";

        // STATUS
        maxHealth = 6;
        health = 6;
    }

    //Frame images used for the player
    @Override
    public void getImage() {
        idle_front = setup("Player_goblin_front_idle", "player");
        front_walking1 = setup("Player_goblin_front_walking1", "player");
        front_walking2 = setup("Player_goblin_front_walking2", "player");
        idle_back = setup("Player_goblin_back_idle", "player");
        back_walking1 = setup("Player_goblin_back_walking1", "player");
        back_walking2 = setup("Player_goblin_back_walking2", "player");
        idle_left = setup("Player_goblin_side_left_idle", "player");
        left_walking1 = setup("Player_goblin_side_left_walking1", "player");
        left_walking2 = setup("Player_goblin_side_left_walking2", "player");
        idle_right = setup("Player_goblin_side_right_idle", "player");
        right_walking1 = setup("Player_goblin_side_right_walking1", "player");
        right_walking2 = setup("Player_goblin_side_right_walking2", "player");

    }

    //What can the player do at every update of the game
    public void update()
    {
        //Handling of the movement
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed)
        {
            if (keyHandler.upPressed)
                direction = "up";
            else if (keyHandler.downPressed)
                direction = "down";
            else if (keyHandler.leftPressed)
                direction = "left";
            else if (keyHandler.rightPressed)
                direction = "right";


            collisionOn = false;
            gp.Checker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.Checker.checkObject(this, true);
            pickUpObject(objIndex);

            // NPC COLLISION
            int npcIndex = gp.Checker.checkEntity(this, gp.NPC);
            interactNPC(npcIndex);

            int hostileIndex = gp.Checker.checkEntity(this, gp.Hostile);
            contactHostile(hostileIndex);

            gp.eventHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
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
        else
            spriteNumber = 3;

        // This needs to be outside of key if statement
        if (invincible == true)
        {
            invincibleCounter++;
            if(invincibleCounter > 60)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpObject(int i)
    {
        if (i != 999)
        {

        }
    }
    public void interactNPC(int i)
    {
        if (i != 999)
        {
            if (gp.keyHandler.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.NPC[i].speak();
            }
        }
        gp.keyHandler.enterPressed = false;
    }

    public void contactHostile(int i)
    {
        if (i != 999)
        {
            if (invincible == false)
            {
                health -= 1;
                invincible = true;
            }
        }
    }


    //Putting the images in the game
    public void draw(Graphics2D g2)
    {

        BufferedImage image = null;

        //How does the player look in what direction
        switch (direction)
        {
            case "up":
                if (spriteNumber == 1 )
                    image = back_walking1;
                if (spriteNumber == 2)
                    image = back_walking2;
                if (spriteNumber == 3)
                    image = idle_back;
                break;
            case "down":
                if (spriteNumber == 1)
                    image = front_walking1;
                if (spriteNumber == 2)
                    image = front_walking2;
                if (spriteNumber == 3)
                    image = idle_front;
                break;
            case "left":
                if (spriteNumber == 1)
                    image = left_walking1;
                if (spriteNumber == 2)
                    image = left_walking2;
                if (spriteNumber == 3)
                    image = idle_left;
                break;
            case  "right":
                if (spriteNumber ==  1)
                    image = right_walking1;
                if (spriteNumber == 2)
                    image = right_walking2;
                if (spriteNumber == 3)
                    image = idle_right;
                break;
        }

        if (invincible == true)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
