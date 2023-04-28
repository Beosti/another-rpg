package main.data.quest;

public abstract class Objective {
    private String description;

    public Objective(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public abstract boolean isComplete();
}
