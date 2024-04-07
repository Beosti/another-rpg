package main;

import main.api.AssetSetter;
import main.api.CollisionChecker;
import main.entity.Entity;
import main.entity.ItemEntity;
import main.entity.LivingEntity;
import main.entity.PlayerEntity;
import main.handlers.EventHandler;
import main.handlers.KeyHandler;
import main.init.ModValues;
import main.screens.TitleScreen;
import main.sound.Sound;
import main.tile.TileManager;
import main.ui.HealthScreen;
import main.ui.Screen;
import main.ui.Ui;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// The background the player walks on and npcs, monsters etc.
// Basically interface we walking on
// tl;dr handles the game
public class GamePanel extends JPanel implements Runnable{
    ArrayList<Screen> overlays = new ArrayList<>();

    //FPS
    byte FPS = 60;

    // SYSTEM
    public TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound sound = new Sound();
    Sound se = new Sound();
    public CollisionChecker Checker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Ui ui = new Ui(this);
    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public PlayerEntity playerEntity = new PlayerEntity(this, keyHandler);
    public Entity object[] = new Entity[10];
    public LivingEntity NPC[] = new LivingEntity[10];
    public LivingEntity Hostile[] = new LivingEntity[20];
    public ItemEntity itemEntity[] = new ItemEntity[10]; // TODO make an entity item thingy
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public GameState gameState;


    public GamePanel() throws FileNotFoundException {
        this.setPreferredSize(new Dimension(ModValues.SCREEN_WIDTH, ModValues.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        ArrayList<Screen> overlays = new ArrayList<>();
        overlays.add(new HealthScreen());
        ui.setScreen(new TitleScreen(this));
        assetSetter.setItem();
        assetSetter.setObject();
        assetSetter.setHostile();
        playMusic(0);
        gameState = GameState.TITLE_SCREEN;
        assetSetter.setNpc();
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
        if (GameState.updateState(gameState))
        {
            //PLAYER
            playerEntity.update();
            //NPC
            for (LivingEntity entity : NPC) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (int i = 0; i < Hostile.length; i++) {
                if (Hostile[i] != null) {
                    if (Hostile[i].alive && !Hostile[i].dying)
                        Hostile[i].update();
                    if (!Hostile[i].alive)
                        Hostile[i] = null;
                }
            }
        }
        else if (GameState.pauseState(gameState))
        {
            // GAME PAUSED
        }
    }

    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D) graphics;


        tileManager.draw(g2);
        playerEntity.draw(g2);

        entityList.add(playerEntity);
        for (Entity value : itemEntity)
        {
            if (value != null)
            {
                entityList.add(value);
            }
        }
        for (LivingEntity value : NPC) {
            if (value != null) {
                entityList.add(value);
            }
        }
        for (Entity object : object) {
            if (object != null) {
                entityList.add(object);
            }
        }
        for (Entity hostile : Hostile) {
            if (hostile != null) {
                entityList.add(hostile);
            }

        }
        // SORT
        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity entity1, Entity entity2) {
                int result = Integer.compare(entity1.worldY, entity2.worldY);
                    return result;
                }

                @Override
                public boolean equals(Object obj) {
                    return false;
                }
            });
            for (Entity entity : entityList) {
                entity.draw(g2);
            }
            entityList.clear();
        for (Screen overlay : this.overlays) {
            overlay.draw(g2);
        }
        if (ui.hasScreen())
            ui.getCurrentScreen().draw(g2);



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
