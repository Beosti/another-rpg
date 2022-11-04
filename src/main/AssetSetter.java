package main;

import entity.npcs.NPC_OldMan;
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

    }

    public void setNpc()
    {
        gp.NPC[0] = new NPC_OldMan(gp);
        gp.NPC[0].worldX = gp.tileSize*21;
        gp.NPC[0].worldY = gp.tileSize*21;


    }
}
