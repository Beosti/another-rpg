package main.entity;

import java.awt.image.BufferedImage;

// Entity class for all the entities in the game
public class Entity {

    public int x, y;
    public int speed;
    public String name;

    public BufferedImage idle_front, front_walking1, front_walking2,
                         idle_back, back_walking1, back_walking2,
                         idle_left, left_walking1, left_walking2,
                         idle_right, right_walking1, right_walking2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;
}
