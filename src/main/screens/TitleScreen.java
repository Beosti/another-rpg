package main.screens;

import main.GamePanel;
import main.GameState;
import main.IKeyHandling;
import main.Main;
import main.api.screen.ScreenHelper;
import main.init.ModValues;
import main.ui.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TitleScreen extends Screen implements IKeyHandling {

    private byte column = 0;
    GamePanel gamePanel;
    public TitleScreen(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    @Override
    public void init(int keyCode) {
        if (keyCode == KeyEvent.VK_S && column != 2)
            column += 1;
        if (keyCode == KeyEvent.VK_Z && column != 0)
            column -= 1;
        if (keyCode == KeyEvent.VK_ENTER && column == 0) {
            gamePanel.ui.setScreen(null);
            gamePanel.gameState = GameState.PLAY_STATE;
        }

    }

    @Override
    public void draw(Graphics2D g2)
    {
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, ModValues.SCREEN_WIDTH, ModValues.SCREEN_HEIGHT);

        g2.setFont(Main.fonts.maruMonica);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45));
        String text = "Little goblin commits federal treason";
        int x = ScreenHelper.getXforCenteredText(g2, ModValues.SCREEN_WIDTH, text);
        int y = ModValues.TILE_SIZE*3;

        // SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);
        // MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "New Game";
        x = ScreenHelper.getXforCenteredText(g2, ModValues.SCREEN_WIDTH, text);
        y += ModValues.TILE_SIZE*4;
        g2.drawString(text, x, y);

        g2.drawString(">", x-ModValues.TILE_SIZE, y + (ModValues.TILE_SIZE * column));

        /*
        if (commandNum == 0)
        {
            g2.drawString(">", x-ModValues.TILE_SIZE, y);
        }

         */

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Load Game";
        x = ScreenHelper.getXforCenteredText(g2, ModValues.SCREEN_WIDTH, text);
        y += ModValues.TILE_SIZE;
        g2.drawString(text, x, y);
        /*
        if (commandNum == 1)
        {
            g2.drawString(">", x-ModValues.TILE_SIZE, y);
        }

         */

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Quit Game";
        x = ScreenHelper.getXforCenteredText(g2, ModValues.SCREEN_WIDTH, text);
        y += ModValues.TILE_SIZE;
        g2.drawString(text, x, y);
        /*
        if (commandNum == 2)
        {
            g2.drawString(">", x-ModValues.TILE_SIZE, y);
        }

         */
    }
}

