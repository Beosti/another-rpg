package main;

public enum GameState {

    TITLE_SCREEN,
    PLAY_STATE,
    PAUSE_STATE,
    INVENTORY_STATE,
    DIALOGUE_STATE;

    public static boolean updateState(GameState gameState)
    {
        if (gameState.equals(PLAY_STATE) || gameState.equals(INVENTORY_STATE))
            return true;
        return false;
    }
    public static boolean pauseState(GameState gameState)
    {
        if (gameState.equals(PAUSE_STATE))
            return true;
        return false;
    }
}
