package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DoorObject extends SuperObject {

    public DoorObject()
    {
        this.name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door_sprite.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        collision = true;
    }
}
