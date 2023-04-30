package main.entity.npcs;

import main.GamePanel;
import main.data.quest.Objective;
import main.data.quest.Quest;
import main.quests.oldman.KillSlimesQuest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NPC_OldMan extends NPCEntity {
    GamePanel gp;
    public NPC_OldMan(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        direction = "up";
        speed = 1;
        Quest quest = new KillSlimesQuest();
        addQuest(quest);

        getImage();
        setDialogue();
    }

    @Override
    public void getImage() {
        front_walking1 = setup("oldman_down_1", "oldman");
        front_walking2 = setup("oldman_down_2", "oldman");
        back_walking1 = setup("oldman_up_1", "oldman");
        back_walking2 = setup("oldman_up_2", "oldman");
        left_walking1 = setup("oldman_left_1", "oldman");
        left_walking2 = setup("oldman_left_2", "oldman");
        right_walking1 = setup("oldman_right_1", "oldman");
        right_walking2 = setup("oldman_right_2", "oldman");

    }

    @Override
    public void setDialogue()
    {
        dialogues[0] = "Haven't seen you around here before, how are you?";
        dialogues[1] = "You should go look somewhere else";
        dialogues[2] = "What?";
        dialogues[3] = "Ok boomer?";
    }
    @Override
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

    @Override
    public void speak()
    {
        if (dialogues[dialogueIndex] == null)
            dialogueIndex = 0;

        boolean inCommonQuest = Collections.disjoint(quests, gp.player.inProgressQuest);
        if (!inCommonQuest) // has something in common
        {
            for (Quest quest : gp.player.inProgressQuest)
            {
                System.out.println(quest.areObjectivesComplete());
                if (quests.contains(quest) && quest.areObjectivesComplete())
                {
                    gp.player.addMoney(quest.getReward());
                    gp.ui.currentDialogue = "Good job on doing the quest, you've got a reward!";
                    gp.player.removeInProgressQuests(quest);
                    gp.player.addFinishedQuests(quest);
                }
                else
                {
                    gp.ui.currentDialogue = "When are you finished with the quest?";
                }
            }
        }
        else // has nothing in common
        {
            gp.ui.currentDialogue = dialogues[dialogueIndex];
            if (dialogueIndex == 2 && !(gp.player.finishedQuest.contains(quests))) {
                gp.ui.currentDialogue = "Could you please kill the slimes for me? 3 of em";
                gp.ui.choiceDialogue = true;
            }

        }

        gp.ui.questEntityNPC = this;
        dialogueIndex++;

        super.speak();
    }
}
