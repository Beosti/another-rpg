package main.object;

import main.entity.Entity;
import main.GamePanel;

public class DoorObject extends Entity {

    public DoorObject(GamePanel gp)
    {
        super(gp);
        this.name = "Door";

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    @Override
    public void getImage() {
        idle_front = setup("/door/door_sprite.png", "objects");
    }
}
