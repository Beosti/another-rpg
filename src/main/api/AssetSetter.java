package main.api;

import main.GamePanel;
import main.entity.hostile.RedSlimeEntity;
import main.entity.npcs.NPC_OldMan;

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

    public void setHostile()
    {
        gp.Hostile[0] = new RedSlimeEntity(gp);
        gp.Hostile[0].worldX = gp.tileSize * 23;
        gp.Hostile[0].worldY = gp.tileSize *23;
    }
}
