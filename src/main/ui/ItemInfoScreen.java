package main.ui;

import main.GamePanel;
import main.api.screen.ScreenHelper;
import main.init.ModValues;

import java.awt.*;

public class ItemInfoScreen extends InventoryScreen {
    public boolean drawItemInfo;
    public int itemInfoSlotCol;
    public int itemInfoSlotRow;

    public ItemInfoScreen(GamePanel gamePanel) {
        super(gamePanel);
    }


    public void drawItemInfo(Graphics2D g2) {
        if (!this.drawItemInfo)
            return;
        int itemIndex = getItemIndexOnSlot();
        if (itemIndex > gp.playerEntity.oldInventory.size()) {
            this.drawItemInfo = false;
            return;
        }

        int frameX = ModValues.TILE_SIZE * 9;
        int frameY = ModValues.TILE_SIZE * 8 + 22;
        int frameWidth = ModValues.TILE_SIZE * 6;
        int frameHeight = ModValues.TILE_SIZE * 3;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        ScreenHelper.drawWindow(g2, frameX, frameY, frameWidth, frameHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));

        if (itemIndex < gp.playerEntity.oldInventory.size()) {
            if (gp.playerEntity.oldInventory.get(itemIndex).hasEquipped)
                g2.drawString("UNEQUIP", ModValues.TILE_SIZE * 9 + 35, ModValues.TILE_SIZE * 9 + 35);
            else
                g2.drawString("EQUIP", ModValues.TILE_SIZE * 9 + 35, ModValues.TILE_SIZE * 9 + 35);
        }

        g2.drawString("DROP", ModValues.TILE_SIZE * 9 + 35, ModValues.TILE_SIZE * 10 + 35);
        g2.drawString("USE", ModValues.TILE_SIZE * 11 + 70, ModValues.TILE_SIZE * 9 + 35);
        g2.drawString("EXIT", ModValues.TILE_SIZE * 11 + 63, ModValues.TILE_SIZE * 10 + 35);

        // SLOT
        final int slotXstart = frameX + 22;
        final int slotYstart = frameY + 24;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = ModValues.TILE_SIZE + 3;

        // CURSOR
        int cursorX = (int) (slotXstart + (slotSize * itemInfoSlotCol) * 2.5);
        int cursorY = slotYstart + (slotSize * itemInfoSlotRow);

        if (itemInfoSlotCol == 0 && itemInfoSlotRow == 0) {
            // equip
        } else if (itemInfoSlotCol == 0 && itemInfoSlotRow == 1) {
            // drop
        } else if (itemInfoSlotCol == 1 && itemInfoSlotRow == 0) {
            // use
        } else if (itemInfoSlotCol == 1 && itemInfoSlotRow == 1) {
            // exit
        }

        int cursorWidth = ModValues.TILE_SIZE * 2;
        int cursorHeight = ModValues.TILE_SIZE;

        // DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }
}
