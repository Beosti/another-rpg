package main.entity.hostile;

import main.api.DamageCalculation;
import main.api.GameValues;
import main.entity.Entity;
import main.GamePanel;

import java.util.Random;

public class RedSlimeEntity extends Entity {
    public RedSlimeEntity(GamePanel gp) {
        super(gp);
        this.name = "Red slime";
        this.attackDamage = DamageCalculation.damageCalculation(1, 2);
        this.type = GameValues.HOSTILE;
        this.speed = 1;
        this.maxHealth = 5;
        this.health = maxHealth;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    @Override
    public void getImage() // TODO check if you want it to be in entity or not
    {
        front_walking1 = setup("redslime_down_1", "hostile/slime/red");
        front_walking2 = setup("redslime_down_2", "hostile/slime/red");
        back_walking1 = setup("redslime_down_1", "hostile/slime/red");
        back_walking2 = setup("redslime_down_2", "hostile/slime/red");
        left_walking1 = setup("redslime_down_1", "hostile/slime/red");
        left_walking2 = setup("redslime_down_2", "hostile/slime/red");
        right_walking1 = setup("redslime_down_1", "hostile/slime/red");
        right_walking2 = setup("redslime_down_2", "hostile/slime/red");
    }

    @Override
    public void setAction()
    {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number from 100 to 100;

            if (i <= 25)
                direction = "down";
            if (i > 25 && i <= 50)
                direction = "up";
            if (i > 50 && i <= 75)
                direction = "left";
            if (i > 75 && i <= 100)
                direction = "right";
            actionLockCounter = 0;
        }
    }

}
