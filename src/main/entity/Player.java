package main.entity;

import main.api.GameValues;
import main.entity.npcs.NPCEntity;
import main.GamePanel;
import main.handlers.KeyHandler;
import main.api.DamageCalculation;
import main.object.item.weapons.BasicSwordItem;
import main.object.item.weapons.BasicShieldItem;

import java.awt.*;
import java.awt.image.BufferedImage;


//Class for the player
public class Player extends Entity{

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public Player(GamePanel gp, KeyHandler keyHandler)
    {
        super(gp);

        this.name = "Player";
        this.strength = 0;
        this.defense = 0;
        this.maxHealth = 6;
        this.health = 6;
        this.speed = 2;
        this.type = GameValues.PLAYER;
        firstHand = new BasicSwordItem(gp);
        secondHand = new BasicShieldItem(gp);

        direction = "down";
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 45;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 33;
        solidArea.height = 34;
        attackArea.width = 36;
        attackArea.height = 36;
        getImage();
        getAttackImage();
    }
    public int getAttackDamageMelee()
    {
        return attackDamage = strength + DamageCalculation.damageCalculation(1, 2) + firstHand.attackValue;
    }
    public int getDefenseValueMelee()
    {
        return defenseValue = strength + secondHand.defenseValue;
    }
    public void getAttackImage()
    {
        attackUp1 = setupCustom("Goblin_Attack_up_1", "player", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setupCustom("Goblin_Attack_up_2", "player", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setupCustom("Goblin_Attack_down_1", "player", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setupCustom("Goblin_Attack_down_2", "player", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setupCustom("Goblin_Attack_left_1", "player", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setupCustom("Goblin_Attack_left_2", "player", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setupCustom("Goblin_Attack_right_1", "player", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setupCustom("Goblin_Attack_right_2", "player", gp.tileSize * 2, gp.tileSize);

    }

    //Frame images used for the player
    @Override
    public void getImage() {
        idle_front = setup("Player_goblin_front_idle", "player");
        front_walking1 = setup("Player_goblin_front_walking1", "player");
        front_walking2 = setup("Player_goblin_front_walking2", "player");
        idle_back = setup("Player_goblin_back_idle", "player");
        back_walking1 = setup("Player_goblin_back_walking1", "player");
        back_walking2 = setup("Player_goblin_back_walking2", "player");
        idle_left = setup("Player_goblin_side_left_idle", "player");
        left_walking1 = setup("Player_goblin_side_left_walking1", "player");
        left_walking2 = setup("Player_goblin_side_left_walking2", "player");
        idle_right = setup("Player_goblin_side_right_idle", "player");
        right_walking1 = setup("Player_goblin_side_right_walking1", "player");
        right_walking2 = setup("Player_goblin_side_right_walking2", "player");

    }

    //What can the player do at every update of the game
    @Override
    public void update()
    {
        //Handling of the movement
        if (attacking)
        {
            attacking();
        }
        else if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.enterPressed)
        {
            if (keyHandler.upPressed) direction = "up";
            else if (keyHandler.downPressed) direction = "down";
            else if (keyHandler.leftPressed) direction = "left";
            else if (keyHandler.rightPressed) direction = "right";


            collisionOn = false;
            gp.Checker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.Checker.checkObject(this, true);
            pickUpObject(objIndex);

            // NPC COLLISION
            int npcIndex = gp.Checker.checkEntity(this, gp.NPC);
            interactNPC(npcIndex);

            // HOSTILE COLLISION
            int hostileIndex = gp.Checker.checkEntity(this, gp.Hostile);
            contactHostile(hostileIndex);

            gp.eventHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyHandler.enterPressed)
            {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            gp.keyHandler.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 13)
            {
                if (spriteNumber == 1)
                    spriteNumber = 2;
                else if (spriteNumber == 2)
                    spriteNumber = 1;
                else if (spriteNumber == 3)
                    spriteNumber = 1;
                spriteCounter = 0;
            }
        }
        else
            spriteNumber = 3;

        // This needs to be outside of key if statement
        if (invincible)
        {
            invincibleCounter++;
            if(invincibleCounter > 60)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpObject(int i)
    {
        if (i != 999)
        {

        }
    }
    public void attacking()
    {
        spriteCounter++;

        if (spriteCounter <= 5)
        {
            spriteNumber = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25)
        {
            spriteNumber = 2;

            // save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = attackArea.width;
            int solidAreaHeight = attackArea.height;

            // adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }
            // attack area becomes solid
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision
            int monsterIndex = gp.Checker.checkEntity(this, gp.Hostile);
            damageMonster(monsterIndex);
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25)
        {
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void interactNPC(int i)
    {
        if (gp.keyHandler.enterPressed)
        {
            if (i != 999)
            {
                gp.gameState = GameValues.DIALOGUESTATE;
                if (gp.NPC[i] instanceof NPCEntity npcEntity)
                {
                    npcEntity.speak();
                }
            }
            else
            {
                attacking = true;
            }
        }
    }

    public void contactHostile(int i)
    {
        if (i != 999)
        {
            if (!invincible)
            {
                health -= gp.Hostile[i].attackDamage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i)
    {
        if (i != 999)
        {
            if (!gp.Hostile[i].invincible)
            {
                gp.Hostile[i].health -= gp.player.getAttackDamageMelee();
                gp.Hostile[i].invincible = true;
                if (gp.Hostile[i].health <= 0)
                {

                    gp.Hostile[i].dying = true;
                }
            }
        }
    }

    //Putting the images in the game
    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        //How does the player look in what direction
        switch (direction) {
            case "up" -> {
                if (!attacking)
                {
                    if (spriteNumber == 1) image = back_walking1;
                    if (spriteNumber == 2) image = back_walking2;
                    if (spriteNumber == 3) image = idle_back;
                }
                else if (attacking)
                {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNumber == 1) image = attackUp1;
                    if (spriteNumber == 2) image = attackUp2;
                }
            }
            case "down" -> {
                if (!attacking)
                {
                    if (spriteNumber == 1) image = front_walking1;
                    if (spriteNumber == 2) image = front_walking2;
                    if (spriteNumber == 3) image = idle_front;
                }
                else if (attacking)
                {
                    if (spriteNumber == 1) image = attackDown1;
                    if (spriteNumber == 2) image = attackDown2;
                }
            }
            case "left" -> {
                if (!attacking)
                {
                    if (spriteNumber == 1) image = left_walking1;
                    if (spriteNumber == 2) image = left_walking2;
                    if (spriteNumber == 3) image = idle_left;
                }
                else if (attacking)
                {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNumber == 1) image = attackLeft1;
                    if (spriteNumber == 2) image = attackLeft2;
                }
            }
            case "right" -> {
                if (!attacking)
                {
                    if (spriteNumber == 1) image = right_walking1;
                    if (spriteNumber == 2) image = right_walking2;
                    if (spriteNumber == 3) image = idle_right;
                }
                else if (attacking)
                {
                    if (spriteNumber == 1) image = attackRight1;
                    if (spriteNumber == 2) image = attackRight2;
                }
            }
        }

        if (invincible) // TODO add a thingy where the player goes in and off rendering as the classic rpgs
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }



        g2.drawImage(image, tempScreenX, tempScreenY,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
