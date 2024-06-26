package main.ui;

import main.GamePanel;
import main.GameState;
import main.api.GameValues;
import main.api.screen.ScreenHelper;
import main.entity.npcs.NPCEntity;
import main.object.item.weapons.WeaponItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Ui {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    public String currentDialogue = "";
    public boolean choiceDialogue = false;
    public NPCEntity questEntityNPC = null;
    public boolean acceptQuest = false;
    public int commandNum = 0;
    BufferedImage heart_full, heart_half, heart_empty;
    ArrayList<String> messageScroll = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public int slotCol = 0;
    public int slotRow = 0;
    public int subSlotCol = 0;
    public int subSlotRow = 0;
    public int dialogueCol = 0;

    public boolean drawItemInfo = false;
    private Screen currentScreen = null;
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
    }

    public Screen getCurrentScreen()
    {
        return this.currentScreen;
    }
    public void closeScreen()
    {
        this.currentScreen = null;
    }
    public boolean hasScreen()
    {
        if (currentScreen != null)
            return true;
        return false;
    }
    public void setScreen(Screen screen)
    {
        this.currentScreen = screen;
    }
    /*

    public void addMessage(String text)
    {
        messageScroll.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        PauseScreen pauseScreen = new PauseScreen(g2, gp);

        g2.setFont(maruMonica);
        g2.setColor(Color.white);
        /*
        if (gp.gameState.equals(GameState.TITLE_SCREEN))
        {
            drawTitleScreen();
        }
        if (GameState.updateState(gp.gameState))
        {
            drawPlayerLife();
            drawMessageScroll();
        }
        else if (gp.gameState.equals(GameState.PAUSE_STATE))
        {
            pauseScreen.drawPauseScreen();
            drawPlayerLife();
        }
        else if (gp.gameState == GameValues.PLAYER_INVENTORY)
        {
            drawPlayerLife();
            inventoryScreen.drawInventory(g2);
            itemInfoScreen.drawItemInfo(g2);
        }
        else if (gp.gameState == GameValues.PLAYER_STATS_INVENTORY)
        {
            drawPlayerLife();
            characterScreen();
            drawInventory();
            drawItemInfo();

        }


    }

    public void drawItemInfo() // TODO equip and unequip items
    {
        if (!drawItemInfo)
            return;
        int itemIndex = getItemIndexOnSlot();
        if (itemIndex > gp.player.inventory.size())
        {
            this.drawItemInfo = false;
            return;
        }
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize * 8 + 22;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 3;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        if (itemIndex < gp.player.inventory.size())
        {
            if (gp.player.inventory.get(itemIndex).hasEquipped)
                g2.drawString("UNEQUIP", gp.tileSize * 9 + 35, gp.tileSize * 9 + 35);
            else
                g2.drawString("EQUIP", gp.tileSize * 9 + 35, gp.tileSize * 9 + 35);
        }
        /*
            if (gp.player.firstHand != null && gp.player.firstHand.hasEquipped)
                g2.drawString("UNEQUIP", gp.tileSize * 9 + 35, gp.tileSize * 9 + 35);
            else
                g2.drawString("EQUIP", gp.tileSize * 9 + 35, gp.tileSize * 9 + 35);


        //g2.drawString("EQUIP", gp.tileSize * 9 + 35, gp.tileSize * 9 + 35);
        g2.drawString("DROP", gp.tileSize * 9 + 35, gp.tileSize * 10 + 35);
        g2.drawString("USE", gp.tileSize * 11 + 70, gp.tileSize * 9 + 35);
        g2.drawString("EXIT", gp.tileSize * 11 + 63, gp.tileSize * 10 + 35);

        // SLOT
        final int slotXstart = frameX + 22;
        final int slotYstart = frameY + 24;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;
        // CURSOR
        int cursorX = (int) (slotXstart + (slotSize * subSlotCol) * 2.5);
        int cursorY = slotYstart + (slotSize * subSlotRow);
        if (subSlotCol == 0 && subSlotRow == 0) // equip
            ;
        else if (subSlotCol == 0 && subSlotRow == 1) // drop
            ;
        else if (subSlotCol == 1 && subSlotRow == 0) // use
            ;
        else if (subSlotCol == 1 && subSlotRow == 1) // exit
            ;
        int cursorWidth = gp.tileSize * 2;
        int cursorHeight = gp.tileSize;
        // DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }

    public void drawInventory()
    {
        // FRAME
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize / 2;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        // CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        // DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);


        // DRAW ITEMS
        for (int i = 0; i <gp.player.inventory.size(); i++)
        {
            g2.drawImage(gp.player.inventory.get(i).down, slotX, slotY, null);
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14)
            {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
        // DESCRIPTION
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(26F));

        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size())
        {
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for (String line: gp.player.inventory.get(itemIndex).getDescription().split("\n"))
            {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
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
        System.out.println(gp.player.getQuests());

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
        if (choiceDialogue)
        {
            int buttonWidth = 100;
            int buttonHeight = 40;

            int shiftChangeButtons = 300;

            final int slotXstart = x + 6 + shiftChangeButtons;
            int slotSize = gp.tileSize + 96;
            int cursorX = slotXstart + (slotSize * dialogueCol);


            // Draw the accept button
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            drawSubWindow(cursorX, y - 2, buttonWidth, buttonHeight);
            g2.drawString("Accept", x + 20 + shiftChangeButtons, y + 27);
            if (this.acceptQuest)
            {
                gp.player.addInProgressQuests(this.questEntityNPC.getQuests().get(0));
                this.currentDialogue = "Thank you for this, come back when you ready!";
                this.acceptQuest = false;
                this.choiceDialogue = false;
            }
            // Draw the decline button
            x += buttonWidth + 50;
            g2.drawString("Decline", x + 12 + shiftChangeButtons, y + 27);
        }
    }

    public void characterScreen()
    {
        // CREATE A FRAME
        final int frameX = gp.tileSize * 2 - 85;
        final int frameY = gp.tileSize + 40;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 32;

        // NAMES
        g2.drawString("Level: " + gp.player.getLevel(), textX, textY);
        textY += lineHeight;
        g2.drawString("Experience: " + gp.player.getLevel().getCurrentExperience() + "/" + gp.player.getLevel().getMaxExperience(), textX, textY);
        textY += lineHeight;
        g2.drawString("Health: " + gp.player.getHealth().getCurrentHealth() + "/" + gp.player.getHealth().getMaxHealth(), textX, textY);
        textY += lineHeight;
        g2.drawString("Speed: " + gp.player.getEntityStats().getSpeed(), textX, textY);
        textY += lineHeight;
        if (gp.player.firstHand != null && gp.player.firstHand instanceof WeaponItem)
        {
            g2.drawString("Strength: " + gp.player.getEntityStats().getStrength() + " + " + ((WeaponItem) gp.player.firstHand).getDamageAmount().getAmount() + "D" + ((WeaponItem) gp.player.firstHand).getDamageAmount().getDice(), textX, textY);
            textY += lineHeight;
        }
        else
        {
            g2.drawString("Strength: " + gp.player.getEntityStats().getStrength(), textX, textY);
            textY += lineHeight;
        }
        g2.drawString("Dexterity: " + gp.player.getEntityStats().getDexterity(), textX, textY);
        textY += lineHeight;
        g2.drawString("First hand: ", textX, textY);
        textY += lineHeight;
        if (gp.player.firstHand != null)
        {
            g2.drawString("" + gp.player.firstHand.name, textX, textY);
            textY += lineHeight;
        }
        else
        {
            g2.drawString("empty", textX, textY);
            textY += lineHeight;
        }
        g2.drawString("Second hand: ", textX, textY);
        textY += lineHeight;
        if (gp.player.secondHand != null)
        {
            g2.drawString("" + gp.player.secondHand.name, textX, textY);
            textY += lineHeight;
        }
        else
        {
            g2.drawString("empty", textX, textY);
            textY += lineHeight;
        }
        /*
        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        // Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);


    }
    public int getXforAlignToRightText(String text, int tailX)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    public void drawPlayerLife()
    {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // BLANK HEART
        while (i < gp.player.getHealth().getMaxHealth()/2)
        {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while (i < gp.player.getHealth().getCurrentHealth())
        {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.getHealth().getCurrentHealth())
            {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    public void drawMessageScroll()
    {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16));

        for (int i = 0; i<messageScroll.size(); i++)
        {
            if (messageScroll.get(i) != null)
            {
                g2.setColor(Color.WHITE);
                g2.drawString(messageScroll.get(i), messageX, messageY);
                int counter = messageCounter.get(i) + 1; // message counter ++
                messageCounter.set(i, counter); // set the counter to the array
                messageY += 50;

                if (messageCounter.get(i) > 180)
                {
                    messageScroll.remove(i);
                    messageCounter.remove(i);

                }
            }
        }


    }

     */
    public int getItemIndexOnSlot()
    {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }
}
