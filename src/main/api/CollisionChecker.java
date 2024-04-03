package main.api;

import main.GamePanel;
import main.entity.Entity;
import main.entity.LivingEntity;
import main.init.ModValues;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile(LivingEntity entity)
    {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/ ModValues.TILE_SIZE;
        int entityRightCol = entityRightWorldX/ModValues.TILE_SIZE;
        int entityTopRow = entityTopWorldY/ModValues.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / ModValues.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (entity.direction) { // TODO check this code out interesting for events
            case "up" -> {
                entityTopRow = (int) ((entityTopWorldY - entity.getEntityStats().getSpeed()) / ModValues.TILE_SIZE);
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    entity.collisionOn = true;
            }
            case "down" -> {
                entityBottomRow = (int) ((entityBottomWorldY - entity.getEntityStats().getSpeed()) / ModValues.TILE_SIZE);
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    entity.collisionOn = true;
            }
            case "left" -> {
                entityLeftCol = (int) ((entityLeftWorldX - entity.getEntityStats().getSpeed()) / ModValues.TILE_SIZE);
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    entity.collisionOn = true;
            }
            case "right" -> {
                entityRightCol = (int) ((entityRightWorldX + entity.getEntityStats().getSpeed()) / ModValues.TILE_SIZE);
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision)
                    entity.collisionOn = true;
            }
        }
    }
    public int checkItemEntity(Entity entity, boolean player)
    {
        int index = 999;
        for (int i = 0; i < gp.itemEntity.length; i++)
        {
            if (gp.itemEntity[i] != null)
            {
                //Get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the objects solid area position
                gp.itemEntity[i].solidArea.x = gp.itemEntity[i].worldX + gp.itemEntity[i].solidArea.x;
                gp.itemEntity[i].solidArea.y = gp.itemEntity[i].worldY + gp.itemEntity[i].solidArea.y;

                if (entity.solidArea.intersects(gp.itemEntity[i].solidArea))
                {
                    if (player)
                        index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.itemEntity[i].solidArea.x = gp.itemEntity[i].solidAreaDefaultX;
                gp.itemEntity[i].solidArea.y = gp.itemEntity[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public int checkObject(LivingEntity entity, boolean player)
    {
        int index = 999;
        for (int i = 0; i < gp.object.length; i++)
        {
            if (gp.object[i] != null)
            {
                //Get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the objects solid area position
                gp.object[i].solidArea.x = gp.object[i].worldX + gp.object[i].solidArea.x;
                gp.object[i].solidArea.y = gp.object[i].worldY + gp.object[i].solidArea.y;

                switch (entity.direction)
                {
                    case "up":
                        entity.solidArea.y -= entity.getEntityStats().getSpeed();
                        if (entity.solidArea.intersects(gp.object[i].solidArea)) {
                            if (gp.object[i].collision == true)
                            {
                                entity.collisionOn = true;
                            }
                            if (player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.getEntityStats().getSpeed();
                        if (entity.solidArea.intersects(gp.object[i].solidArea))
                        {
                            if (gp.object[i].collision == true)
                            {
                                entity.collisionOn = true;
                            }
                            if (player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.getEntityStats().getSpeed();
                        if (entity.solidArea.intersects(gp.object[i].solidArea))
                        {
                            if (gp.object[i].collision == true)
                            {
                                entity.collisionOn = true;
                            }
                            if (player == true)
                            {
                                index = i;
                            }                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.getEntityStats().getSpeed();
                        if (entity.solidArea.intersects(gp.object[i].solidArea))
                        {
                            if (gp.object[i].collision == true)
                            {
                                entity.collisionOn = true;
                            }
                            if (player == true)
                            {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.object[i].solidArea.x = gp.object[i].solidAreaDefaultX;
                gp.object[i].solidArea.y = gp.object[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    //NPC or MONSTER
    public int checkEntity(LivingEntity entity, Entity[] target)
    {
        int index = 999;
        for (int i = 0; i < gp.object.length; i++)
        {
            if (target[i] != null)
            {
                //Get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the objects solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> entity.solidArea.y -= entity.getEntityStats().getSpeed();
                    case "down" -> entity.solidArea.y += entity.getEntityStats().getSpeed();
                    case "left" -> entity.solidArea.x -= entity.getEntityStats().getSpeed();
                    case "right" -> entity.solidArea.x += entity.getEntityStats().getSpeed();
                }
                if (entity.solidArea.intersects(target[i].solidArea))
                {
                    if (target[i] != entity)
                    {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(LivingEntity entity)
    {
        boolean contactPlayer = false;
        //Get entity solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //Get the objects solid area position
        gp.playerEntity.solidArea.x = gp.playerEntity.worldX + gp.playerEntity.solidArea.x;
        gp.playerEntity.solidArea.y = gp.playerEntity.worldY + gp.playerEntity.solidArea.y;

        switch (entity.direction) {
            case "up" -> entity.solidArea.y -= entity.getEntityStats().getSpeed();
            case "down" -> entity.solidArea.y += entity.getEntityStats().getSpeed();
            case "left" -> entity.solidArea.x -= entity.getEntityStats().getSpeed();
            case "right" -> entity.solidArea.x += entity.getEntityStats().getSpeed();
        }
        if (entity.solidArea.intersects(gp.playerEntity.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.playerEntity.solidArea.x = gp.playerEntity.solidAreaDefaultX;
        gp.playerEntity.solidArea.y = gp.playerEntity.solidAreaDefaultY;

        return contactPlayer;
    }
}
