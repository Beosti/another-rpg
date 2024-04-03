package main.entity.hostile;

import main.DamageAmount;
import main.api.DamageCalculation;
import main.api.EntityCategory;
import main.api.EntityState;
import main.api.GameValues;
import main.api.entity.EntityStats;
import main.api.entity.Health;
import main.entity.Entity;
import main.GamePanel;
import main.entity.LivingEntity;
import main.entity.MonsterEntity;

import java.util.Random;

public class RedSlimeEntity extends LivingEntity {
    public RedSlimeEntity(GamePanel gp) {
        super(gp);
        this.setName("Red Slime");
        this.setHealth(new Health().setRandomizedHealth((byte) 1, (byte) 2));
        this.setEntityStats(new EntityStats(3, 0, 0, 0, 1f));
        this.setDamageAmount(new DamageAmount((byte) 1, (byte) 2));
        this.setEntityCategory(EntityCategory.SLIME);
        this.setEntityState(EntityState.HOSTILE);
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
