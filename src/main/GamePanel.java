package main;

import entity.Player;
import object.KeyObject;
import object.SuperObject;
import tile.TileManager;

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
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    byte FPS = 60;

    // WORLD SETTINGS
    public final int maxWorldCol = 48;
    public final int maxWorldRow = 48;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // SYSTEM
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler(this);
    Sound sound = new Sound();
    Sound se = new Sound();
    public CollisionChecker Checker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Ui ui = new Ui(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);
    public SuperObject object[] = new SuperObject[10];

    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        assetSetter.setObject();
        playMusic(0);
        gameState = playState;
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
        if (gameState == playState)
        {
            player.update();
        }
        else if (gameState == pauseState)
        {
            // GAME PAUSED
        }
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D) graphics;

        tileManager.draw(g2);
        for (int i=0; i< object.length; i++)
        {
            if (object[i] != null)
                object[i].draw(g2, this);
        }
        player.draw(g2);

        // UI
        ui.draw(g2);

        g2.dispose();
    }
    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic()
    {
        sound.stop();
    }
    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }
}
