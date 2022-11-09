package main.object;

import main.entity.Entity;
import main.GamePanel;


public class KeyObject extends Entity {

    public KeyObject(GamePanel gp)
    {
        super(gp);
        this.name = "Key";
        collision = true;

        getImage();
    }

    @Override
    public void getImage() {
        down = setup("key/key_sprite", "objects");
    }
}
