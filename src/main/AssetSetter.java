package main;

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
        gp.object[0].worldX = 30 * gp.tileSize;
        gp.object[0].worldY = 50 * gp.tileSize;

        /*
        gp.object[1] = new KeyObject();
        gp.object[1].worldX = 20 * gp.tileSize;
        gp.object[1].worldY = 40 * gp.tileSize;
         */

    }
}
