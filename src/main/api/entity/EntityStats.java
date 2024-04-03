package main.api.entity;


public class EntityStats {

    private int strength;
    private int dexterity;
    private int intelligence;
    private int charisma;
    private float speed;

    public EntityStats(int strength, int dexterity, int intelligence, int charisma, float speed)
    {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.charisma = charisma;
        this.speed = speed;
    }

    public void setStrength(int amount)
    {
        this.strength = amount;
    }
    public int getStrength()
    {
        return this.strength;
    }
    public void alterStrength(int amount)
    {
        this.strength += amount;
    }
    public void setDexterity(int amount)
    {
        this.dexterity = amount;
    }
    public int getDexterity()
    {
        return this.dexterity;
    }
    public void alterDexterity(int amount)
    {
        this.dexterity += amount;
    }
    public void setIntelligence(int amount)
    {
        this.intelligence = amount;
    }
    public int getIntelligence()
    {
        return this.intelligence;
    }
    public void alterIntelligence(int amount)
    {
        this.intelligence += amount;
    }
    public void setCharisma(int amount)
    {
        this.charisma = amount;
    }
    public int getCharisma(int amount)
    {
        return this.charisma;
    }
    public void alterCharisma(int amount)
    {
        this.charisma += amount;
    }
    public void setSpeed(float amount)
    {
        this.speed = amount;
    }
    public float getSpeed()
    {
        return this.speed;
    }
    public void alterSpeed(float amount)
    {
        this.speed += amount;
    }
}
