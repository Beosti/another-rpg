package main.entity;

import main.DamageAmount;
import main.GamePanel;
import main.api.EntityCategory;
import main.api.EntityState;
import main.api.GameValues;
import main.api.entity.EntityStats;
import main.api.entity.Health;
import main.api.entity.Level;
import main.object.item.Item;
import main.api.entity.EntityStats;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LivingEntity extends Entity{
    private String name;
    private EntityStats entityStats;
    private Health health;
    private Level level;
    private EntityCategory entityCategory;
    private EntityState entityState;
    private DamageAmount damageAmount;
    // IMAGES
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    //Every sprite needed for a whole entity image in the game
    public BufferedImage idle_front, front_walking1, front_walking2,
            idle_back, back_walking1, back_walking2,
            idle_left, left_walking1, left_walking2,
            idle_right, right_walking1, right_walking2;
    // SPRITE RELATED
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    // ATTACKING
    public int actionLockCounter = 0;
    public boolean invincible = false;
    boolean attacking = false;
    int invincibleCounter = 0;
    public boolean alive = true;
    public boolean dying = false;
    public int dyingCounter = 0;
    boolean gotHit = false;
    int damageHit = 0;
    int counter = 0;

    // ITEM
    public Item firstHand;
    public Item secondHand;

    public LivingEntity(GamePanel gp)
    {
        super(gp);
        this.entityState = EntityState.NEUTRAL;
    }
    public void getImage() {};

    public void setAction() {};
    public void update() {
        setAction();
        collisionOn = false;

        gp.Checker.checkTile(this);
        gp.Checker.checkObject(this, false);
        gp.Checker.checkItemEntity(this, false);
        gp.Checker.checkEntity(this, gp.NPC);
        gp.Checker.checkEntity(this, gp.Hostile);
        boolean checkPlayer = gp.Checker.checkPlayer(this);

        if (this.invincible)
        {
            invincibleCounter++;
            if(invincibleCounter > 40)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (this.entityCategory.equals(EntityCategory.MONSTER) && checkPlayer)
        {
            if (!gp.player.invincible)
            {
                gp.player.getHealth().alterCurrentHealth(-1);
                gp.player.invincible = true;
            }
        }

        if (!collisionOn)
        {
            switch (direction) {
                case "up" -> worldY -= this.entityStats.getSpeed();
                case "down" -> worldY += this.entityStats.getSpeed();
                case "left" -> worldX -= this.entityStats.getSpeed();
                case "right" -> worldX += this.entityStats.getSpeed();
            }
        }

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
    public void dyingAnimation(Graphics2D g2)
    {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        if (dyingCounter > i && dyingCounter <= i *2) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if (dyingCounter > i*2 && dyingCounter <= i * 3) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        if (dyingCounter > i*3 && dyingCounter <= i * 4) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if (dyingCounter > i*4)
        {
            dying = false;
            alive = false;
        }
    }

    public void draw(Graphics2D g2)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;


        //How does the player look in what direction
        if (down == null)
        {
            switch (direction) {
                case "up" -> {
                    if (spriteNumber == 1)
                        image = back_walking1;
                    if (spriteNumber == 2)
                        image = back_walking2;
                    if (spriteNumber == 3)
                        image = idle_back;
                }
                case "down" -> {
                    if (spriteNumber == 1)
                        image = front_walking1;
                    if (spriteNumber == 2)
                        image = front_walking2;
                    if (spriteNumber == 3)
                        image = idle_front;
                }
                case "left" -> {
                    if (spriteNumber == 1)
                        image = left_walking1;
                    if (spriteNumber == 2)
                        image = left_walking2;
                    if (spriteNumber == 3)
                        image = idle_left;
                }
                case "right" -> {
                    if (spriteNumber == 1)
                        image = right_walking1;
                    if (spriteNumber == 2)
                        image = right_walking2;
                    if (spriteNumber == 3)
                        image = idle_right;
                }
            }
        }
        else
            image = down;
        // MONSTER HP BAR
        if (this.entityState.equals(EntityState.HOSTILE))
        {
            System.out.println(this.getHealth().getCurrentHealth());
            double oneScale = (double) gp.tileSize/this.health.getMaxHealth();
            double healthBarValue = oneScale*this.health.getCurrentHealth();
            // the flashing away when you get hit
            if (dying)
            {
                dyingAnimation(g2);
            }
            // when a monster gets hit, the health bar appears
            if (gotHit && !dying)
            {
                counter++;
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16F));
                g2.setColor(Color.RED);
                g2.drawString("" + damageHit, screenX + 20, screenY-32 - counter / 5);
                if (counter > 80) {
                    gotHit = false;
                    counter = 0;
                }
            }

            if (this.health.getCurrentHealth() < this.health.getMaxHealth() && !dying)
            {
                g2.setColor(new Color(150, 255, 255));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) healthBarValue, 10);
            }
        }
        // Draws the tiles specifically around the player, so spare on efficiency
        if (worldX  + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX  - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY  + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY  - gp.tileSize < gp.player.worldY + gp.player.screenY)
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Health getHealth()
    {
        return this.health;
    }
    public void setHealth(Health health)
    {
        this.health = health;
    }
    public DamageAmount getDamageAmount()
    {
        return this.damageAmount;
    }
    public void setDamageAmount(DamageAmount damageAmount)
    {
        this.damageAmount = damageAmount;
    }
    public void setEntityCategory(EntityCategory entityCategory)
    {
        this.entityCategory = entityCategory;
    }
    public EntityCategory getEntityCategory()
    {
        return this.entityCategory;
    }
    public Level getLevel()
    {
        return this.level;
    }
    public void setLevel(Level level)
    {
        this.level = level;
    }
    public void setEntityState(EntityState entityState)
    {
        this.entityState = entityState;
    }
    public EntityState getEntityState()
    {
        return this.entityState;
    }
    public void setEntityStats(EntityStats entityStats)
    {
        this.entityStats = entityStats;
    }
    public EntityStats getEntityStats()
    {
        return this.entityStats;
    }
}
