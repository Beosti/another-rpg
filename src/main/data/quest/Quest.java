package main.data.quest;

import java.util.List;

public abstract class Quest {

    private String title;
    private String description;
    private List<Objective> objectives;
    private int reward;

    public Quest(String title, String description, List<Objective> objectives) {
        this.title = title;
        this.description = description;
        this.objectives = objectives;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean areObjectivesComplete() {
        for (Objective objective : objectives) {
            if (!objective.isComplete()) {
                return false;
            }
        }
        return true;
    }
    public List<Objective> getObjectives() {
        return objectives;
    }
    public void setReward(int valueReward)
    {
        this.reward = valueReward;
    }
    public int getReward()
    {
        return this.reward;
    }
}
