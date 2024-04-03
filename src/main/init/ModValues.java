package main.init;

public class ModValues {

    // SCREEN SETTINGS
    public static final int originalTileSize = 16; // 16x16 tile (default size of everything)
    public static final int scale = 3;

    public static final int TILE_SIZE = originalTileSize * scale; // 48x48 tile
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * maxScreenCol; // 768 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * maxScreenRow; // 576 pixels
    // WORLD SETTINGS
    public static final int MAX_WORLD_COL = 64;
    public static final int MAX_WORLD_ROW = 68;

}
