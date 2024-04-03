package main.handlers;

import main.GamePanel;
import main.init.ModValues;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRectangle;
    int eventRectangleDefaultX, eventRectangleDefaultY;
    int debug = 0;
    boolean isOnPath = false;
    public EventHandler(GamePanel gp)
    {
        this.gp = gp;

        eventRectangle = new Rectangle();
        eventRectangle.x = 23;
        eventRectangle.y = 23;
        eventRectangle.width = 18;
        eventRectangle.height = 18;
        eventRectangleDefaultX = eventRectangle.x;
        eventRectangleDefaultY = eventRectangle.y;
    }

    public void checkEvent()
    {
        speedTiles();

    }
    public boolean hit(int eventCol, int eventRow, String reqDirection)
    {
        boolean hit = false;

        gp.playerEntity.solidArea.x = gp.playerEntity.worldX + gp.playerEntity.solidArea.x;
        gp.playerEntity.solidArea.y = gp.playerEntity.worldY + gp.playerEntity.solidArea.y;
        eventRectangle.x = eventCol* ModValues.TILE_SIZE + eventRectangle.x;

        if (gp.playerEntity.solidArea.intersects(eventRectangle))
        {
            if (gp.playerEntity.direction.equals(reqDirection) || reqDirection.contentEquals("any"))
            {
                hit = true;
            }
        }

        gp.playerEntity.solidArea.x = gp.playerEntity.solidAreaDefaultX;
        gp.playerEntity.solidArea.y = gp.playerEntity.solidAreaDefaultY;
        eventRectangle.x = eventRectangleDefaultX;
        eventRectangle.y = eventRectangleDefaultY;

        return hit;
    }
    public void speedTiles()
    {
        int coordinates = gp.tileManager.mapTileNum[gp.playerEntity.worldX/ModValues.TILE_SIZE][gp.playerEntity.worldY/ModValues.TILE_SIZE];
        if (coordinates == 4 && !isOnPath)
        {
            gp.playerEntity.getEntityStats().alterSpeed(1);
            isOnPath = true;
        }
        else if (coordinates != 3 && isOnPath) {
            gp.playerEntity.getEntityStats().alterSpeed(-1);
            isOnPath = false;
        }
    }
}
