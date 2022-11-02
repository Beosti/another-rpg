package main;

import org.w3c.dom.css.Rect;

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
        int coordinates = gp.tileManager.mapTileNum[gp.player.worldX/gp.tileSize][gp.player.worldY/gp.tileSize];
        System.out.println(coordinates);
        if (coordinates == 3) // TODO make it so you get speed on the path
        {
            isOnPath = true;
            //gp.player.speed += 1;
        }
        //System.out.println(gp.player.speed);
        if (debug == 200)
        {
            System.out.println(gp.player.worldX);
            System.out.println(gp.player.worldY);
            debug = 0;
        }
        debug++;
    }
    public boolean hit(int eventCol, int eventRow, String reqDirection)
    {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRectangle.x = eventCol*gp.tileSize + eventRectangle.x;

        if (gp.player.solidArea.intersects(eventRectangle))
        {
            if (gp.player.direction.equals(reqDirection) || reqDirection.contentEquals("any"))
            {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRectangle.x = eventRectangleDefaultX;
        eventRectangle.y = eventRectangleDefaultY;

        return hit;
    }
    public void speedTiles(int gameState)
    {
        // TODO when on the tile that's a path you go faster
    }
}
