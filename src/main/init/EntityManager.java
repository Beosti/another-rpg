package main.init;

import main.GamePanel;
import main.entity.Entity;
import main.entity.hostile.RedSlimeEntity;

public class EntityManager {

    private static GamePanel gamePanel;

    public static void setGamePanel(GamePanel gamePanel) {
        EntityManager.gamePanel = gamePanel;
    }

    public static final int RED_SLIME = 1;
    // Add other entity types here...

    public static Entity createEntity(int entityType) {
        switch (entityType) {
            case RED_SLIME:
                return new RedSlimeEntity(gamePanel);
            // Add other entity types here...
            default:
                throw new IllegalArgumentException("Invalid entity type: " + entityType);
        }
    }
}



