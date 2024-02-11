package main.ui;

import main.GamePanel;

import java.awt.*;

public class InventoryScreen extends Screen {

    public int inventorySlotCol;
    public int inventorySlotRow;

    public InventoryScreen(Graphics2D g2, GamePanel gp)
    {
        super(g2, gp);
    }

    public void drawInventory(Graphics2D g2)
    {
        // FRAME
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize / 2;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2);

        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        // CURSOR
        System.out.println("Col: " + inventorySlotCol);
        System.out.println("Row: " + inventorySlotRow);
        int cursorX = slotXstart + (slotSize * inventorySlotCol);
        int cursorY = slotYstart + (slotSize * inventorySlotRow);
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
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight, g2);
            for (String line: gp.player.inventory.get(itemIndex).getDescription().split("\n"))
            {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public int getItemIndexOnSlot()
    {
        int itemIndex = this.inventorySlotCol + (this.inventorySlotRow * 5);
        return itemIndex;
    }
}
