package main.data.quest;

import java.util.List;

public abstract class Quest {

    private String title;
    private String description;
    private List<Objective> objectives;

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

    public List<Objective> getObjectives() {
        return objectives;
    }
}
