package main.entity;

import main.GamePanel;
import main.object.item.Item;

import java.awt.image.BufferedImage;

public class ItemEntity extends Entity{
    BufferedImage down;
    public ItemEntity(GamePanel gp, Item item) {
        super(gp);
        down = item.down;
    }
}
