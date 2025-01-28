package main.ui;

import main.GamePanel;
import main.api.screen.ScreenHelper;
import main.init.ModValues;

import java.awt.*;

public class CharacterScreen extends Screen {

    public CharacterScreen(GamePanel gamePanel)
    {
        this.gp = gamePanel;
        this.setName("characterscreen");
    }

    @Override
    public void draw(Graphics2D g2) {
        // FRAME of the actual inventory
        int frameX = ModValues.TILE_SIZE;
        int frameY = ModValues.TILE_SIZE / 2;
        int frameWidth = ModValues.TILE_SIZE*6;
        int frameHeight = ModValues.TILE_SIZE*9;
        ScreenHelper.drawWindow(g2, frameX, frameY, frameWidth, frameHeight);
    }
}
