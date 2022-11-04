package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

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
