package main.handlers;

import main.GamePanel;

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
        if (gp.gameState == gp.titleScreenState)
        {
            if (code == KeyEvent.VK_Z && gp.ui.commandNum > 0)
                gp.ui.commandNum--;
            if (code == KeyEvent.VK_S && gp.ui.commandNum < 2)
                gp.ui.commandNum++;
            if (code == KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum == 0)
                {
                    gp.gameState = gp.playState;
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
        else if (code == KeyEvent.VK_Z)
            upPressed = true;
        else if (code == KeyEvent.VK_Q)
            leftPressed = true;
        else if (code == KeyEvent.VK_D)
            rightPressed = true;
        else if (code == KeyEvent.VK_S)
            downPressed = true;
        else if (code == KeyEvent.VK_ENTER)
            enterPressed = true;

        if (code == KeyEvent.VK_P) // pause button
        {
            if (gp.gameState == gp.playState)
                gp.gameState = gp.pauseState;
            else if (gp.gameState == gp.pauseState)
                gp.gameState = gp.playState;
        }
        if (gp.gameState == gp.dialogueState && code == KeyEvent.VK_ESCAPE)
            gp.gameState = gp.playState;
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
