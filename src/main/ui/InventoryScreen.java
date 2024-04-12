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
    private boolean examineWindowOpen = false;
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
            if (!subWindowOpen)
            {
                subWindowOpen = true;
                subInventorySlotCol = 0;
                subInventorySlotRow = 0;
                return;
            }
            if (subInventorySlotRow == 1 && subInventorySlotCol == 0)
            {
                examineWindowOpen = !examineWindowOpen;
                return;
            }
            if (subInventorySlotRow == 1 && subInventorySlotCol == 1) // EXIT button
            {
                examineWindowOpen = false;
                subWindowOpen = false;
                return;
            }
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
        int subCursorX = (int) (subSlotXstart + (subSlotSize * subInventorySlotCol * 2.5));
        int subCursorY = subSlotYstart + (subSlotSize * subInventorySlotRow);
        int subCursorWidth = (int) (ModValues.TILE_SIZE * 2.5);
        int subCursorHeight = ModValues.TILE_SIZE;
        // DRAW CURSOR
        if (subWindowOpen)
        {
            ScreenHelper.drawCursor(g2, subCursorX, subCursorY, subCursorWidth, subCursorHeight);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            String textEquip = "Equip";
            g2.setColor(Color.WHITE);
            g2.drawString(textEquip, subSlotXstart + 12, subSlotYstart + 31);
            String textUse = "Use";
            g2.drawString(textUse, subSlotXstart + 160, subSlotYstart + 31);
            //g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            String textExamine = "Examine";
            g2.drawString(textExamine, subSlotXstart + 12, subSlotYstart + 84);
            String textExit = "Exit";
            //g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
            g2.setColor(Color.WHITE);
            g2.drawString(textExit, (subSlotXstart + 160), (subSlotYstart + 84));
        }
        if (examineWindowOpen)
        {
            ScreenHelper.drawSubWindow(g2, subFrameX, subFrameY + 140, subFrameWidth, subFrameHeight);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16F));
            g2.setColor(Color.WHITE);
            g2.drawString("Name: " + gp.playerEntity.getInventory().getItem(getItemIndexOnSlot()).getName(), subSlotXstart - 4, subSlotYstart + 148);
            g2.drawString("Description: ", subSlotXstart - 4, subSlotYstart + 166);
            g2.drawString(gp.playerEntity.getInventory().getItem(getItemIndexOnSlot()).getDescription(), subSlotXstart - 4, subSlotYstart + 182);
            g2.drawString("Damage: " + gp.playerEntity.getInventory().getItem(getItemIndexOnSlot()).getDamageAmount().getAmount() + "D" + gp.playerEntity.getInventory().getItem(getItemIndexOnSlot()).getDamageAmount().getDice(), subSlotXstart - 4, subSlotYstart + 200);

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
