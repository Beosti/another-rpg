package main;

import object.ChestObject;
import object.DoorObject;
import object.KeyObject;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        gp.object[0] = new KeyObject();
        gp.object[0].worldX = 20 * gp.tileSize;
        gp.object[0].worldY = 40 * gp.tileSize;


        gp.object[1] = new DoorObject();
        gp.object[1].worldX = 20 * gp.tileSize;
        gp.object[1].worldY = 30 * gp.tileSize;

        gp.object[2] = new ChestObject();
        gp.object[2].worldX = 20 * gp.tileSize;
        gp.object[2].worldY = 20 * gp.tileSize;


    }
}
