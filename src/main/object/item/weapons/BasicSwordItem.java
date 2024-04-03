package main.object.item.weapons;

import main.DamageAmount;
import main.GamePanel;
import main.api.DamageCalculation;
import main.object.item.Item;

public class BasicSwordItem extends WeaponItem {

    public BasicSwordItem(GamePanel gp)
    {
        super(gp);
        this.name = "Basic sword";
        this.description = "Just a basic sword";
        this.setDamageAmount(new DamageAmount((byte) 1, (byte) 2));
        down = setup("sword_normal", "weapons");
    }
}
