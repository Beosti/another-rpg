package entity.npcs;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {
    GamePanel gp;

    public NPC_OldMan(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        direction = "up";
        speed = 1;

        getNpcImage();
        setDialogue();
    }

    public void getNpcImage()
    {
        front_walking1 = setup("oldman_down_1", "oldman");
        front_walking2 = setup("oldman_down_2", "oldman");
        back_walking1 = setup("oldman_up_1", "oldman");
        back_walking2 = setup("oldman_up_2", "oldman");
        left_walking1 = setup("oldman_left_1", "oldman");
        left_walking2 = setup("oldman_left_2", "oldman");
        right_walking1 = setup("oldman_right_1", "oldman");
        right_walking2 = setup("oldman_right_2", "oldman");
    }

    public void setDialogue()
    {
        dialogues[0] = "Danal is autistic af";
        dialogues[1] = "You should go look somewhere else";
        dialogues[2] = "WHat?";
        dialogues[3] = "Ok boomer?";

    }
    public void setAction()
    {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number from 100 to 100;

            if (i <= 25)
                direction = "down";
            if (i > 25 && i <= 50)
                direction = "up";
            if (i > 50 && i <= 75)
                direction = "left";
            if (i > 75 && i <= 100)
                direction = "right";
            actionLockCounter = 0;
        }
    }

    public void speak()
    {
        if (dialogues[dialogueIndex] == null)
            dialogueIndex =0;
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        super.speak();
    }
}
