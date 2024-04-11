package main.ui;

import main.GamePanel;
import main.IKeyHandling;
import main.api.screen.ScreenHelper;
import main.init.ModValues;
import main.object.item.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Inventory screen that the player opens
 */
public class InventoryScreen extends Screen implements IKeyHandling {

    private int inventorySlotCol;
    private int inventorySlotRow;
    private int subInventorySlotCol;
    private int subInventorySlotRow;

    private boolean subWindowOpen = false;
    public InventoryScreen(GamePanel gamePanel)
    {
        this.gp = gamePanel;
    }

    @Override
    public void init(int keyCode) {
        if (!subWindowOpen)
        {
            if (keyCode == KeyEvent.VK_DOWN && !(inventorySlotRow == 3))
                inventorySlotRow++;
            if (keyCode == KeyEvent.VK_UP && !(inventorySlotRow == 0))
                inventorySlotRow--;
            if (keyCode == KeyEvent.VK_LEFT && !(inventorySlotCol == 0))
                inventorySlotCol--;
            if (keyCode == KeyEvent.VK_RIGHT && !(inventorySlotCol == 4))
                inventorySlotCol++;
        }
        else
        {
            if (keyCode == KeyEvent.VK_DOWN && !(subInventorySlotRow == 1))
                subInventorySlotRow++;
            if (keyCode == KeyEvent.VK_UP && !(subInventorySlotRow == 0))
                subInventorySlotRow--;
            if (keyCode == KeyEvent.VK_LEFT && !(subInventorySlotCol == 0))
                subInventorySlotCol--;
            if (keyCode == KeyEvent.VK_RIGHT && !(subInventorySlotCol == 1))
                subInventorySlotCol++;
        }
        if (keyCode == KeyEvent.VK_ENTER)
        {
            if (!gp.playerEntity.getInventory().getItems().containsValue(getItemIndexOnSlot())) // can only open sub window if item is in slot targeted
                return;
            if (subWindowOpen) {
                subWindowOpen = false;
                return;
            }
            subWindowOpen = true;
        }
    }

    @Override
    public void draw(Graphics2D g2)
    {
        // FRAME of the actual inventory
        int frameX = ModValues.TILE_SIZE * 9;
        int frameY = ModValues.TILE_SIZE / 2;
        int frameWidth = ModValues.TILE_SIZE*6;
        int frameHeight = ModValues.TILE_SIZE*5;
        ScreenHelper.drawWindow(g2, frameX, frameY, frameWidth, frameHeight);

        // FIRST SLOT OF THE MAIN FRAME
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = ModValues.TILE_SIZE + 3;

        // CURSOR
        //System.out.println("Col: " + inventorySlotCol);
        //System.out.println("Row: " + inventorySlotRow);
        int cursorX = slotXstart + (slotSize * inventorySlotCol);
        int cursorY = slotYstart + (slotSize * inventorySlotRow);
        int cursorWidth = ModValues.TILE_SIZE;
        int cursorHeight = ModValues.TILE_SIZE;
        ScreenHelper.drawCursor(g2, cursorX, cursorY, cursorWidth, cursorHeight);

        // FRAME of the sub window
        int subFrameX = ModValues.TILE_SIZE * 9;
        int subFrameY = ModValues.TILE_SIZE * 6;
        int subFrameWidth = ModValues.TILE_SIZE*6;
        int subFrameHeight = ModValues.TILE_SIZE*3;
        if (subWindowOpen)
            ScreenHelper.drawSubWindow(g2, subFrameX, subFrameY, subFrameWidth, subFrameHeight);

        // FIRST SLOT OF THE SUB FRAME
        final int subSlotXstart = subFrameX + 20;
        final int subSlotYstart = subFrameY + 20;
        int subSlotX = subSlotXstart;
        int subSlotY = subSlotYstart;
        int subSlotSize = ModValues.TILE_SIZE + 3;

        // SUB CURSOR
        int subCursorX = subSlotXstart + (subSlotSize * subInventorySlotCol);
        int subCursorY = subSlotYstart + (subSlotSize * subInventorySlotRow);
        int subCursorWidth = ModValues.TILE_SIZE * 2;
        int subCursorHeight = ModValues.TILE_SIZE;
        // DRAW CURSOR
        if (subWindowOpen)
        {
            ScreenHelper.drawCursor(g2, subCursorX, subCursorY, subCursorWidth, subCursorHeight);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            String text = "Equip";
            //g2.setColor(Color.BLACK);
            //g2.drawString(text, subSlotXstart, subSlotYstart);
            g2.setColor(Color.WHITE);
            g2.drawString(text, subSlotXstart, subSlotYstart + ModValues.TILE_SIZE / 2);
        }

        // DRAW ITEMS
        for (Map.Entry<Item, Integer> entry : gp.playerEntity.getInventory().getItems().entrySet()) {
            Item item = entry.getKey();
            int index = entry.getValue();
            g2.drawImage(item.down, slotX, slotY, null);
            slotX += slotSize;
            if (index == 4 || index == 9 || index == 14)
            {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
    }

    public int getItemIndexOnSlot()
    {
        return this.inventorySlotCol + (this.inventorySlotRow * 5);
    }
}
