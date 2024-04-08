package main.ui;

import main.GamePanel;
import main.IKeyHandling;
import main.api.screen.ScreenHelper;
import main.init.ModValues;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

/**
 * Inventory screen that the player opens
 */
public class InventoryScreen extends Screen implements IKeyHandling {

    private int inventorySlotCol;
    private int inventorySlotRow;

    public InventoryScreen(GamePanel gamePanel)
    {
        this.gp = gamePanel;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        // FRAME of the actual inventory
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
        for (int i = 0; i < gp.playerEntity.inventory.size(); i++)
        {
            if (gp.playerEntity.inventory.get(i) != null)
            {
                System.out.println(getItemIndexOnSlot());
            }
        }
    }

    public int getItemIndexOnSlot()
    {
        int itemIndex = this.inventorySlotCol + (this.inventorySlotRow * 5);
        return itemIndex;
    }

    @Override
    public void init(int keyCode) {
        if (keyCode == KeyEvent.VK_DOWN && !(inventorySlotRow == 3)) inventorySlotRow++;
        if (keyCode == KeyEvent.VK_UP && !(inventorySlotRow == 0)) inventorySlotRow--;
        if (keyCode == KeyEvent.VK_LEFT && !(inventorySlotCol == 0)) inventorySlotCol--;
        if (keyCode == KeyEvent.VK_RIGHT && !(inventorySlotCol == 4)) inventorySlotCol++;
    }
}
