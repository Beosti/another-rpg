package main.entity.npcs;

import main.api.GameValues;
import main.entity.Entity;
import main.GamePanel;

public class NPCEntity extends Entity {
    GamePanel gp;
    public int dialogueIndex = 0;
    public String dialogues[] = new String[20];
    public NPCEntity(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.type = GameValues.NPC;
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
