package main.object.item.weapons;

import main.GamePanel;
import main.object.item.Item;

public class WeaponItem extends Item {

    private int dice;
    private int amount;
    private int extra;
    public WeaponItem(GamePanel gp) {
        super(gp);
    }

    public void setDamage(int dice, int amount, int extra)
    {
        this.dice = dice;
        this.amount = amount;
        this.extra = extra;
    }

    public int getDice()
    {
        return this.dice;
    }
    public void setDice(int amount)
    {
        this.dice = amount;
    }
    public int getAmount()
    {
        return this.amount;
    }
    public void setAmount(int amount)
    {
        this.amount = amount;
    }
    public int getExtra()
    {
        return this.extra;
    }
    public void setExtra(int amount)
    {
        this.extra = amount;
    }

}
