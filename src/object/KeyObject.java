package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class KeyObject extends Entity {

    public KeyObject(GamePanel gp)
    {
        super(gp);
        this.name = "Key";
        idle_front = setup("/key/key_sprite.png", "objects");

        collision = true;
    }
}
