package main;

import object.KeyObject;
import ui.HealthUi;
import ui.Screen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Ui {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    BufferedImage heart_full, heart_half, heart_empty;
    public Ui(GamePanel gp)
    {
        this.gp = gp;

        try
        {
            InputStream is = getClass().getResourceAsStream("/font/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/PurisaBold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        }   catch (FontFormatException | IOException e)
        {
            e.printStackTrace();
        }
        Screen healthUi = new HealthUi(gp);
        heart_full = healthUi.state1;
        heart_half = healthUi.state2;
        heart_empty = healthUi.state3;
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleScreenState)
        {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState)
        {
            drawPlayerLife();
        }
        else if (gp.gameState == gp.pauseState)
        {
            drawPauseScreen();
            drawPlayerLife();
        }
        else if (gp.gameState == gp.dialogueState)
        {
            drawDialogueScreen();
            drawPlayerLife();
        }
    }
    public void drawTitleScreen()
    {
        //TODO change the background to the map and seeing npcs and stuff
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45));
        String text = "Little goblin commits federal treason";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        // SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);
        // MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "New Game";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0)
        {
            g2.drawString(">", x-gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Load Game";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1)
        {
            g2.drawString(">", x-gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Quit Game";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2)
        {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
    public void drawPauseScreen()
    {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen()
    {
        // WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2 + 395;
        int width = gp.screenWidth - (gp.tileSize*3);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;


        for (String line : currentDialogue.split("\n"))
        {
            g2.drawString(currentDialogue, x - 20, y);
            y += 40;
        }
    }
    public void drawPlayerLife()
    {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // BLANK HEART
        while (i < gp.player.maxHealth/2)
        {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while (i < gp.player.health)
        {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.health)
            {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0, 0, 0, 220);
        g2.setColor(c);
        g2.fillRoundRect(x , y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
