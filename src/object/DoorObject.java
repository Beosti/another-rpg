package object;

import entity.Entity;
import main.GamePanel;

public class DoorObject extends Entity {

    public DoorObject(GamePanel gp)
    {
        super(gp);
        this.name = "Door";
        idle_front = setup("/door/door_sprite.png", "objects");

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
