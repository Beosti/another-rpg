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
                    gp.playMusic(0);
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
        else if (gp.gameState == GameValues.PLAYSTATE) {

            if (code == KeyEvent.VK_Z) upPressed = true;
            if (code == KeyEvent.VK_Q) leftPressed = true;
            if (code == KeyEvent.VK_D) rightPressed = true;
            if (code == KeyEvent.VK_S) downPressed = true;
            if (code == KeyEvent.VK_ENTER) enterPressed = true;
            if (code == KeyEvent.VK_I) gp.gameState = GameValues.PLAYER_STATS;
        }
        // DIALOGUE STATE
        if (gp.gameState == GameValues.DIALOGUESTATE && code == KeyEvent.VK_ESCAPE) gp.gameState = GameValues.PLAYSTATE;
        // PAUSE STATE
        if (code == KeyEvent.VK_P)
        {
            if (gp.gameState == GameValues.PLAYSTATE) gp.gameState = GameValues.PAUSESTATE;
            else if (gp.gameState == GameValues.PAUSESTATE) gp.gameState = GameValues.PLAYSTATE;
        }
        // CHARACTER STATE
        if (code == KeyEvent.VK_C)
        {
            if (gp.gameState == GameValues.PLAYSTATE) gp.gameState = GameValues.PLAYER_STATS;
            else if (gp.gameState == GameValues.PLAYER_STATS) gp.gameState = GameValues.PLAYSTATE;
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
