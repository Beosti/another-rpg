package main.object;

import main.entity.Entity;
import main.GamePanel;
import main.entity.Object;

public class ChestObject extends Object {

    public ChestObject(GamePanel gp)
    {
        super(gp);
        this.name = "Chest";

        collision = true;
        down = setupObject("chest_sprite1", "chest");
    }

    /*
    @Override
    public void getImage() {
        down = setup("/chest/chest_sprite1.png", "objects");
    }

     */
}
