package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class KeyObject extends SuperObject{

    public KeyObject()
    {
        this.name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key_sprite.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
