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

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
        String name = "Name:";
        g2.drawString(name, frameX + 12, frameY + 36);
        String race = "Race:";
        g2.drawString(race, frameX + 12, frameY + 68);
        String rightHand = "Right hand: ";
        g2.drawString(rightHand, frameX + 12, frameY + 100);
        String itemName = "";
        if (gp.playerEntity.getItemInHand() == null)
            itemName = "empty";
        else itemName = gp.playerEntity.getItemInHand().name;
        g2.drawString(itemName, frameX + 12, frameY + 132);
        String leftHand = "Left hand: ";
        g2.drawString(leftHand, frameX + 12, frameY + 164);

    }
}
