package main.data.quest;

import main.entity.Entity;
import main.init.EntityManager;

public class KillingObjective extends Objective{
    private Entity entityKill;
    private int amountToKill;
    private int amountKilled;
    private String description;

    public KillingObjective(Entity entityKill, int amountToKill, String description)
    {
        super(description);
        this.entityKill = entityKill;
        this.amountToKill = amountToKill;
        this.amountKilled = 0;
    }

    public Entity getEntityKill()
    {
        return entityKill;
    }
    public int getAmountKill()
    {
        return amountKilled;
    }
    public void incrementKilled()
    {
        amountKilled++;
    }
    public boolean isComplete()
    {
        return amountKilled >= amountToKill;
    }
}
