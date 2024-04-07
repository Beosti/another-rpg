package main.entity;

import main.GameState;
import main.IKeyHandling;
import main.api.EntityCategory;
import main.api.entity.EntityStats;
import main.api.entity.Health;
import main.data.quest.KillingObjective;
import main.data.quest.Objective;
import main.data.quest.Quest;
import main.entity.npcs.NPCEntity;
import main.GamePanel;
import main.handlers.KeyHandler;
import main.init.ModValues;
import main.object.item.Item;
import main.object.item.weapons.WeaponItem;
import main.ui.InventoryScreen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


//Class for the player
public class PlayerEntity extends LivingEntity implements IKeyHandling {

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public ArrayList<Item> inventory = new ArrayList<>();
    public final int inventorySize = 20;
    private boolean inInventory = false;
    public int money;
    public List<Quest> inProgressQuest = new ArrayList<Quest>();
    public List<Quest> finishedQuest = new ArrayList<Quest>();
    public boolean upPressed, leftPressed, rightPressed, downPressed, enterPressed;
    public PlayerEntity(GamePanel gp, KeyHandler keyHandler)
    {
        super(gp);

        this.setName("Player");
        this.setEntityStats(new EntityStats(3, 0, 0, 0, 3));
        this.setHealth(new Health().setRandomizedHealth((byte) 1, (byte) 5));
        this.money = 0;
        this.setEntityCategory(EntityCategory.GOBLIN);
        direction = "down";
        this.keyHandler = keyHandler;

        screenX = ModValues.SCREEN_WIDTH / 2 - (ModValues.TILE_SIZE/2);
        screenY = ModValues.SCREEN_HEIGHT / 2 - (ModValues.TILE_SIZE/2);

        worldX = ModValues.TILE_SIZE * 24;
        worldY = ModValues.TILE_SIZE * 45;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 6;
        attackArea.width = 36;
        attackArea.height = 36;
        getImage();
        getAttackImage();
        //setItems();
    }

    @Override
    public void init(int keyCode) {
        if (!this.inInventory)
        {
            switch (keyCode) {
                case (KeyEvent.VK_Z) -> this.upPressed = true;
                case (KeyEvent.VK_S) -> this.downPressed = true;
                case (KeyEvent.VK_Q) -> this.leftPressed = true;
                case (KeyEvent.VK_D) -> this.rightPressed = true;
                case (KeyEvent.VK_E) -> {
                    this.inInventory = true;
                    gp.ui.setScreen(new InventoryScreen(gp));
                }
            }
        }
        else {
            if (keyCode == KeyEvent.VK_E) {
                this.inInventory = false;
                gp.ui.setScreen(null);
            }
            this.inInventory = false;
        }
    }

    @Override
    public void released(int keyCode) {
        if (!this.inInventory)
        {
            switch (keyCode) {
                case (KeyEvent.VK_Z) -> this.upPressed = false;
                case (KeyEvent.VK_S) -> this.downPressed = false;
                case (KeyEvent.VK_Q) -> this.leftPressed = false;
                case (KeyEvent.VK_D) -> this.rightPressed = false;
            }
        }
    }

    public void setItems()
    {

        inventory.add(firstHand);
        inventory.add(secondHand);
        firstHand.hasEquipped = true;
        secondHand.hasEquipped = true;
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
        else if (this.upPressed || this.downPressed || this.rightPressed || this.leftPressed)
        {
            if (this.upPressed) direction = "up";
            if (this.downPressed) direction = "down";
            if (this.rightPressed) direction = "right";
            if (this.leftPressed) direction = "left";


            collisionOn = false;
            gp.Checker.checkTile(this);

            //TODO check this for chest attack

            // ITEM ENTITY COLLISION
            int itemEntityIndex = gp.Checker.checkItemEntity(this, true);
            pickUpItemEntity(itemEntityIndex);

            // CHECK OBJECT COLLISION
            int objIndex = gp.Checker.checkObject(this, true);
            pickUpObject(objIndex);

            // NPC COLLISION
            int npcIndex = gp.Checker.checkEntity(this, gp.NPC);
            //interactNPC(npcIndex);

            // HOSTILE COLLISION
            int hostileIndex = gp.Checker.checkEntity(this, gp.Hostile);
            contactHostile(hostileIndex);

            gp.eventHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            //if (!collisionOn && !keyHandler.enterPressed)
            if (!collisionOn)
            {
                switch (direction) {
                    case "up" -> worldY -= this.getEntityStats().getSpeed();
                    case "down" -> worldY += this.getEntityStats().getSpeed();
                    case "left" -> worldX -= this.getEntityStats().getSpeed();
                    case "right" -> worldX += this.getEntityStats().getSpeed();
                }
            }

            //gp.keyHandler.enterPressed = false;

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

    public void pickUpItemEntity(int i)
    {
        if (i != 999 && inventorySize > inventory.size())
        {
            Item pickedItem = gp.itemEntity[i].item;
            gp.playerEntity.inventory.add(pickedItem);
            gp.itemEntity[i] = null;
        }
    }

    public void pickUpObject(int i)
    {

    }
    public void attacking()
    {
        // counts up the sprite during the whole attack
        spriteCounter++;

        // first sprite the first 5 frames
        if (spriteCounter <= 5)
        {
            spriteNumber = 1;
        }
        // exactly the actual attacking
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
        // resets everything after attacking
        if (spriteCounter > 25)
        {
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
            solidArea.width = 30;
            solidArea.height = 6;
        }
    }
    /*
    public void interactNPC(int i) // TODO make it so you don't attack when you have a chest in front of you
    {
        if (gp.keyHandler.enterPressed)
        {
            if (i != 999)
            {
                gp.gameState = GameState.DIALOGUE_STATE;
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

     */

    public void contactHostile(int i)
    {
        if (i != 999)
        {
            if (!invincible)
            {
                this.getHealth().alterCurrentHealth(-gp.Hostile[i].getDamageAmount().getDamage());
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
                int damageDealt = gp.playerEntity.getAttackDamageMelee();
                gp.Hostile[i].getHealth().alterCurrentHealth(-damageDealt);
                //gp.ui.addMessage(gp.player.getAttackDamageMelee() + " damage");
                gp.Hostile[i].damageHit = damageDealt;
                if (gp.Hostile[i].gotHit) { // TODO might add an arraylist so damage is shown of multiple damages at the same time
                    gp.Hostile[i].gotHit = false;
                    gp.Hostile[i].counter = 0;
                }
                gp.Hostile[i].gotHit = true;
                gp.Hostile[i].invincible = true;
                if (gp.Hostile[i].getHealth().getCurrentHealth() <= 0)
                {
                    gp.Hostile[i].dying = true;
                    for (Quest quest : getInProgressQuests())
                    {
                        for (Objective objective : quest.getObjectives())
                        {
                            if (objective instanceof KillingObjective killingObjective)
                            {
                                if (killingObjective.getEntityKill().name.equals(gp.Hostile[i].getName()))
                                {
                                    killingObjective.incrementKilled();
                                }
                            }
                        }
                    }
                    //gp.ui.addMessage("killed the " + gp.Hostile[i].getName());
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
                    tempScreenY = screenY - ModValues.TILE_SIZE;
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
                    tempScreenX = screenX - ModValues.TILE_SIZE;
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
    public void addInProgressQuests(Quest inProgressQuest)
    {
        this.inProgressQuest.add(inProgressQuest);
    }
    public void removeInProgressQuests(Quest removeInProgressQuest)
    {
        this.inProgressQuest.remove(removeInProgressQuest);
    }
    public List<Quest> getInProgressQuests()
    {
        return this.inProgressQuest;
    }
    public void addFinishedQuests(Quest questFinished)
    {
        this.finishedQuest.add(questFinished);
    }
    public void removeFinishedQuests(Quest removeFinishedQuest)
    {
        this.finishedQuest.remove(removeFinishedQuest);
    }
    public List<Quest> getFinishedQuests()
    {
        return this.finishedQuest;
    }

    public void addMoney(int money)
    {
        this.money += money;
    }
    public int getMoney()
    {
        return this.money;
    }
    public void setMoney(int money)
    {
        this.money = money;
    }

    /**
     * Method for the calculating of all the damage, counting the dices amount of it + the extra and other effects
     * @return the total attack damage calculated
     */
    public int getAttackDamageMelee()
    {
        if (firstHand != null && firstHand instanceof WeaponItem) {
            WeaponItem weaponItem = (WeaponItem) firstHand;
            return weaponItem.getDamageAmount().getDamage() + this.getEntityStats().getStrength();
        }
        else
            return this.getEntityStats().getStrength();
    }
    public void getAttackImage()
    {
        attackUp1 = setupCustom("Goblin_Attack_up_1", "player", ModValues.TILE_SIZE, ModValues.TILE_SIZE * 2);
        attackUp2 = setupCustom("Goblin_Attack_up_2", "player", ModValues.TILE_SIZE, ModValues.TILE_SIZE * 2);
        attackDown1 = setupCustom("Goblin_Attack_down_1", "player", ModValues.TILE_SIZE, ModValues.TILE_SIZE * 2);
        attackDown2 = setupCustom("Goblin_Attack_down_2", "player", ModValues.TILE_SIZE, ModValues.TILE_SIZE * 2);
        attackLeft1 = setupCustom("Goblin_Attack_left_1", "player", ModValues.TILE_SIZE * 2, ModValues.TILE_SIZE);
        attackLeft2 = setupCustom("Goblin_Attack_left_2", "player", ModValues.TILE_SIZE * 2, ModValues.TILE_SIZE);
        attackRight1 = setupCustom("Goblin_Attack_right_1", "player", ModValues.TILE_SIZE * 2, ModValues.TILE_SIZE);
        attackRight2 = setupCustom("Goblin_Attack_right_2", "player", ModValues.TILE_SIZE * 2, ModValues.TILE_SIZE);

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
}
