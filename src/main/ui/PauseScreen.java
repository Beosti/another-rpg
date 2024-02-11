package main.ui;

import main.GamePanel;

import java.awt.*;

public class PauseScreen extends Screen {

    public PauseScreen(Graphics2D g2, GamePanel gp) {
        super(g2, gp);
        this.font = g2.getFont().deriveFont(Font.PLAIN, 80F);
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.setFont(this.font);
        g2.drawString(text, x, y);
    }
}
