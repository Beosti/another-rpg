package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// Entity class for all the entities in the game
public class Entity {

    public int worldX, worldY;
    public int speed;
    public String name;

    //Every sprite needed for a whole entity image in the game
    public BufferedImage idle_front, front_walking1, front_walking2,
                         idle_back, back_walking1, back_walking2,
                         idle_left, left_walking1, left_walking2,
                         idle_right, right_walking1, right_walking2;
    public String direction;

    //Variables for the sprite changing
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
