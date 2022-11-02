package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Class that organizes the keys used.
public class KeyHandler implements KeyListener {

    //Initialization of what buttons that are usable
    GamePanel gp;
    public boolean upPressed, leftPressed, rightPressed, downPressed;

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
        if (code == KeyEvent.VK_Z)
            upPressed = true;

        if (code == KeyEvent.VK_Q)
            leftPressed = true;

        if (code == KeyEvent.VK_D)
            rightPressed = true;

        if (code == KeyEvent.VK_S)
            downPressed = true;

        if (code == KeyEvent.VK_P) // pause button
        {
            if (gp.gameState == gp.playState)
                gp.gameState = gp.pauseState;
            else if (gp.gameState == gp.pauseState)
                gp.gameState = gp.playState;
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
