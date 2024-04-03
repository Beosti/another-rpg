package main.api.entity;

import java.util.Random;

public class Health {
    private int currentHealth;
    private int maxHealth;

    public Health setRandomizedHealth(byte amount, byte dice)
    {
        int maxHealth = 0;
        Random random = new Random();
        for (int i = 0; i < amount; i++)
            maxHealth += random.nextInt(dice) + 1;
        Health health = new Health();
        health.setMaxHealth(maxHealth);
        health.setCurrentHealth(maxHealth);
        return health;
    }

    public int getCurrentHealth()
    {
        return this.currentHealth;
    }
    public void setCurrentHealth(int health)
    {
        this.currentHealth = health;
    }
    public void alterCurrentHealth(int amount)
    {
        this.currentHealth += amount;
    }

    public int getMaxHealth()
    {
        return this.maxHealth;
    }
    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }
    public void alterMaxHealth(int amount)
    {
        this.maxHealth += amount;
    }
}
