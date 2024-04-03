package main.tile;

import main.GamePanel;
import main.init.ModValues;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int [ModValues.MAX_WORLD_COL] [ModValues.MAX_WORLD_ROW];

        getTileImage();
        loadMap("/maps/GoblinMapText");
    }

    public void getTileImage()
    {
        try
        {
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/Tile_grass1.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/Tile_grass2.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass/Tile_grass3.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path/Tile_path1.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water/Tile_water1.png"));
            tile[5].collision = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Specifically load the map to be drawn later
    public void loadMap(String map)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < ModValues.MAX_WORLD_COL && row < ModValues.MAX_WORLD_ROW)
            {
                String line = br.readLine();

                while (col < ModValues.MAX_WORLD_COL)
                {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == ModValues.MAX_WORLD_COL)
                {
                    col = 0;
                    row++;
                }
            }
        }
        catch (Exception e)
        {

        }
    }

    // Specifically draw the map
    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;

        // Draws the tiles
        while (worldCol < ModValues.MAX_WORLD_COL && worldRow < ModValues.MAX_WORLD_ROW)
        {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * ModValues.TILE_SIZE;
            int worldY = worldRow * ModValues.TILE_SIZE;
            int screenX = worldX - gp.playerEntity.worldX + gp.playerEntity.screenX;
            int screenY = worldY - gp.playerEntity.worldY + gp.playerEntity.screenY;

            // Draws the tiles specifically around the player, so spare on efficiency
            if (worldX  + ModValues.TILE_SIZE > gp.playerEntity.worldX - gp.playerEntity.screenX &&
                worldX  - ModValues.TILE_SIZE < gp.playerEntity.worldX + gp.playerEntity.screenX &&
                worldY  + ModValues.TILE_SIZE > gp.playerEntity.worldY - gp.playerEntity.screenY &&
                worldY  - ModValues.TILE_SIZE < gp.playerEntity.worldY + gp.playerEntity.screenY)
                g2.drawImage(tile[tileNum].image, screenX, screenY, ModValues.TILE_SIZE, ModValues.TILE_SIZE, null);
            worldCol++;

            if (worldCol == ModValues.MAX_WORLD_COL)
            {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
