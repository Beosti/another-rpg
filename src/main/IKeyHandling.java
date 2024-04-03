package main;

public interface IKeyHandling {

    public abstract void init(int keyCode);
    public default void released(int keyCode)
    {

    }
}
