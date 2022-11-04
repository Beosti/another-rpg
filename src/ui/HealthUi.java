package ui;

import main.GamePanel;

import javax.imageio.ImageIO;

public class HealthUi extends Screen{

    GamePanel gp;

    public HealthUi(GamePanel gp)
    {
        this.gp = gp;


        state1 = setup("fullheart_texture", "health");
        state2 = setup("halfheart_texture", "health");
        state3 = setup("emptyheart_texture", "health");
    }
}
