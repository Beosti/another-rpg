package main.api.entity;

public class Level {
    private int level;
    private int experience;
    private int maxExperience;

    public int getCurrentLevel()
    {
        return this.level;
    }
    public void setLevel(int level)
    {
        this.level = level;
    }
    public void alterLevel(int amount)
    {
        this.level += amount;
    }

    public int getCurrentExperience()
    {
        return this.experience;
    }
    public void setExperience(int experience)
    {
        this.experience = experience;
    }
    public void alterExperience(int amount)
    {
        this.experience += amount;
    }

    public int getMaxExperience()
    {
        return this.maxExperience;
    }
    public void setMaxExperience(int maxExperience)
    {
        this.maxExperience = maxExperience;
    }
    public void alterMaxExperience(int amount)
    {
        this.maxExperience += amount;
    }
}
