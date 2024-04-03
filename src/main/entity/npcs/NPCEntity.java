package main.entity.npcs;

import main.api.EntityCategory;
import main.api.GameValues;
import main.entity.Entity;
import main.GamePanel;
import main.entity.LivingEntity;

public class NPCEntity extends LivingEntity {
    GamePanel gp;
    public int dialogueIndex = 0;
    public String dialogues[] = new String[20];
    public NPCEntity(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.setEntityCategory(EntityCategory.HUMAN);
    }

    public void setDialogue() {};
    public void speak() {
        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
}
