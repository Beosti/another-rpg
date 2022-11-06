package main.object;

import main.entity.Entity;
import main.GamePanel;

public class ChestObject extends Entity {

    public ChestObject(GamePanel gp)
    {
        super(gp);
        this.name = "Chest";

        collision = true;
    }

    @Override
    public void getImage() {
        idle_front = setup("/chest/chest_sprite.png", "objects");
    }
}
