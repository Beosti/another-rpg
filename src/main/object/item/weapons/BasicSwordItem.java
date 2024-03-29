package main.object.item.weapons;

import main.GamePanel;
import main.api.DamageCalculation;
import main.object.item.Item;

public class BasicSwordItem extends WeaponItem {

    public BasicSwordItem(GamePanel gp)
    {
        super(gp);
        this.name = "Basic sword";
        this.description = "Just a basic sword";
        this.setDamage(2, 1, 0);
        down = setup("sword_normal", "weapons");
    }
}
