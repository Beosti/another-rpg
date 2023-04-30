package main.ui;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
    Overlay for the health on screen, should make a specific class for overlays
 */
public class HealthScreen extends Screen {
    public BufferedImage heart_full, heart_half, heart_empty;

    public HealthScreen(Graphics2D g2, GamePanel gp) {
        super(g2, gp);
        heart_full = setup("fullheart_texture", "health");
        heart_half = setup("halfheart_texture", "health");
        heart_empty = setup("emptyheart_texture", "health");
    }
}