package main;

import java.util.Random;

public class DamageAmount {

    private byte dice = 0;
    private byte amount = 0;
    private int damage = 0;

    public DamageAmount(byte amount, byte dice)
    {
        this.amount = amount;
        this.dice = dice;
    }

    public void setDice(byte dice)
    {
        this.dice = dice;
    }
    public byte getDice() {
        return this.dice;
    }
    public void setAmount(byte amount)
    {
        this.amount = amount;
    }
    public byte getAmount()
    {
        return this.amount;
    }
    public void setDamage(int damage)
    {
        this.damage = damage;
    }
    public int getDamage()
    {
        int damage = 0;
        Random random = new Random();
        for (int i = 0; i < amount; i++)
            damage += random.nextInt(dice) + 1;
        return damage;
    }
}
