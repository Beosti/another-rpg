package main.quests.oldman;

import main.GamePanel;
import main.data.quest.KillingObjective;
import main.data.quest.Objective;
import main.data.quest.Quest;
import main.entity.Entity;
import main.entity.hostile.RedSlimeEntity;
import main.init.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class KillSlimesQuest extends Quest {

    public KillSlimesQuest() {
        super("Kill Slimes", "Slay 3 slimes to complete this quest", getSlimeObjectives());
        this.setReward(50);
    }

    private static List<Objective> getSlimeObjectives() {
        List<Objective> objectives = new ArrayList<>();
        objectives.add(new KillingObjective(EntityManager.createEntity(EntityManager.RED_SLIME), 3, "Kill 3 slimes"));
        return objectives;
    }
}

