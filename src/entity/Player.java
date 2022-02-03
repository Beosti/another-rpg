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

    //Constructor
    public Player(GamePanel gp, KeyHandler keyHandler)
    {
        this.gp = gp;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues()
    {
        name = "Player";
        x = 100;
        y = 100;
        speed = 2;
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
            {

                direction = "up";
                y -= speed;
            }
            else if (keyHandler.downPressed)
            {
                direction = "down";
                y += speed;
            }
            else if (keyHandler.leftPressed)
            {
                direction = "left";
                x -= speed;
            }
            else if (keyHandler.rightPressed)
            {
                direction = "right";
                x += speed;
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

    //Putting the images in the game
    public void draw(Graphics2D g2)
    {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

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

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
