package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Class that organizes how you move.
public class KeyHandler implements KeyListener {

    public boolean upPressed, leftPressed, rightPressed, downPressed;

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Z)
        {
            upPressed = true;
        }
        if (code == KeyEvent.VK_Q)
        {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = true;
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
