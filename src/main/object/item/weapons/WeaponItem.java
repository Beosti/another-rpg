package main.object.item.weapons;

import main.DamageAmount;
import main.GamePanel;
import main.object.item.Item;

public class WeaponItem extends Item {

    private int dice;
    private int amount;
    private int extra;
    private DamageAmount damageAmount;
    public WeaponItem(GamePanel gp) {
        super(gp);
    }

}
