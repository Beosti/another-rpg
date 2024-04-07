package main.ui;

import main.GamePanel;
import main.api.screen.ScreenHelper;
import main.init.ModValues;

import java.awt.*;

public class InventoryScreen extends Screen {

    public int inventorySlotCol;
    public int inventorySlotRow;

    public InventoryScreen(GamePanel gamePanel)
    {
        this.gp = gamePanel;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        // FRAME
        int frameX = ModValues.TILE_SIZE * 9;
        int frameY = ModValues.TILE_SIZE / 2;
        int frameWidth = ModValues.TILE_SIZE*6;
        int frameHeight = ModValues.TILE_SIZE*5;
        ScreenHelper.drawSubWindow(g2, frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = ModValues.TILE_SIZE + 3;

        // CURSOR
        System.out.println("Col: " + inventorySlotCol);
        System.out.println("Row: " + inventorySlotRow);
        int cursorX = slotXstart + (slotSize * inventorySlotCol);
        int cursorY = slotYstart + (slotSize * inventorySlotRow);
        int cursorWidth = ModValues.TILE_SIZE;
        int cursorHeight = ModValues.TILE_SIZE;
        // DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);


        // DRAW ITEMS
        for (int i = 0; i < gp.playerEntity.inventory.size(); i++)
        {
            g2.drawImage(gp.playerEntity.inventory.get(i).down, slotX, slotY, null);
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
        int dFrameHeight = ModValues.TILE_SIZE*3;
        // DESCRIPTION
        int textX = dFrameX + 20;
        int textY = dFrameY + ModValues.TILE_SIZE;
        g2.setFont(g2.getFont().deriveFont(26F));

        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.playerEntity.inventory.size())
        {
            ScreenHelper.drawSubWindow(g2, dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for (String line: gp.playerEntity.inventory.get(itemIndex).getDescription().split("\n"))
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
