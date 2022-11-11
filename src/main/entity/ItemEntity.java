package main.entity;

import main.GamePanel;
import main.object.item.Item;

public class ItemEntity extends Entity{
    Item item;
    public ItemEntity(GamePanel gp, Item item) {
        super(gp);
        this.item = item;
        down = item.down;
    }
    public void setItem(Item item)
    {
        this.item = item;
    }

    public Item getItem()
    {
        return this.item;
    }
}
