package main.ui;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
    Overlay for the health on screen, should make a specific class for overlays
 */
public class HealthScreen extends Screen {
    private BufferedImage heart_full = setup("fullheart_texture", "health");
    public HealthScreen()
    {
        super();

    }


    @Override
    public void draw(Graphics2D g2) {

    }
}