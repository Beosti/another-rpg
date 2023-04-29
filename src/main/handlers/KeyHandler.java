package main.handlers;

import main.GamePanel;
import main.api.GameValues;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Class that organizes the keys used.
public class KeyHandler implements KeyListener {

    //Initialization of what buttons that are usable
    GamePanel gp;
    public boolean upPressed, leftPressed, rightPressed, downPressed, enterPressed;

    public KeyHandler(GamePanel gamePanel)
    {
        this.gp = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        //TITLE STATE
        if (gp.gameState == GameValues.TITLE_SCREEN)
        {
            if (code == KeyEvent.VK_Z && gp.ui.commandNum > 0) gp.ui.commandNum--;
            if (code == KeyEvent.VK_S && gp.ui.commandNum < 2) gp.ui.commandNum++;
            if (code == KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum == 0)
                {
                    gp.gameState = GameValues.PLAYSTATE;
                    //gp.playMusic(0); // TODO make it play it again
                }
                if (gp.ui.commandNum == 1)
                {
                    // add later
                }
                if (gp.ui.commandNum == 2)
                {
                    System.exit(0);
                }
            }
        }
        // PLAY STATE
        else if (gp.gameState == GameValues.PLAYSTATE || gp.gameState == GameValues.PLAYER_STATS || gp.gameState == GameValues.PlAYER_QUESTS) {
            if (gp.gameState == GameValues.PLAYER_STATS || gp.gameState == GameValues.PlAYER_QUESTS)
            {
                if (code == KeyEvent.VK_ESCAPE) gp.gameState = GameValues.PLAYSTATE;
            }
            if (code == KeyEvent.VK_Z) upPressed = true;
            if (code == KeyEvent.VK_Q) leftPressed = true;
            if (code == KeyEvent.VK_D) rightPressed = true;
            if (code == KeyEvent.VK_S) downPressed = true;
            if (code == KeyEvent.VK_ENTER) enterPressed = true;
        }
        // DIALOGUE STATE
        if (gp.gameState == GameValues.DIALOGUESTATE && code == KeyEvent.VK_ESCAPE) gp.gameState = GameValues.PLAYSTATE;
        // PAUSE STATE
        if (code == KeyEvent.VK_P)
        {
            if (gp.gameState == GameValues.PLAYSTATE) gp.gameState = GameValues.PAUSESTATE;
            else if (gp.gameState == GameValues.PAUSESTATE) gp.gameState = GameValues.PLAYSTATE;
        }
        // CHARACTER STATE //todo make it so you can access inventory during character state
        if (code == KeyEvent.VK_C)
        {
            if (gp.gameState == GameValues.PLAYSTATE) gp.gameState = GameValues.PLAYER_STATS;
            else if (gp.gameState == GameValues.PLAYER_STATS) gp.gameState = GameValues.PLAYSTATE;

        }
        // INVENTORY STATE
        if (code == KeyEvent.VK_I)
        {
            if (gp.gameState == GameValues.PLAYSTATE) gp.gameState = GameValues.PLAYER_INVENTORY;
            else if (gp.gameState == GameValues.PLAYER_INVENTORY) gp.gameState = GameValues.PLAYSTATE;
            if (gp.gameState == GameValues.PLAYER_STATS) gp.gameState = GameValues.PLAYER_STATS_INVENTORY;
            else if (gp.gameState == GameValues.PLAYER_STATS_INVENTORY) gp.gameState = GameValues.PLAYER_STATS;
        }
        // ACCEPTING OR DENYING QUEST
        if (gp.gameState == GameValues.DIALOGUESTATE && gp.ui.choiceDialogue)
        {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = GameValues.PLAYSTATE;
                gp.ui.choiceDialogue = false;
            }
            if (code == KeyEvent.VK_D && gp.ui.dialogueCol == 0) gp.ui.dialogueCol++;
            if (code == KeyEvent.VK_Q && gp.ui.dialogueCol == 1) gp.ui.dialogueCol--;
        }
        // SCROLLING INVENTORY
        if (gp.gameState == GameValues.PLAYER_INVENTORY || gp.gameState == GameValues.PLAYER_STATS_INVENTORY)
        {
            if (code == KeyEvent.VK_ESCAPE)
                gp.gameState = GameValues.PLAYSTATE;
            if (!gp.ui.drawItemInfo)
            {
                if (code == KeyEvent.VK_Z && gp.ui.slotRow != 0) gp.ui.slotRow--;
                if (code == KeyEvent.VK_Q && gp.ui.slotCol != 0) gp.ui.slotCol--;
                if (code == KeyEvent.VK_D && gp.ui.slotCol != 4) gp.ui.slotCol++;
                if (code == KeyEvent.VK_S && gp.ui.slotRow != 3) gp.ui.slotRow++;
            }
            else {
                if (code == KeyEvent.VK_Z && gp.ui.subSlotRow != 0) gp.ui.subSlotRow--;
                if (code == KeyEvent.VK_Q && gp.ui.subSlotCol != 0) gp.ui.subSlotCol--;
                if (code == KeyEvent.VK_D && gp.ui.subSlotCol != 1) gp.ui.subSlotCol++;
                if (code == KeyEvent.VK_S && gp.ui.subSlotRow != 1) gp.ui.subSlotRow++;
            }

            if (code == KeyEvent.VK_ENTER) {
                if (!gp.ui.drawItemInfo)
                    gp.ui.drawItemInfo = true;
                else
                {
                    if (gp.ui.subSlotCol == 0 && gp.ui.subSlotRow == 0) // equip
                    {
                        int itemIndex = gp.ui.getItemIndexOnSlot();
                        if (itemIndex < gp.player.inventory.size())
                        {
                            if (gp.player.firstHand != null && gp.player.inventory.get(itemIndex) == gp.player.firstHand)
                                // item is in firsthand + unequip
                            {
                                gp.player.firstHand = null;
                                gp.player.inventory.get(itemIndex).hasEquipped = false;
                            }
                            else if (gp.player.secondHand != null && gp.player.inventory.get(itemIndex) == gp.player.secondHand)
                                // item is in secondhand + unequip
                            {
                                gp.player.secondHand = null;
                                gp.player.inventory.get(itemIndex).hasEquipped = false;
                            }
                            else
                                // item is not in hand
                            if (gp.player.firstHand != null && gp.player.secondHand != null)
                            // hands are full
                            {
                                return;
                            }
                            else
                            //hands are not full
                            {

                                if (gp.player.firstHand != null && gp.player.secondHand == null) {
                                    gp.player.secondHand = gp.player.inventory.get(itemIndex);
                                    gp.player.secondHand.hasEquipped = true;
                                }
                                else if (gp.player.firstHand == null && gp.player.secondHand != null) {
                                    gp.player.firstHand = gp.player.inventory.get(itemIndex);
                                    gp.player.firstHand.hasEquipped = true;
                                }
                                else {
                                    gp.player.firstHand = gp.player.inventory.get(itemIndex);
                                    gp.player.firstHand.hasEquipped = true;
                                }
                            }


                        }
                        /*
                        if (gp.player.firstHand != null && gp.player.firstHand.hasEquipped)
                            gp.player.firstHand = null;
                        else
                        {
                            // this gets the item in inventory
                            int itemIndex = gp.ui.getItemIndexOnSlot();
                            if (itemIndex < gp.player.inventory.size())
                            {
                                gp.player.firstHand = gp.player.inventory.get(itemIndex);
                                gp.player.inventory.get(itemIndex).hasEquipped = true;
                            }
                        }

                         */
                    }
                    else if (gp.ui.subSlotCol == 0 && gp.ui.subSlotRow == 1) // drop
                        ;
                    else if (gp.ui.subSlotCol == 1 && gp.ui.subSlotRow == 0) // use
                        ;
                    else if (gp.ui.subSlotCol == 1 && gp.ui.subSlotRow == 1) // exit
                        gp.ui.drawItemInfo = false;
                    // code if pressed entered
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Z)
        {
            upPressed = false;
        }
        if (code == KeyEvent.VK_Q)
        {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = false;
        }
    }
}
