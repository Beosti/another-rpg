package main.ui;

import main.GamePanel;

import java.awt.*;

public class ItemInfoScreen extends InventoryScreen {
    public boolean drawItemInfo;
    public int itemInfoSlotCol;
    public int itemInfoSlotRow;
    public ItemInfoScreen(Graphics2D g2, GamePanel gp) {
        super(g2, gp);
    }

    public void drawItemInfo() {
        if (!this.drawItemInfo)
            return;
        int itemIndex = getItemIndexOnSlot();
        if (itemIndex > gp.player.inventory.size()) {
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

        if (itemIndex < gp.player.inventory.size()) {
            if (gp.player.inventory.get(itemIndex).hasEquipped)
                g2.drawString("UNEQUIP", gp.tileSize * 9 + 35, gp.tileSize * 9 + 35);
            else
                g2.drawString("EQUIP", gp.tileSize * 9 + 35, gp.tileSize * 9 + 35);
        }

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

        int cursorWidth = gp.tileSize * 2;
        int cursorHeight = gp.tileSize;

        // DRAW CURSOR
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }



}
