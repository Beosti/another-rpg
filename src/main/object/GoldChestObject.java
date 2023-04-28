package main.object;

import main.GamePanel;
import main.entity.Object;

public class GoldChestObject extends Object {
    //TODO make this a goldChest that requires a key and make a woodChest that doesn't require one
    public GoldChestObject(GamePanel gp)
    {
        super(gp);
        this.name = "Chest";

        collision = true;
        down = setupObject("gold_chest_sprite1", "chest");
    }

    /*
    @Override
    public void getImage() {
        down = setup("/chest/gold_chest_sprite1.png", "objects");
    }

     */
}
