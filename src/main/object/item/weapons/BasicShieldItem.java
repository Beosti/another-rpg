package main.object.item.weapons;

import main.GamePanel;
import main.api.DamageCalculation;
import main.object.item.Item;

public class BasicShieldItem extends Item {

    public BasicShieldItem(GamePanel gp)
    {
        super(gp);
        this.name = "Basic Shield";
        this.description = "Just a basic shield";
        down = setup("shield_normal", "weapons");
    }
}
