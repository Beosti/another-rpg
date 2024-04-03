package main.ui;

import main.GamePanel;
import main.api.screen.ScreenHelper;
import main.init.ModValues;

import java.awt.*;

public class PauseScreen extends Screen {

    public PauseScreen() {

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        String text = "PAUSED";
        int x = ScreenHelper.getXforCenteredText(graphics2D, ModValues.SCREEN_WIDTH, text);
        int y = ModValues.SCREEN_HEIGHT/2;

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 80F));
        graphics2D.drawString(text, x, y);
    }
}
