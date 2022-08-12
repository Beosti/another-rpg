package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


//Class for the player
public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    //Constructor
    public Player(GamePanel gp, KeyHandler keyHandler)
    {
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 30;
        solidAreaDefaultX = solidArea.x;
        solidAeaDefaultY = solidArea.y;
        solidArea.width = 33;
        solidArea.height = 30;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues()
    {
        name = "Player";
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 45;
        speed = 5;
        direction = "down";
    }

    //Frame images used for the player
    public void getPlayerImage()
    {
        try{
            idle_front = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_front_idle.png"));
            front_walking1 = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_front_walking1.png"));
            front_walking2 = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_front_walking2.png"));
            idle_back = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_back_idle.png"));
            back_walking1 = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_back_walking1.png"));
            back_walking2 = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_back_walking2.png"));
            idle_left = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_side_left_idle.png"));
            left_walking1 = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_side_left_walking1.png"));
            left_walking2 = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_side_left_walking2.png"));
            idle_right = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_side_right_idle.png"));
            right_walking1 = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_side_right_walking1.png"));
            right_walking2 = ImageIO.read(getClass().getResourceAsStream("/entities/player/Player_goblin_side_right_walking2.png"));

        }catch (IOException e)
        {
            e.printStackTrace();
        }
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
    }

    public void pickUpObject(int i)
    {
        if (i != 999)
        {
            String objectName = gp.object[i].name;
            switch (objectName)
            {
                case "Key":
                    hasKey++;
                    gp.object[i] = null;
                    gp.ui.showMessage("Picked up a key");
                    break;
                case "Door":
                    if (hasKey > 0)
                    {
                        gp.object[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door");
                    }
                    else
                        gp.ui.showMessage("You need a key");

                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    // gp.playerSE(play cool music);
                    break;
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
