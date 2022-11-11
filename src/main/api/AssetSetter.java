package main.api;

import main.GamePanel;
import main.entity.Entity;
import main.entity.ItemEntity;
import main.entity.hostile.RedSlimeEntity;
import main.entity.npcs.NPC_OldMan;
import main.object.KeyObject;
import main.object.item.weapons.BasicSwordItem;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setItem()
    {
        BasicSwordItem item = new BasicSwordItem(gp);
        gp.itemEntity[0] = new ItemEntity(gp, item);
        gp.itemEntity[0].worldX = gp.tileSize * 24;
        gp.itemEntity[0].worldY = gp.tileSize * 40;

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
        gp.Hostile[0].worldX = gp.tileSize * 24;
        gp.Hostile[0].worldY = gp.tileSize * 40;
        gp.Hostile[1] = new RedSlimeEntity(gp);
        gp.Hostile[1].worldX = gp.tileSize * 26;
        gp.Hostile[1].worldY = gp.tileSize * 40;
        gp.Hostile[2] = new RedSlimeEntity(gp);
        gp.Hostile[2].worldX = gp.tileSize * 28;
        gp.Hostile[2].worldY = gp.tileSize * 40;

    }
}
