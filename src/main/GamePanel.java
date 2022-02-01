package main;

import main.entity.Player;

import javax.swing.*;
import java.awt.*;

// The background the player walks on and npcs, monsters etc.
// Basically interface we walking on
// tl;dr handles the game
public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile (default size of everything)
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    byte FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);

    // Set players's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run()
    {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null)
        {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000)
            {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D) graphics;

        player.draw(g2);

        g2.dispose();
    }
}
