package main.api;

import main.GamePanel;
import main.entity.ItemEntity;
import main.entity.hostile.RedSlimeEntity;
import main.entity.npcs.NPC_OldMan;
import main.init.ModValues;
import main.object.GoldChestObject;
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
        gp.itemEntity[0].worldX = ModValues.TILE_SIZE * 24;
        gp.itemEntity[0].worldY = ModValues.TILE_SIZE * 40;

    }

    public void setObject()
    {
        gp.object[0] = new GoldChestObject(gp);
        gp.object[0].worldX = ModValues.TILE_SIZE * 26;
        gp.object[0].worldY = ModValues.TILE_SIZE * 46;
    }

    public void setNpc()
    {
        gp.NPC[0] = new NPC_OldMan(gp);
        gp.NPC[0].worldX = ModValues.TILE_SIZE*21;
        gp.NPC[0].worldY = ModValues.TILE_SIZE*21;


    }

    public void setHostile()
    {
        gp.Hostile[0] = new RedSlimeEntity(gp);
        gp.Hostile[0].worldX = ModValues.TILE_SIZE * 24;
        gp.Hostile[0].worldY = ModValues.TILE_SIZE * 40;
        gp.Hostile[1] = new RedSlimeEntity(gp);
        gp.Hostile[1].worldX = ModValues.TILE_SIZE * 26;
        gp.Hostile[1].worldY = ModValues.TILE_SIZE * 40;
        gp.Hostile[2] = new RedSlimeEntity(gp);
        gp.Hostile[2].worldX = ModValues.TILE_SIZE * 28;
        gp.Hostile[2].worldY = ModValues.TILE_SIZE * 40;

    }
}
